package com.codecool.dungeoncrawl.logic.controller;

import com.codecool.dungeoncrawl.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameController {

    @FXML
    private Canvas canvas = new Canvas(640, 640);
    @FXML
    private BorderPane borderpane = new BorderPane();




    public Canvas getCanvas() {
        return canvas;
    }

    public BorderPane getBorderpane() {
        return borderpane;
    }

    public void getFight() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fight-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("FIGHT MODE");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gameOverView() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("end-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Game over!");
            stage.setScene(scene);
            stage.show();
            Timeline animation = new Timeline(new KeyFrame(Duration.seconds(3.0), e -> stage.close()));
            animation.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}