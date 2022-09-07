package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.game.map.GameMap;
import com.codecool.dungeoncrawl.game.map.generator.MapConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ConfController {

    @FXML
    private TextField height;

    @FXML
    private TextField levels;

    @FXML
    private TextField maxRoomYX;

    @FXML
    private TextField maxRooms;

    @FXML
    private TextField medusas;

    @FXML
    private TextField minRoomXY;

    @FXML
    private TextField npcs;

    @FXML
    private TextField randomConnections;

    @FXML
    private TextField randomSpurs;

    @FXML
    private TextField roomOverlap;

    @FXML
    private TextField skeletons;

    @FXML
    private TextField vampires;

    @FXML
    private TextField width;

    @FXML
    void startGame(ActionEvent event) throws SQLException {
        MapConfig.LEVELS.setNumber(Integer.parseInt(levels.getText()));
        Main.LEVELS_AMOUNT = MapConfig.LEVELS.getNumber();
        Main.levels = new GameMap[MapConfig.LEVELS.getNumber()];

        MapConfig.WIDTH.setNumber(Integer.parseInt(width.getText()));
        MapConfig.HEIGHT.setNumber(Integer.parseInt(height.getText()));
        MapConfig.MAX_ROOMS.setNumber(Integer.parseInt(maxRooms.getText()));
        MapConfig.MIN_ROOM_XY.setNumber(Integer.parseInt(minRoomXY.getText()));
        MapConfig.MAX_ROOM_XY.setNumber(Integer.parseInt(maxRoomYX.getText()));
        MapConfig.ROOM_OVERLAP.setRoomOverlap(roomOverlap.getText().equals("true"));
        MapConfig.RANDOM_CONNECTIONS.setNumber(Integer.parseInt(randomConnections.getText()));
        MapConfig.RANDOM_SPURS.setNumber(Integer.parseInt(randomSpurs.getText()));
        MapConfig.SKELETONS.setNumber(Integer.parseInt(skeletons.getText()));
        MapConfig.VAMPIRES.setNumber(Integer.parseInt(vampires.getText()));
        MapConfig.MEDUSAS.setNumber(Integer.parseInt(medusas.getText()));
        MapConfig.NPCS.setNumber(Integer.parseInt(npcs.getText()));

        ViewController.setGameView();
    }

}
