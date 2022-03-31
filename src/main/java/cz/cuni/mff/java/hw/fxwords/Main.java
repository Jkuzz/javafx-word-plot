package cz.cuni.mff.java.hw.fxwords;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderRepeat;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label message = new Label("Hello, JavaFX!");

        Button fileChooserButton = new Button();
        fileChooserButton.setText("Select file");
        fileChooserButton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File chosenFile = fileChooser.showOpenDialog(stage);
            if(chosenFile != null) {
                message.setText(chosenFile.getAbsolutePath());
            }
        });

        BorderPane appPane = new BorderPane();
        appPane.setPadding(new Insets(20, 20, 20, 20));
        VBox vbox = new VBox(fileChooserButton, message);
        appPane.setCenter(vbox);

        stage.setScene(new Scene(appPane));
        stage.setTitle("Hello");
        stage.show();
    }
}
