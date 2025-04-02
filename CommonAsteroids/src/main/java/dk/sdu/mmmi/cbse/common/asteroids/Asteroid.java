package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.parts.CollisionPart;

/**
 *
 * @author corfixen
 */
public class Asteroid extends Entity {
    public Asteroid()
    {
        super();
        addPart(new CollisionPart());
    }
}
