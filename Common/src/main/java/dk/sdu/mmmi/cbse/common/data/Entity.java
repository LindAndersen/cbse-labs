package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.services.Part;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private Map<Class<? extends Part>, Part> parts = new HashMap<>();

    public <T extends Part> T getPart(Class<T> partClass) {
        return partClass.cast(parts.get(partClass));
    }

    public <T extends Part> void addPart(T part) {
        parts.put(part.getClass(), part);
    }

    public <T extends Part> void removePart(Class<T> partClass) {
        parts.remove(partClass);
    }

    public boolean hasPart(Class<? extends Part> partClass) {
        return parts.containsKey(partClass);
    }

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
        
    public float getRadius() {
        return this.radius;
    }
}
