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
        xAxis.setLabel("Devices");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data<>("Desktop", 178));
        dataSeries1.getData().add(new XYChart.Data<>("Phone"  , 65));
        dataSeries1.getData().add(new XYChart.Data<>("Tablet"  , 23));
        barChart.getData().add(dataSeries1);
        return barChart;
    }


    private Scene makeScene(Stage stage) {
        Label message = new Label("Hello, JavaFX!");
        BarChart<String, Number> barChart = makeChart();

        Button fileChooserButton = new Button();
        fileChooserButton.setText("Select file");
        fileChooserButton.setMaxWidth(Double.MAX_VALUE);
        FileChosenListener listener = new FileChosenListener(barChart, stage, message);
        fileChooserButton.setOnAction(listener::updateGraph);

        BorderPane appPane = new BorderPane();
        appPane.setPadding(new Insets(20, 20, 20, 20));
        VBox vbox = new VBox(fileChooserButton, message, barChart);
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
