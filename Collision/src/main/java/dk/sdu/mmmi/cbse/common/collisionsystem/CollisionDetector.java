package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.parts.CollisionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;                    
                }

                if (entity1.getClass().equals(entity2.getClass())) {
                    continue;
                }

                // CollisionDetection
                if (this.collides(entity1, entity2)) {
                    if (entity1.hasPart(CollisionPart.class)) {
                        entity1.getPart(CollisionPart.class).setHit(true);
                    }
                    if (entity2.hasPart(CollisionPart.class)) {
                        entity2.getPart(CollisionPart.class).setHit(true);
                    }
                }
            }
        }

    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }


}
