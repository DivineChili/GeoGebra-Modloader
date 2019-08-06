package net.divinechili.ggbml.wrapper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Wrapper extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox box = new VBox();

        Label lbl = new Label("Hello World!");
        box.getChildren().add(lbl);

        primaryStage.setScene(new Scene(box));
        primaryStage.setTitle("Test...");
        primaryStage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
