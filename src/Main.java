import Controller.Controller;
import Controller.DefaultController;
import Model.Game;
import Model.Model;
import View.DefaultView;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Model game = new Game();
        View view = new DefaultView(game);
        Controller controller = new DefaultController(game, view);
        Thread thread = new Thread(controller);
        thread.start();
        view.start(stage);
    }
}
