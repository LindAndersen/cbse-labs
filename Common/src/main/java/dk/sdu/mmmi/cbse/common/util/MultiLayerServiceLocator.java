package dk.sdu.mmmi.cbse.common.util;

import java.io.IOException;
import java.lang.module.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MultiLayerServiceLocator {

    INSTANCE;

    private final List<ModuleLayer> pluginLayers = new ArrayList<>();
    private final Map<Class<?>, List<Object>> serviceCache = new HashMap<>();
    private boolean initialized = false;

    /**
     * Call this early during app startup.
     */
    public synchronized void init() {
        if (initialized) return;

        Path pluginsDir = Paths.get("plugins");
        if (!Files.isDirectory(pluginsDir)) return;

        try (Stream<Path> paths = Files.list(pluginsDir)) {
            for (Path pluginJar : paths.filter(p -> p.toString().endsWith(".jar")).toList()) {
                try {
                    ModuleLayer layer = loadPluginModuleLayer(pluginJar);
                    if (layer != null) {
                        pluginLayers.add(layer);
                    }
                } catch (Exception e) {
                    System.err.println("Failed to load plugin: " + pluginJar);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading plugins directory", e);
        }

        initialized = true;
    }

    private ModuleLayer loadPluginModuleLayer(Path jarPath) throws IOException {
        ModuleFinder finder = ModuleFinder.of(jarPath);
        Set<String> moduleNames = finder.findAll().stream()
                .map(mref -> mref.descriptor().name())
                .collect(Collectors.toSet());

        Configuration config = ModuleLayer.boot().configuration()
                .resolve(finder, ModuleFinder.of(), moduleNames);

        URL[] urls = { jarPath.toUri().toURL() };
        ClassLoader pluginClassLoader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());

        return ModuleLayer.boot().defineModulesWithOneLoader(config, pluginClassLoader);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> locateAll(Class<T> service) {
        if (!initialized) {
            throw new IllegalStateException("ServiceLocator must be initialized before use.");
        }

        if (serviceCache.containsKey(service)) {
            return (List<T>) serviceCache.get(service);
        }

        List<T> all = new ArrayList<>();
        for (ModuleLayer layer : pluginLayers) {
            ServiceLoader<T> loader = ServiceLoader.load(layer, service);
            for (T instance : loader) {
                all.add(instance);
            }
        }

        serviceCache.put(service, new ArrayList<>(all));
        return all;
    }
}
