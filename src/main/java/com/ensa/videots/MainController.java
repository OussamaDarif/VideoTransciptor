package com.ensa.videots;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    @FXML
    private StackPane pagesStack;

    // a hashmap containing menus buttons ids and their corresponding pages
    private static Map<String, String> menus_pages = new HashMap<>();

    static {
        menus_pages.put("settingsMenuButton", PageNavigator.SETTINGSPAGE);
        menus_pages.put("micMenuButton", PageNavigator.SPEECHRECOGNITIONPAGE);
        menus_pages.put("textToSpeechMenuButton", PageNavigator.TEXTTOSPEECHPAGE);
        menus_pages.put("videoTransMenuButton", PageNavigator.VIDEOTRANSCRIPTIONPAGE);
        menus_pages.put("infoMenuButton", PageNavigator.INFOSPAGE);
        menus_pages.put("browseYoutubeMenuButton", PageNavigator.BROWSEYOUTUBEPAGE);
    }

    public void loadTextToSpeechPage(Pane pane) {
        pane.getStylesheets().add(getClass().getResource("text-to-speech.css").toExternalForm());
    }

    public Object loadPage(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(PageNavigator.class.getResource(fxml));
        Node node = loader.load();

        if (PageNavigator.TEXTTOSPEECHPAGE.equals(fxml)) {
            loadTextToSpeechPage((Pane) node);
        }
        pagesStack.getChildren().setAll(node);
        return loader.getController();
    }

    public void onMenuButtonClicked(ActionEvent event) {
        String clickedButtonId = ((Node) event.getSource()).getId();
        // set background color for clicked button
        ((Button) event.getSource()).setStyle("-fx-background-color: #D2E4F1");
        for (String btnId :
                menus_pages.keySet()) {
            if (!btnId.equals(clickedButtonId)) {
                ((Button) event.getSource()).getScene().lookup("#" + btnId).setStyle(null);
            }
        }
        PageNavigator.loadPage(menus_pages.get(clickedButtonId));
    }

}
