import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.enemyspaceship.EnemySpaceshipControlSystem;
import dk.sdu.mmmi.cbse.enemyspaceship.EnemySpaceshipPlugin;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

module EnemySpacehip {
    requires Common;
    requires CommonBullet;
    uses BulletSPI;
    provides IEntityProcessingService with EnemySpaceshipControlSystem;
    provides IGamePluginService with EnemySpaceshipPlugin;
}