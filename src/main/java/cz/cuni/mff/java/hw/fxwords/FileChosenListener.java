package cz.cuni.mff.java.hw.fxwords;

import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public record FileChosenListener(BarChart<String, Number> wordsChart,
                                 Stage stage, Label message) {

    public void updateGraph(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.setInitialDirectory(new File("."));
        File chosenFile = fileChooser.showOpenDialog(stage);
        if (chosenFile == null) {
            return;
        }
        message.setText(chosenFile.getAbsolutePath());
    }
}
