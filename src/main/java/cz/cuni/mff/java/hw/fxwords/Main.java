package cz.cuni.mff.java.hw.fxwords;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static BarChart<String, Number> makeChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Words");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Occurrences");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setBarGap(0.15);
        barChart.setMinWidth(800);
        return barChart;
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
        appPane.setTop(fileChooserButton);
        appPane.setCenter(barChart);
        appPane.setMinWidth(barChart.getMinWidth());
        stage.setMinWidth(appPane.getMinWidth() + appPane.getPadding().getLeft() + appPane.getPadding().getRight());
        return new Scene(appPane);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(makeScene(stage));
        stage.setTitle("Hello");
        stage.show();
    }
}
