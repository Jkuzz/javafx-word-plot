package cz.cuni.mff.java.hw.fxwords;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderRepeat;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private static BarChart<String, Number> makeChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Words");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Occurrences");
        return new BarChart<>(xAxis, yAxis);
    }


    private Scene makeScene(Stage stage) {
        BarChart<String, Number> barChart = makeChart();

        Button fileChooserButton = new Button();
        fileChooserButton.setText("Select file");
        fileChooserButton.setMaxWidth(Double.MAX_VALUE);
        FileChosenListener listener = new FileChosenListener(barChart, stage);
        fileChooserButton.setOnAction(listener::updateGraph);

        BorderPane appPane = new BorderPane();
        appPane.setPadding(new Insets(20, 20, 20, 20));
        VBox vbox = new VBox(fileChooserButton, barChart);
        appPane.setCenter(vbox);
        return new Scene(appPane);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(makeScene(stage));
        stage.setTitle("Hello");
        stage.show();
    }
}
