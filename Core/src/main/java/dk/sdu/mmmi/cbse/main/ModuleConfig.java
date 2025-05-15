package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.MultiLayerServiceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class ModuleConfig {

    public ModuleConfig() {
    }

    @Bean
    public Game game(){
        return new Game(gamePluginServices(), entityProcessingServiceList(), postEntityProcessingServices());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServiceList(){
        return MultiLayerServiceLocator.INSTANCE.locateAll(IEntityProcessingService.class);
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return MultiLayerServiceLocator.INSTANCE.locateAll(IGamePluginService.class);
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return MultiLayerServiceLocator.INSTANCE.locateAll(IPostEntityProcessingService.class);
    }
}