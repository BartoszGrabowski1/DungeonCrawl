package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.game.map.GameMap;
import com.codecool.dungeoncrawl.game.controller.*;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    public static Scene scene;
    public static final int SCREEN_SIZE = 20;
    public static final int LEVELS_AMOUNT = 3;
    public static final GameMap[] levels = new GameMap[3];
    public static GameMap bossLevel;
    public static int level = 1;
    public static int eqNumber = 0;
    public static GameMap map;
    public static Timeline animation;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.alwaysOnTopProperty();
        primaryStage.requestFocus();
        stage = primaryStage;
        ViewController.setMenuView();
    }

}