package com.codecool.dungeoncrawl.game.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class EndGameController {

    @FXML
    private Button btn;

    @FXML
    private ImageView gameOverImg;

    @FXML
    private AnchorPane pane;

    @FXML
    void initialize() {
        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(3.0), e -> ViewController.closeView()));
        animation.play();
    }

}
