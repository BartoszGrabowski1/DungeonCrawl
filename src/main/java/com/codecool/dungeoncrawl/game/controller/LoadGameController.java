package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.game.Items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML
    void initialize() throws SQLException {
        selectButton.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_select.png")));
        selectButton.setOnMouseEntered(t -> selectButton.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_select_hover.png"))));
        selectButton.setOnMouseExited(t -> selectButton.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_select.png"))));

        mainMenuButton.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_menu.png")));
        mainMenuButton.setOnMouseEntered(t -> mainMenuButton.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_menu_hover.png"))));
        mainMenuButton.setOnMouseExited(t -> mainMenuButton.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_menu.png"))));


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
