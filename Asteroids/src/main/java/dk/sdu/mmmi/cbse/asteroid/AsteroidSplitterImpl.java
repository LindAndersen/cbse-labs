package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        List<Entity> asteroids = createAsteroids(e);

        world.removeEntity(e);

        for(Entity asteroid : asteroids) {
            world.addEntity(asteroid);
        }
    }


    private List<Entity> createAsteroids(Entity original) {
        int offset = 30;
        Entity asteroid1 = createAsteroid(original, original.getRotation()-45, offset);
        Entity asteroid2 = createAsteroid(original, original.getRotation()+45, -offset);
        List<Entity> asteroids = new ArrayList<>();
        asteroids.add(asteroid1);
        asteroids.add(asteroid2);
        return asteroids;
    }

    private Entity createAsteroid(Entity original, double rotation, int offset) {
        Entity asteroid = new Asteroid();
        float size = original.getRadius()/2;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(original.getX()+offset);
        asteroid.setY(original.getY()+offset);
        asteroid.setRadius(size);
        asteroid.setRotation(rotation);
        return asteroid;
    }
}
