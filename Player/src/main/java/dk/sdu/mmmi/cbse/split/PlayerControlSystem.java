package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.parts.CollisionPart;
import dk.sdu.mmmi.cbse.common.parts.HealthPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {
    private static final long SHOOT_COOLDOWN = 100;

    @Override
    public void process(GameData gameData, World world) {
            
        for (Entity entityPlayer : world.getEntities(Player.class)) {
            Player player = (Player) entityPlayer;
            if (player.hasPart(HealthPart.class) && player.hasPart(CollisionPart.class) && player.getPart(CollisionPart.class).isHit())
            {
                HealthPart playerHealthPart = player.getPart(HealthPart.class);
                playerHealthPart.handleHit();
                if (!playerHealthPart.isAlive())
                {
                    world.removeEntity(player);
                }

                player.getPart(CollisionPart.class).setHit(false);
            }

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 5);                
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 5);                
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            long currentTime = System.currentTimeMillis();
            if(gameData.getKeys().isDown(GameKeys.SPACE) && (currentTime - player.getLastShotTime()) >= SHOOT_COOLDOWN) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {
                            world.addEntity(spi.createBullet(player, gameData));
                            player.setLastShotTime(currentTime);
                        }
                );
            }
            
        if (player.getX() < 0) {
            player.setX(1);
        }

        if (player.getX() > gameData.getDisplayWidth()) {
            player.setX(gameData.getDisplayWidth()-1);
        }

        if (player.getY() < 0) {
            player.setY(1);
        }

        if (player.getY() > gameData.getDisplayHeight()) {
            player.setY(gameData.getDisplayHeight()-1);
        }

                                        
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
