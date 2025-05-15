import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    opens dk.sdu.mmmi.cbse.main to javafx.graphics,spring.core, spring.beans, spring.context;
    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
}


