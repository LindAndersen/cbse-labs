package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.parts.CollisionPart;
import dk.sdu.mmmi.cbse.common.parts.HealthPart;

/**
 *
 * @author Emil
 */
public class Player extends Entity {
    public Player() {
        super();
        addPart(new CollisionPart());
        addPart(new HealthPart(10));
    }
}
