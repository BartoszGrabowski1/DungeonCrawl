package com.codecool.dungeoncrawl;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

public class GameController {

    @FXML
    private Canvas canvas;
    @FXML
    private BorderPane borderpane;

    public Canvas getCanvas() {
        return canvas;
    }

    public BorderPane getBorderpane() {
        return borderpane;
    }

}
