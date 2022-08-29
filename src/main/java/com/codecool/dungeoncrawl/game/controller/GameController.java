package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.CellType;
import com.codecool.dungeoncrawl.game.Items.Item;
import com.codecool.dungeoncrawl.game.creatures.Monster;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

import static com.codecool.dungeoncrawl.Main.*;
import static com.codecool.dungeoncrawl.game.controller.ViewController.context;
import static com.codecool.dungeoncrawl.game.music.MusicPlayer.playSound;
import static com.codecool.dungeoncrawl.game.music.MusicPlayer.stepSound;

public class GameController {

    @FXML
    public Canvas ccanvas;
    //    @FXML
//    private Canvas canvas = new Canvas(640, 640);
    @FXML
    private BorderPane borderpane = new BorderPane();


    @FXML
    private Label healthLabel;

    public Label getHealthLabel() {
        return healthLabel;
    }

    @FXML
    private Button pickUpItemBtn;

    public Button getPickUpItemBtn() {
        return pickUpItemBtn;
    }

    @FXML
    private TableColumn<Item, String> itemDescription;

    @FXML
    private TableColumn<Item, String> itemName;

    @FXML
    private TableColumn<Item, Integer> itemValue;

    @FXML
    private TableView tableView;

    public Canvas getCanvas() {
        return ccanvas;
    }

    public BorderPane getBorderpane() {
        return borderpane;
    }

    @FXML
    public void initialize() {

        context = getCanvas().getGraphicsContext2D();

        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        itemValue.setCellValueFactory(new PropertyValueFactory<>("itemValue"));

        tableView.setPlaceholder(new Label("No items found yet"));

        pickUpItemBtn.setFocusTraversable(false);
        pickUpItemBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            map.getPlayer().pickUpItem();
            List<Item> playerInventory = map.getPlayer().getInventory();
            tableView.getItems().add(playerInventory.get(eqNumber));
            eqNumber++;
            refresh(pickUpItemBtn, context);
        });

        hideButton(pickUpItemBtn);

        animation = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> incrementLabel()));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.playFromStart();
//        if (!map.getMonsters().isEmpty()) {
//            animation = new Timeline(new KeyFrame(Duration.seconds(10.0), e -> playRandomMonsterSounds(monsterSounds)));
//            animation.setCycleCount(Animation.INDEFINITE);
//            animation.playFromStart();
//        }

//        getCanvas().getScene().setOnKeyPressed(this::onKeyPressed);
        refresh(pickUpItemBtn, context);
    }

    public void setupKeys() {
        Main.scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        //wywolanie okna game over
//        if (FightController.isGameOver) {
//            gameOver();
//        }
        playSound(stepSound, (float) 0.1);
        switch (keyEvent.getCode()) {
            case W:
            case UP:
                map.getPlayer().move(0, -1);
                refresh(pickUpItemBtn, context);
                break;
            case S:
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh(pickUpItemBtn, context);
                break;
            case A:
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh(pickUpItemBtn, context);
                break;
            case D:
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh(pickUpItemBtn, context);
                break;
//            case R:
//                getFight();
            default:
                break;
        }
//        if (FightController.isFightAvailable) {
//            startFight();
//        }
    }

    public void refresh(Button pickUpItemBtn, GraphicsContext context) {
//        boolean playerOnItem = false;
//        checkTile();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
        for (int x = -SCREEN_SIZE; x < SCREEN_SIZE; x++) {
            for (int y = -SCREEN_SIZE; y < SCREEN_SIZE; y++) {
                try {
                    Cell cell = map.getCell(map.getPlayer().getX() + x - (SCREEN_SIZE / 2), map.getPlayer().getY() + y - (SCREEN_SIZE / 2));
                    if (cell.getCreature() != null) {
                        Tiles.drawTile(context, cell.getCreature(), x, y);
//                        if (cell.getItem() != null && cell.getActor() instanceof Player) {
//                            playerOnItem = true;
//                        }
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
//        if (playerOnItem) {
//            showButton(pickUpItemBtn);
//        } else {
//            hideButton(pickUpItemBtn);
//        }
//
//        gc.getHealthLabel().setText("" + map.getPlayer().getHealth());
//        animation.play();
    }

    private void incrementLabel() {
        List<Monster> monsters = map.getMonsters();

        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).monsterMovement(map);
        }
        refresh(pickUpItemBtn, ccanvas.getGraphicsContext2D());
    }

    public void getFight() {
        try {
//            Stage stage = new Stage();
            Stage stage = (Stage) ccanvas.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fight-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("FIGHT MODE");
            stage.setScene(scene);
//            stage.alwaysOnTopProperty();  // niepotrtzebne bo jest to juz w pierwszym tworzeniu okna
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
//            Stage stageToClose = (Stage) ccanvas.getScene().getWindow();  // nic nie zamykamy działamy w jednym oknie
//            stageToClose.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void startFight() {
        animation.stop();
        FightController.player = map.getPlayer();
        getFight();
        FightController.isFightAvailable = false;
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