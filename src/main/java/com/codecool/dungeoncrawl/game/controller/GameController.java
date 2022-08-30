package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.game.map.Tiles;
import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.Items.Item;
import com.codecool.dungeoncrawl.game.map.MapLoader;
import com.codecool.dungeoncrawl.game.creatures.Monster;
import com.codecool.dungeoncrawl.game.creatures.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;

import static com.codecool.dungeoncrawl.Main.*;
import static com.codecool.dungeoncrawl.game.controller.ViewController.context;
import static com.codecool.dungeoncrawl.game.music.MusicPlayer.*;

public class GameController {

    public static Player player;

    private static boolean isMapCreated = false;

    @FXML
    public Canvas mainView;

    @FXML
    private Label healthLabel;

    @FXML
    private Button pickUpItemBtn;

    @FXML
    private TableColumn<Item, String> itemDescription;

    @FXML
    private TableColumn<Item, String> itemName;

    @FXML
    private TableColumn<Item, Integer> itemValue;

    @FXML
    private TableView tableView;

    @FXML
    public void initialize() {
        if (!isMapCreated) initMap();

        context = mainView.getGraphicsContext2D();

        // TODO: extract or move to fxml
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        itemValue.setCellValueFactory(new PropertyValueFactory<>("itemValue"));

        tableView.setPlaceholder(new Label("No items found yet"));

        handleItems();

        moveMonsters();

        playSounds();

        updateGameView(pickUpItemBtn, context);
    }

    private static void playSounds() {
        if (!map.getMonsters().isEmpty()) {
            animation = new Timeline(new KeyFrame(Duration.seconds(10.0), e -> playRandomMonsterSounds(monsterSounds)));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.playFromStart();
        }
        playSound(opening, (float) 0.4);
    }

    private void handleItems() {
        pickUpItemBtn.setFocusTraversable(false);
        pickUpItemBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            map.getPlayer().pickUpItem();
            List<Item> playerInventory = map.getPlayer().getInventory();
            tableView.getItems().add(playerInventory.get(eqNumber));
            eqNumber++;
            updateGameView(pickUpItemBtn, context);
        });

        hideButton(pickUpItemBtn);
    }

    private static void initMap() {
        if (level > LEVELS_AMOUNT) {
            map = MapLoader.loadMap(true);
        } else {
            map = MapLoader.loadMap(false);
        }
//        bossLevel = MapLoader.loadMap(true);
//        for (int i = 0; i < LEVELS_AMOUNT; i++) {
//            levels[i] = MapLoader.loadMap(false);
//        }
//        map = levels[level - 1];
        isMapCreated = true;
    }


    public void setupKeys() {
        Main.scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
            case UP:
                map.getPlayer().move(0, -1);
                updateGameView(pickUpItemBtn, context);
                break;
            case S:
            case DOWN:
                map.getPlayer().move(0, 1);
                updateGameView(pickUpItemBtn, context);
                break;
            case A:
            case LEFT:
                map.getPlayer().move(-1, 0);
                updateGameView(pickUpItemBtn, context);
                break;
            case D:
            case RIGHT:
                map.getPlayer().move(1, 0);
                updateGameView(pickUpItemBtn, context);
                break;
            default:
                break;
        }
    }

    public void updateGameView(Button pickUpItemBtn, GraphicsContext context) {
        boolean playerOnItem = false;

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, mainView.getWidth(), mainView.getHeight());
        for (int x = -SCREEN_SIZE; x < SCREEN_SIZE; x++) {
            for (int y = -SCREEN_SIZE; y < SCREEN_SIZE; y++) {
                try {
                    Cell cell = map.getCell(map.getPlayer().getX() + x - (SCREEN_SIZE / 2), map.getPlayer().getY() + y - (SCREEN_SIZE / 2));
                    if (cell.getCreature() != null) {
                        Tiles.drawTile(context, cell.getCreature(), x, y);
                        if (cell.getItem() != null && cell.getCreature() instanceof Player) {
                            playerOnItem = true;
                        }
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                } catch (Exception e) {
                    Tiles.drawTile(context, new Cell(map, x, y, CellType.EMPTY), x, y);
                }
            }
        }

        checkForItem(pickUpItemBtn, playerOnItem);
        checkForStairs();
        checkForFight();

        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    public static void hideButton(Button pickUpItemBtn) {
        pickUpItemBtn.setVisible(false);
    }

    public static void showButton(Button pickUpItemBtn) {
        pickUpItemBtn.setVisible(true);
    }

    private static void checkForItem(Button pickUpItemBtn, boolean playerOnItem) {
        if (playerOnItem) {
            showButton(pickUpItemBtn);
        } else {
            hideButton(pickUpItemBtn);
        }
    }

    private static void checkForStairs() {
        if (map.getPlayer().getCell().getType().equals(CellType.STAIRS)) {
            level++;
            initMap();
            if (level > LEVELS_AMOUNT) {
                playSound(bossSound, (float) 0.3);
            }
//            if (level > LEVELS_AMOUNT) {
//                map = bossLevel;
//                playSound(bossSound, (float) 0.3);
//            } else {
//                map = levels[level - 1];
//            }
        }
    }

    private void moveMonsters() {
        animation = new Timeline(new KeyFrame(Duration.seconds(1.0), e -> updateMonstersPosition()));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.playFromStart();
    }

    private void updateMonstersPosition() {
        List<Monster> monsters = map.getMonsters();

        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).monsterMovement(map);
        }
        updateGameView(pickUpItemBtn, mainView.getGraphicsContext2D());
    }

    private void checkForFight() {
        if (FightController.isFightAvailable) {
            startFight();
        }
    }

    public void getFight() {
        ViewController.setFightView();
    }

    private void startFight() {
        animation.stop();
        FightController.player = map.getPlayer();
        getFight();
        FightController.isFightAvailable = false;
    }

}