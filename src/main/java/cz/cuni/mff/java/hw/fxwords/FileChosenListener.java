package cz.cuni.mff.java.hw.fxwords;

import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public record FileChosenListener(BarChart<String, Number> wordsChart,
                                 Stage stage) {

    public void updateGraph(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.setInitialDirectory(new File("."));
        File chosenFile = fileChooser.showOpenDialog(stage);
        if (chosenFile == null) {
            return;
        }
        String text;
        try {
            text = Files.readString(Path.of(chosenFile.getPath()));
        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Unable to read file");
            a.show();
            return;
        }
        LinkedHashMap<String, Integer> countedWords = countWords(text);
        while (wordsChart.getData().size() > 0) {  // clear previous series
            wordsChart.getData().remove(0);
        }
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();

        List<Map.Entry<String, Integer>> sortedWordCount = countedWords.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .toList()
                .subList(0, 25);
        for(Map.Entry<String, Integer> entry: sortedWordCount) {
            System.out.println(entry.getKey() + ":\t\t" + entry.getValue());
            dataSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        dataSeries.setName(chosenFile.getAbsolutePath());
        wordsChart.getData().add(dataSeries);

    }

    private LinkedHashMap<String, Integer> countWords(String text) {
        LinkedHashMap<String, Integer> wordCounts = new LinkedHashMap<>();
        for(String word: text.split("\\s+")) {
            Integer oldCount = wordCounts.get(word);
            if(oldCount == null) {
                oldCount = 0;
            }
            wordCounts.put(word, oldCount + 1);
        }

        return wordCounts;
    }
}
