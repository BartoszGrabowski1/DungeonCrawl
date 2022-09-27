package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.List;


public class LoadGameController {
    GameDatabaseManager gameDatabaseManager = new GameDatabaseManager();

    public static PlayerModel playerModel;
    public static GameState gameState;
    public static List<ItemModel> inventory;
    public String playerName;

    @FXML
    private ImageView mainMenuButton;

    @FXML
    private ListView savesList;

    @FXML
    private ImageView selectButton;

    private void initButton(ImageView button, String buttonImage, String buttonImageHover) {
        button.setImage(new Image(Main.class.getResourceAsStream(buttonImage)));
        button.setOnMouseEntered(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImageHover))));
        button.setOnMouseExited(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImage))));
    }

    private void initButtons() {
        initButton(selectButton, "/com/codecool/dungeoncrawl/img/button_select.png", "/com/codecool/dungeoncrawl/img/button_select_hover.png");
        initButton(mainMenuButton, "/com/codecool/dungeoncrawl/img/button_menu.png", "/com/codecool/dungeoncrawl/img/button_menu_hover.png");
    }

    @FXML
    void initialize() throws SQLException {
        initButtons();


        gameDatabaseManager.setup();
        savesList.getItems().addAll(gameDatabaseManager.getLoadNames());
        savesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {

                playerName = (savesList.getSelectionModel().getSelectedItem()).toString();
                playerModel = gameDatabaseManager.getSelectedPlayer(playerName);
                gameState = gameDatabaseManager.getGameState(playerModel.getId());
                inventory = gameDatabaseManager.getUserItems(playerModel.getId());
                GameController.isMapLoaded = true;


            }
        });
    }

    @FXML
    void backToMainMenu(MouseEvent event) {
        ViewController.setMenuView();
    }

    @FXML
    void loadGame(MouseEvent event) {
        ViewController.setGameView();
    }
}
