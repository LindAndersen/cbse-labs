package dk.sdu.mmmi.cbse.enemyspaceship;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.parts.CollisionPart;
import dk.sdu.mmmi.cbse.common.parts.HealthPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemySpaceshipPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        world.addEntity(createEnemySpacehip(gameData));
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    private Entity createEnemySpacehip(GameData gameData)
    {
        Entity enemySpacehip = new EnemySpacehip();
        enemySpacehip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemySpacehip.setX(gameData.getDisplayHeight()/10);
        enemySpacehip.setY(gameData.getDisplayWidth()/10);
        enemySpacehip.setRadius(8);
        enemySpacehip.addPart(new CollisionPart());
        enemySpacehip.addPart(new HealthPart(50));
        return enemySpacehip;
    }
}
