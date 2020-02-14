package net.divinechili.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.divinechili.ModLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Button exitButton;
    @FXML
    public VBox PluginOptionsBox;
    @FXML
    public GridPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exitButton.setOnAction(event -> {
            event.consume();
            ModLoader.close();
        });

    }
}
