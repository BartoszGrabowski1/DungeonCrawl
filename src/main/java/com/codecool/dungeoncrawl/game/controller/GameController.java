package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.game.map.Tiles;
import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;
import com.codecool.dungeoncrawl.game.Items.Item;
import com.codecool.dungeoncrawl.game.map.MapLoader;
import com.codecool.dungeoncrawl.game.creatures.Monster;
import com.codecool.dungeoncrawl.game.creatures.Player;
import com.codecool.dungeoncrawl.game.quests.FirstQuest;
import com.codecool.dungeoncrawl.game.creatures.Creature;
import com.codecool.dungeoncrawl.game.music.Sounds;
import com.codecool.dungeoncrawl.game.music.MusicPlayer;
import com.codecool.dungeoncrawl.game.utils.Utils;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
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
    public static boolean isMusicPlaying = false;
    public static Timeline monstersMoving;

    @FXML
    public Canvas mainView;

    @FXML
    private Label healthLabel;
    @FXML
    private Label manaLabel;
    @FXML
    private Label expLabel;
    @FXML
    private Label dmgLabel;
    @FXML
    private Label apLabel;
    @FXML
    private Label blockLabel;
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
    private TextArea output;

    @FXML
    private TextField input;


    @FXML
    void initialize() {
        // if map isn't created, generate new one.
        if (!isMapCreated) initMap();

        context = mainView.getGraphicsContext2D();

        // TODO: extract or move to fxml
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        itemValue.setCellValueFactory(new PropertyValueFactory<>("itemValue"));

        tableView.setPlaceholder(new Label("No items found yet"));

        handleItems();

        moveMonsters();

        if (!isMusicPlaying) playSounds();

        updateGameView(pickUpItemBtn, context);
    }

    /**
     * Play Sounds
     * <p></p>
     * Plays all sounds in the game.
     * @see MusicPlayer
     * @see Sounds
     */
    private static void playSounds() {
        if (!map.getMonsters().isEmpty()) {
            animation = new Timeline(new KeyFrame(Duration.seconds(10.0), e -> playRandomMonsterSounds(monsterSounds)));
            animation.setCycleCount(Animation.INDEFINITE);
            animation.playFromStart();
            monstersSounds = animation;
        }
        playMainSound();
    }

    /**
     * Play Main Sound
     * <p></p>
     * Plays main game sound
     * @see MusicPlayer
     * @see Sounds
     */
    private static void playMainSound() {
        playSound(opening, (float) 0.4);

        isMusicPlaying = true;

        // main sound loop
        playMainClipDelay = Utils.setTimeout(GameController::playMainSound, Sounds.MAIN.getLengthInMilliseconds());
    }

    /**
     * Handle Items
     * <p></p>
     * Handles item events <i>(picking up, boost player statistics based on item value(if sword or armor)).</i>
     * Also removes the item from map after pick.
     */
    private void handleItems() {
        pickUpItemBtn.setFocusTraversable(false);
        pickUpItemBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            map.getPlayer().pickUpItem();
            List<Item> playerInventory = map.getPlayer().getInventory();
            tableView.getItems().add(playerInventory.get(eqNumber));
            eqNumber++;
            updateGameView(pickUpItemBtn, context);
        });

        hidePickUpButton(pickUpItemBtn);
    }

    /**
     * Initialize Map
     * <p></p>
     * Generates new map for specific level.
     */
    private static void initMap() {
        if (level > LEVELS_AMOUNT) {
            map = MapLoader.loadMap(true);
        } else {
            map = MapLoader.loadMap(false);
        }
        isMapCreated = true;
    }

    /**
     * Setup Keys
     * <p></p>
     * Allows user to move player using keyboard keys <i>(W,A,S,D)</i>.
     */
    public void setupKeys() {
        Main.scene.setOnKeyPressed(this::onKeyPressed);
    }

    /**
     * On Key Pressed
     * <p></p>
     * Move player when specific key <i>(W,A,S,D)</i> is pressed.
     * @param keyEvent Keyboard key event.
     */
    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                map.getPlayer().move(0, -1);
                updateGameView(pickUpItemBtn, context);
                break;
            case S:
                map.getPlayer().move(0, 1);
                updateGameView(pickUpItemBtn, context);
                break;
            case A:
                map.getPlayer().move(-1, 0);
                updateGameView(pickUpItemBtn, context);
                break;
            case D:
                map.getPlayer().move(1, 0);
                updateGameView(pickUpItemBtn, context);
                break;
            case R:
                if(!FirstQuest.isFirstMissionFinished) {
                    FirstQuest.firstMissionAccess(output, input);
                }
            default:
                break;
        }
    }

    /**
     * Update Game View
     * <p></p>
     * Updates <i>(refreshes)</i> main game display.
     * @param pickUpItemBtn Button used to pickup items.
     * @param context Graphics Context from main view.
     */
    private void updateGameView(Button pickUpItemBtn, GraphicsContext context) {
        boolean playerOnItem = false;

        // fill main game display
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, mainView.getWidth(), mainView.getHeight());

        // Cuts the full map into smaller size based on the player location, also centers the "camera" above player.
        for (int x = -SCREEN_SIZE; x < SCREEN_SIZE; x++) {
            for (int y = -SCREEN_SIZE; y < SCREEN_SIZE; y++) {
                try {
                    Cell cell = map.getCell(map.getPlayer().getX() + x - (SCREEN_SIZE / 2), map.getPlayer().getY() + y - (SCREEN_SIZE / 2));
                    if (cell.getCreature() != null) {

                        // draw creature on map
                        Tiles.drawTile(context, cell.getCreature(), x, y);

                        // if player steps on item, show the pickup item button.
                        if (cell.getItem() != null && cell.getCreature() instanceof Player) {
                            playerOnItem = true;
                        }
                    } else if (cell.getItem() != null) {

                        // draw item on map
                        Tiles.drawTile(context, cell.getItem(), x, y);
                    } else {

                        // draw any other cell on map
                        Tiles.drawTile(context, cell, x, y);
                    }
                } catch (Exception e) {

                    // draw empty cells outside the map
                    Tiles.drawTile(context, new Cell(map, x, y, CellType.EMPTY), x, y);
                }
            }
        }

        // check if player steps on specific tiles
        checkForItem(pickUpItemBtn, playerOnItem);
        checkForStairs();
        checkForFight();

        // display player main statistics
        showPlayerStats();
    }

    /**
     * Show Player Stats
     * <p></p>
     * Displays actual player character statistics <i>(health, mana, experience, damage, ability power and block power).</i>
     * @see Player
     * @see Creature
     * @see Item
     */
    private void showPlayerStats() {
        healthLabel.setText("" + map.getPlayer().getHealth());
        manaLabel.setText("" + map.getPlayer().getMana());
        expLabel.setText("" + map.getPlayer().getExp());
        dmgLabel.setText("" + map.getPlayer().getDamage());
        apLabel.setText("" + map.getPlayer().getAbilityPower());
        blockLabel.setText("" + map.getPlayer().getBlockPower());
    }

    /**
     * Hide PickUp Button
     * <p></p>
     * Hides the item pickup button.
     * @param pickUpItemBtn Button for picking items up.
     */
    private static void hidePickUpButton(Button pickUpItemBtn) {
        pickUpItemBtn.setVisible(false);
    }

    /**
     * Show PickUp Button
     * <p></p>
     * Shows the item pickup button.
     * @param pickUpItemBtn Button for picking items up.
     */
    private static void showPickUpButton(Button pickUpItemBtn) {
        pickUpItemBtn.setVisible(true);
    }

    /**
     * Check For Item
     * <p></p>
     * Checks if the player stepped on item and shows/hides pickup item button.
     * @param pickUpItemBtn Button for picking items up.
     * @param playerOnItem True if player stepped on item.
     */
    private static void checkForItem(Button pickUpItemBtn, boolean playerOnItem) {
        if (playerOnItem) {
            showPickUpButton(pickUpItemBtn);
        } else {
            hidePickUpButton(pickUpItemBtn);
        }
    }

    /**
     * Check For Stairs
     * <p></p>
     * Check if the player came down the stairs. If yes, then go to the next level.
     */
    private static void checkForStairs() {
        if (map.getPlayer().getCell().getType().equals(CellType.STAIRS)) {
            level++;
            initMap();
            if (level > LEVELS_AMOUNT) {
                playSound(bossSound, (float) 0.3);
            }
        }
    }

    /**
     * Move Monsters
     * <p></p>
     * Randomly moves monsters on the map.
     * @see Creature
     * @see Monster
     */
    private void moveMonsters() {
        animation = new Timeline(new KeyFrame(Duration.seconds(1.0), e -> updateMonstersPosition()));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.playFromStart();
        monstersMoving = animation;
    }

    /**
     * Update Monsters Position
     * <p></p>
     * Updates monsters position on the map.
     * @see Creature
     * @see Monster
     * @see GameMap
     * @see Cell
     */
    private void updateMonstersPosition() {
        List<Monster> monsters = map.getMonsters();

        for (Monster monster : monsters) {
            monster.monsterMovement(map);
        }
        updateGameView(pickUpItemBtn, mainView.getGraphicsContext2D());
    }

    /**
     * Check For Fight
     * <p></p>
     * Checks if player can fight the monster. If yes, then start the fight.
     * @see FightController
     * @see Player
     * @see Monster
     */
    private void checkForFight() {
        if (FightController.isFightAvailable) {
            startFight();
        }
    }

    /**
     * Get Fight
     * <p></p>
     * Get into fight mode.
     * @see FightController
     */
    private void getFight() {
        ViewController.setFightView();
    }

    /**
     * Start Fight
     * <p></p>
     * Freezes all monsters on the map and starts the fight.
     * @see FightController
     */
    private void startFight() {
        animation.stop();
        FightController.player = map.getPlayer();
        getFight();
        FightController.isFightAvailable = false;
    }

}