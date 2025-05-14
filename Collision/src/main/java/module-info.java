import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.CollisionDetector;

module Collision {
    requires Common;   
    provides IPostEntityProcessingService with CollisionDetector;
}