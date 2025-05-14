package dk.sdu.mmmi.cbse.split;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.parts.CollisionPart;
import dk.sdu.mmmi.cbse.common.parts.HealthPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemySpaceshipControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        Random random = new Random();

        for (Entity spaceship : world.getEntities(EnemySpaceship.class)) {
            if (spaceship.hasPart(HealthPart.class) && spaceship.hasPart(CollisionPart.class) && spaceship.getPart(CollisionPart.class).isHit())
            {
                HealthPart spaceshipHealthPart = spaceship.getPart(HealthPart.class);
                spaceshipHealthPart.handleHit();
                if (!spaceshipHealthPart.isAlive())
                {
                    world.removeEntity(spaceship);
                }

                spaceship.getPart(CollisionPart.class).setHit(false);
            }

            int randomInt = random.nextInt(-3,3) + 1;
            spaceship.setRotation(spaceship.getRotation() + randomInt);

            double changeX = Math.cos(Math.toRadians(spaceship.getRotation()));
            double changeY = Math.sin(Math.toRadians(spaceship.getRotation()));
            spaceship.setX(spaceship.getX() + changeX);
            spaceship.setY(spaceship.getY() + changeY);

            if (random.nextDouble() < 0.07)
            {
                getBulletSPIs().stream().findFirst().ifPresent(spi ->
                {
                    world.addEntity(spi.createBullet(spaceship, gameData));
                });
            }

            if (spaceship.getX() < 0) {
                spaceship.setX(1);
            }

            if (spaceship.getX() > gameData.getDisplayWidth()) {
                spaceship.setX(gameData.getDisplayWidth() - 1);
            }

            if (spaceship.getY() < 0) {
                spaceship.setY(1);
            }

            if (spaceship.getY() > gameData.getDisplayHeight()) {
                spaceship.setY(gameData.getDisplayHeight() - 1);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
