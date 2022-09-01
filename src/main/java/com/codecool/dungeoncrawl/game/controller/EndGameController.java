package com.codecool.dungeoncrawl.game.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;

import static com.codecool.dungeoncrawl.game.music.MusicPlayer.stopSounds;

public class EndGameController {

    @FXML
    void initialize() {
        delayClose(3.0d);
    }

    /**
     * Delay Close
     * <p></p>
     * Stops all sounds and closes the app after delayed time
     * @param seconds after this time app will be closed
     */
    private static void delayClose(double seconds) {
        stopSounds();
        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(seconds), e -> ViewController.closeView()));
        animation.play();
    }

}
