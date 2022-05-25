package com.user.searchengine.searchengine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ControllerSearchScreen implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    static ArrayList<String> words = listResults();

    public static ArrayList<String> listResults() {
        HashMap<String, File> results = RunnableTask.createSearchResults();
        Set<String> resultsSet = results.keySet();
        ArrayList<String> resultsList = new ArrayList<String>();
        for (String s : resultsSet) {
            resultsList.add(s);
        }
        return resultsList;
    }

    @FXML
    private TextField searchBar;
    @FXML
    private ListView<String> listView;

    @FXML
    void search(ActionEvent event) throws IOException {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(), words));
        openScene2(event);
    }

    @FXML
    void dynamicSearch(InputEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBar.getText(), words));
    }


    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listView.getItems().addAll(words);
        listView.setOnMouseClicked(event -> {
            //RunnableTask.displayImage(listView.getId());
            RunnableTask.displayImage(listView.getSelectionModel().getSelectedItem());
        });
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    public void openScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SearchScreen.FXML"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DisplayImageResults.FXML"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
