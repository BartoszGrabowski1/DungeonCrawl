package com.codecool.dungeoncrawl;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController {

    @FXML
    public BorderPane borderpane;

    private Canvas canvas = new Canvas(640, 640);
    @FXML
    private BorderPane borderpane = new BorderPane();

    public Canvas getCanvas() {
        return canvas;
    }

    public BorderPane getBorderpane() {
        return borderpane;
    }
}
