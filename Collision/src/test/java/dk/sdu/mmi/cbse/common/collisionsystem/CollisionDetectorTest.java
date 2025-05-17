package dk.sdu.mmi.cbse.common.collisionsystem;

import dk.sdu.mmmi.cbse.common.collisionsystem.CollisionDetector;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.parts.CollisionPart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionDetectorTest {

    private static class Asteroid extends Entity {}
    private static class Player extends Entity {}
    private static class EnemySpaceship extends Entity {}

    private Entity createEntity(Class<? extends Entity> clazz, double x, double y, float radius) {
        try {
            Entity e = clazz.getDeclaredConstructor().newInstance();
            e.setX(x);
            e.setY(y);
            e.setRadius(radius);
            e.addPart(new CollisionPart());
            return e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private boolean isHit(Entity e) {
        return e.getPart(CollisionPart.class).isHit();
    }

    private void runCollisionTest(Entity e1, Entity e2, boolean expectHit) {
        GameData gameData = new GameData();
        World world = new World();
        world.addEntity(e1);
        world.addEntity(e2);

        CollisionDetector detector = new CollisionDetector();
        detector.process(gameData, world);

        String types = e1.getClass().getSimpleName() + " vs " + e2.getClass().getSimpleName();

        if (expectHit) {
            assertTrue(isHit(e1), types + ": Entity 1 should be hit");
            assertTrue(isHit(e2), types + ": Entity 2 should be hit");
        } else {
            assertFalse(isHit(e1), types + ": Entity 1 should NOT be hit");
            assertFalse(isHit(e2), types + ": Entity 2 should NOT be hit");
        }
    }

    @Test
    void testSameTypeNoCollision() {
        runCollisionTest(
                createEntity(Asteroid.class, 0, 0, 5),
                createEntity(Asteroid.class, 3, 4, 5),
                false
        );

        runCollisionTest(
                createEntity(Player.class, 0, 0, 5),
                createEntity(Player.class, 3, 4, 5),
                false
        );

        runCollisionTest(
                createEntity(EnemySpaceship.class, 0, 0, 5),
                createEntity(EnemySpaceship.class, 3, 4, 5),
                false
        );
    }

    @Test
    void testDifferentTypesDoCollide() {
        runCollisionTest(
                createEntity(Asteroid.class, 0, 0, 5),
                createEntity(Player.class, 3, 4, 5),
                true
        );

        runCollisionTest(
                createEntity(Asteroid.class, 0, 0, 5),
                createEntity(EnemySpaceship.class, 3, 4, 5),
                true
        );

        runCollisionTest(
                createEntity(Player.class, 0, 0, 5),
                createEntity(EnemySpaceship.class, 3, 4, 5),
                true
        );
    }
}
