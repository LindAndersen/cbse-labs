package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.parts.CollisionPart;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity {
    public Bullet() {
        super();
        addPart(new CollisionPart());
    }
}
