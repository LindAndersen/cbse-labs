import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.split.EnemySpaceshipControlSystem;
import dk.sdu.mmmi.cbse.split.EnemySpaceshipPlugin;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

module EnemySpaceship {
    requires Common;
    requires CommonBullet;
    uses BulletSPI;
    provides IEntityProcessingService with EnemySpaceshipControlSystem;
    provides IGamePluginService with EnemySpaceshipPlugin;
}