package View;

import Model.GameItems.FieldStatus;
import Model.Model;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultView extends View {
    Model model;
    HashMap<ArrayList<Integer>,Button> buttonLocation;
    public DefaultView(Model model) {
        this.model = model;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World!");
        GridPane pane = new GridPane();
        buttonLocation = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                button.setMinWidth(100);
                button.setMinHeight(100);
                button.setWrapText(true);
                button.setStyle(String.format("-fx-font-size: %dpx;", (int) (0.45 * 100)));
                ArrayList<Integer> location = new ArrayList<>();
                location.add(i);
                location.add(j);
                buttonLocation.put(location, button);
                pane.add(button, i, j);
            }
        }

        stage.setScene(new Scene(pane, 300, 300));
        stage.show();
    }

    @Override
    public void updatePosition() {
        Platform.runLater(() -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    FieldStatus status = model.getFieldStatus(i, j);
                    ArrayList<Integer> key = new ArrayList<>();
                    key.add(i);
                    key.add(j);
                    Button button = buttonLocation.get(key);
                    if (status == FieldStatus.CIRCLE) {
                        button.setText("O");
                    } else if (status == FieldStatus.CROSS) {
                        button.setText("X");
                    } else {
                        button.setText("");
                    }
                }
            }
        }
        );

    }
}
