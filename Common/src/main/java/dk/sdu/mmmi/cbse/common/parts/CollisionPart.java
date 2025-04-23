package dk.sdu.mmmi.cbse.common.parts;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.Part;

public class CollisionPart implements Part {
    private boolean isHit = false;

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
