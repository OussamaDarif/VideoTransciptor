package com.ensa.videots;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(PageNavigator.MAINPAGE));
        MainController mainController = loader.getController();
        PageNavigator.setMainController(mainController);
        PageNavigator.loadPage(PageNavigator.SETTINGSPAGE);
        mainPane.lookup("#" + "settingsMenuButton").setStyle("-fx-background-color: #D2E4F1");
        return mainPane;
    }

    public Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
        return scene;
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("video transcript");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icons/transcription_logo.png")));
        stage.setScene(createScene(loadMainPane()));
        stage.setMinWidth(720.0);
        stage.setMaxWidth(720.0);
        stage.setMinHeight(512.0);
        stage.setMaxHeight(512.0);
        stage.setOnCloseRequest(event -> {
            try {
                //close asynchttp api requests client
                TextToSpeechController.client.close();
                BrowseYoutubeController.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
