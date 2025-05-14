package dk.sdu.mmmi.cbse.split;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.parts.CollisionPart;
import dk.sdu.mmmi.cbse.common.parts.HealthPart;

public class EnemySpaceship extends Entity {
    public EnemySpaceship()
    {
        super();
        addPart(new CollisionPart());
        addPart(new HealthPart(100));
    }
}
