package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.game.CellType;
import com.codecool.dungeoncrawl.game.GameMap;
import com.codecool.dungeoncrawl.game.MapLoader;
import com.codecool.dungeoncrawl.game.controller.FightController;
import com.codecool.dungeoncrawl.game.controller.GameController;
import com.codecool.dungeoncrawl.game.controller.MenuController;
import com.codecool.dungeoncrawl.game.controller.NameController;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import static com.codecool.dungeoncrawl.game.music.MusicPlayer.*;

public class Main extends Application {

    public static final int SCREEN_SIZE = 20;
    public static final int LEVELS_AMOUNT = 3;
    public static final GameMap[] levels = new GameMap[3];
    public static GameMap bossLevel;
    public static int level = 1;
    public static int eqNumber = 0;

    private final Random random = new Random();


    public static GameMap map;
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1100, 650);
    //    Canvas canvas = new Canvas(
//            SCREEN_SIZE * Tiles.TILE_WIDTH,
//            SCREEN_SIZE * Tiles.TILE_WIDTH);
    GameController gc = fxmlLoader.getController();
//    static GameController gc = new GameController();
    GraphicsContext context = gc.getCanvas().getGraphicsContext2D();
    Label healthLabel = new Label();
    //    Button pickUpItemBtn = new Button("Pick up");
    public static Timeline animation;

    public Main() throws IOException {
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void printMenu(Stage stage) {
        try {
//            Stage stage = new Stage();
            stage.setResizable(false);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Main Menu");
            stage.alwaysOnTopProperty();
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
//            stage.showAndWait();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hideButton(Button pickUpItemBtn) {
        pickUpItemBtn.setVisible(false);
    }

    public static void showButton(Button pickUpItemBtn) {
        pickUpItemBtn.setVisible(true);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        printMenu(primaryStage);
        if (MenuController.nextWindow && NameController.startGame) {  // po zamknieciu jednego okna odpalic gre (czyli bez tego ifa)
            System.out.println("weszlo tu");
            bossLevel = MapLoader.loadMap(true);
            for (int i = 0; i < LEVELS_AMOUNT; i++) {
                levels[i] = MapLoader.loadMap(false);
            }
            map = levels[level - 1];
//            GridPane ui = new GridPane();  // do wyjebania bo jest juz w fxmlu
//            ui.setPrefWidth(200);
//            ui.setPadding(new Insets(10));


//            TableView tableView = new TableView<>();
//            TableColumn<Item, String> playerInv = new TableColumn<>("Player Inventory");
//            TableColumn<Item, String> column1 = new TableColumn<>("Item");
//            TableColumn<Item, String> column2 = new TableColumn<>("Description");
//            TableColumn<Item, Integer> column3 = new TableColumn<>("Value");
//            column1.setCellValueFactory(new PropertyValueFactory<>("itemName"));
//            column2.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
//            column3.setCellValueFactory(new PropertyValueFactory<>("itemValue"));
//            playerInv.getColumns().addAll(column1, column2, column3);
//            tableView.getColumns().add(playerInv);
//            tableView.setStyle("-fx-background-color: grey");
//            column1.setStyle("-fx-background-color: DimGray");
//            column2.setStyle("-fx-background-color: DimGray");
//            column3.setStyle("-fx-background-color: DimGray");
//            playerInv.setStyle("-fx-background-color: DimGray");
//            tableView.setPrefWidth(240);
//            tableView.setPrefHeight(180);

            // do wyjebania bo jest juz w fxmlu i w game controllerze
//            ui.add(new Label("Health: "), 0, 0);
//            ui.add(healthLabel, 1, 0);
//            pickUpItemBtn.setFocusTraversable(false);
//            ui.add(pickUpItemBtn, 1, 1);
//            pickUpItemBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
//                map.getPlayer().pickUpItem();
//                List<Item> playerInventory = map.getPlayer().getInventory();
//                tableView.getItems().add(playerInventory.get(eqNumber));
//                eqNumber++;
//                refresh();
//            });
//            pickUpItemBtn.setStyle("-fx-background-color: grey");
//            tableView.setPlaceholder(new Label("No items found yet"));
//            hideButton(gc.getPickUpItemBtn());

            /**
             * to tu na dole jest do wyjebania bo jest powyzej juz w mainie
             */
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game-view.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 1100, 650);
//            gc = fxmlLoader.getController();
//            context = gc.getCanvas().getGraphicsContext2D();

//            gc.getBorderpane().setCenter(gc.getCanvas());
//            gc.getBorderpane().setRight(ui);
//            gc.getBorderpane().setLeft(tableView);

//            animation = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> incrementLabel()));
//            animation.setCycleCount(Animation.INDEFINITE);
//            animation.playFromStart();
//            if (!map.getMonsters().isEmpty()) {
//                animation = new Timeline(new KeyFrame(Duration.seconds(10.0), e -> playRandomMonsterSounds(monsterSounds)));
//                animation.setCycleCount(Animation.INDEFINITE);
//                animation.playFromStart();
//            }
//
//            primaryStage.setScene(scene);
//            refresh(gc.getPickUpItemBtn());
//            scene.setOnKeyPressed(this::onKeyPressed);
//
//            primaryStage.setTitle("Dungeon Crawl");
//            primaryStage.show();
////            playSound(opening, (float) 0.1);
//            playSound(opening, (float) 0.4);
        }
        System.out.println("nie weszlo");
    }

    public void playRandomMonsterSounds(String[] monsterTypeSounds) {
        int soundNumber = random.nextInt(0, 10);
        playSound(monsterTypeSounds[soundNumber], (float) 1);
    }

//    private void incrementLabel() {
//        List<Monster> monsters = map.getMonsters();
//
//        for (int i = 0; i < monsters.size(); i++) {
//            monsters.get(i).monsterMovement(map);
//        }
//        refresh(gc.getPickUpItemBtn());
//    }

//    private void onKeyPressed(KeyEvent keyEvent) {
//        //wywolanie okna game over
//        if (FightController.isGameOver) {
//            gameOver();
//        }
//        playSound(stepSound, (float) 0.1);
//        switch (keyEvent.getCode()) {
//            case W:
//            case UP:
//                map.getPlayer().move(0, -1);
//                refresh(gc.getPickUpItemBtn());
//                break;
//            case S:
//            case DOWN:
//                map.getPlayer().move(0, 1);
//                refresh(gc.getPickUpItemBtn());
//                break;
//            case A:
//            case LEFT:
//                map.getPlayer().move(-1, 0);
//                refresh(gc.getPickUpItemBtn());
//                break;
//            case D:
//            case RIGHT:
//                map.getPlayer().move(1, 0);
//                refresh(gc.getPickUpItemBtn());
//                break;
//            case R:
//                gc.getFight();
//            default:
//                break;
//        }
//        if (FightController.isFightAvailable) {
//            startFight();
//        }
//    }

//    private void startFight() {
//        animation.stop();
//        FightController.player = map.getPlayer();
//        gc.getFight();
//        FightController.isFightAvailable = false;
//    }

//    public void refresh(Button pickUpItemBtn) {
//        boolean playerOnItem = false;
//        checkTile();
//        context.setFill(Color.BLACK);
//        context.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
//        for (int x = -SCREEN_SIZE; x < SCREEN_SIZE; x++) {
//            for (int y = -SCREEN_SIZE; y < SCREEN_SIZE; y++) {
//                try {
//                    Cell cell = map.getCell(map.getPlayer().getX() + x - (SCREEN_SIZE / 2), map.getPlayer().getY() + y - (SCREEN_SIZE / 2));
//                    if (cell.getActor() != null) {
//                        Tiles.drawTile(context, cell.getActor(), x, y);
//                        if (cell.getItem() != null && cell.getActor() instanceof Player) {
//                            playerOnItem = true;
//                        }
//                    } else if (cell.getItem() != null) {
//                        Tiles.drawTile(context, cell.getItem(), x, y);
//                    } else {
//                        Tiles.drawTile(context, cell, x, y);
//                    }
//                } catch (Exception e) {
//                    Tiles.drawTile(context, new Cell(map, x, y, CellType.EMPTY), x, y);
//                }
//            }
//        }
//        if (playerOnItem) {
//            showButton(pickUpItemBtn);
//        } else {
//            hideButton(pickUpItemBtn);
//        }
//
//        gc.getHealthLabel().setText("" + map.getPlayer().getHealth());
//        animation.play();
//    }

    private static void checkTile() {
        if (map.getPlayer().getCell().getType().equals(CellType.STAIRS)) {
            level++;
            if (level > LEVELS_AMOUNT) {
                map = bossLevel;
                playSound(bossSound, (float) 0.3);
            } else {
                map = levels[level - 1];
            }
        }
    }

    private void gameOver() {
        if (FightController.isGameOver) {
            Stage stageToClose = (Stage) gc.getPickUpItemBtn().getScene().getWindow();
            stageToClose.close();
            gc.gameOverView();
        }
    }
}