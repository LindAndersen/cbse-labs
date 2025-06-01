package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.util.MultiLayerServiceLocator;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {
    public static void main(String[] args) {
        MultiLayerServiceLocator.INSTANCE.init();
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);

        Game game = ctx.getBean(Game.class);
        game.start(window);
        game.render();
    }
}
