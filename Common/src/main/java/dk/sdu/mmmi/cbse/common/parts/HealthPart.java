package dk.sdu.mmmi.cbse.common.parts;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.Part;

public class HealthPart implements Part {
    private int health;

    public HealthPart(int health) {
        this.health = health;
    }

    public void handleHit()
    {
        health--;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return health >= 0;
    }
}
