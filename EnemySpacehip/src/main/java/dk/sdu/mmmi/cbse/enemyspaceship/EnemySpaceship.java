package dk.sdu.mmmi.cbse.enemyspaceship;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.parts.CollisionPart;

public class EnemySpaceship extends Entity {
    public EnemySpaceship()
    {
        super();
        addPart(new CollisionPart());
    }
}
