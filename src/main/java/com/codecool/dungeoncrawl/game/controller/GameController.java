package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.Items.*;
import com.codecool.dungeoncrawl.game.creatures.*;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;
import com.codecool.dungeoncrawl.game.map.MapLoader;
import com.codecool.dungeoncrawl.game.map.Tiles;
import com.codecool.dungeoncrawl.game.music.MusicPlayer;
import com.codecool.dungeoncrawl.game.music.Sounds;
import com.codecool.dungeoncrawl.game.quests.FirstQuest;
import com.codecool.dungeoncrawl.game.quests.SecondQuest;
import com.codecool.dungeoncrawl.game.utils.Utils;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static com.codecool.dungeoncrawl.Main.*;
import static com.codecool.dungeoncrawl.game.controller.ViewController.context;
import static com.codecool.dungeoncrawl.game.music.MusicPlayer.*;
import static com.codecool.dungeoncrawl.game.quests.FirstQuest.questStr;
import static com.codecool.dungeoncrawl.game.quests.SecondQuest.result;

public class GameController {

    public static Player player;
    public static Npc npc;
    private static boolean isMapCreated = false;
    public static boolean isMusicPlaying = false;
    public static boolean isNpcAvailable = false;
    public static Timeline monstersMoving;
    public boolean isInventoryVisible = false;
    public static boolean isMapLoaded = false;
    GameDatabaseManager databaseManager = new GameDatabaseManager();

    @FXML
    public Canvas mainView;

    @FXML
    private ImageView pickUpItemBtn;

    @FXML
    private Label playersNameLabel;

    @FXML
    private Button showInventoryBtn;
    @FXML
    private Button itemSword;
    @FXML
    private Button itemArmor;
    @FXML
    private Button itemHelmet;
    @FXML
    private Label levelLabel;
    @FXML
    private Button itemShield;
    @FXML
    private Button itemShoes;
    @FXML
    private Button eqHelmet1;
    @FXML
    private Button eqSword1;
    @FXML
    private Button eqArmor1;
    @FXML
    private Button eqShoes1;
    @FXML
    private Button eqShield1;
    @FXML
    private Button eqSkull;
    @FXML
    private TableColumn<Item, String> itemDescription;

    @FXML
    private TableColumn<Item, String> itemName;

    @FXML
    private TableColumn<Item, Integer> itemValue;

    @FXML
    private TableView tableView;

    @FXML
    private ImageView saveButton;
    
    @FXML
    private GridPane equipment;

    @FXML
    private TextArea output;

    @FXML
    private TextField input;

    @FXML
    private ImageView actionBtn;

    @FXML
    private ProgressBar manaBar;
    @FXML
    private Label apLabel;

    @FXML
    private Label bpLabel;

    @FXML
    private Label damageLabel;

    @FXML
    private ProgressBar experienceBar;

    @FXML
    private ProgressBar healthBar;

    @FXML
    void saveGame(MouseEvent event) throws SQLException {
        databaseManager.setup();
        input.setVisible(true);
        if (databaseManager.getLoadNames().contains(map.getPlayer().getName())) {
            output.appendText("Such save already exists, would you like to overwrite it? (y/n)");
            input.setOnAction(e -> {
                String inputText = input.getText();
                if (Objects.equals(inputText, "y")) {
                    PlayerModel playerModel = new PlayerModel(player, player.getName());
                    playerModel.setId(databaseManager.getSelectedPlayer(player.getName()).getId());
                    databaseManager.updatePlayer(playerModel);
                    GameState gameState = databaseManager.getGameState(playerModel.getId());
                    gameState.setPlayer(playerModel);
                    databaseManager.updateGameState(gameState);
                    databaseManager.deleteAllUsersItems(playerModel.getId());
                    databaseManager.addAllItemsFromInventory(playerModel, player.getInventory());
                    databaseManager.addAllItemsFromEquipment(playerModel, player.getEquipment());
                    output.clear();
                    askForGameActionAfterSave();
                }
            });
        } else {
            databaseManager.saveAll(map.getPlayer(), map.getPlayer().getName(), map.getPlayer().getInventory(), map.getPlayer().getEquipment());
            askForGameActionAfterSave();
        }
    }

    public void askForGameActionAfterSave() {
        input.clear();
        output.appendText("You've successfully saved the game!  ");
        output.appendText("Would you like to continue or quit game? (play/quit)");
        input.setVisible(true);
        input.setOnAction(e -> {
            String inputText = input.getText();
            if (Objects.equals(inputText, "quit")) {
                MusicPlayer.stopSounds();
                ViewController.closeView();
            }
            input.clear();
            output.clear();
            input.setVisible(false);
        });
    }

    private void initButton(ImageView button, String buttonImage, String buttonImageHover) {
        button.setImage(new Image(Main.class.getResourceAsStream(buttonImage)));
        button.setOnMouseEntered(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImageHover))));
        button.setOnMouseExited(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImage))));
    }

    private void initButtons() {
        initButton(saveButton, "/com/codecool/dungeoncrawl/img/button_save.png", "/com/codecool/dungeoncrawl/img/button_save_hover.png");
        initButton(pickUpItemBtn, "/com/codecool/dungeoncrawl/img/button_pickup.png", "/com/codecool/dungeoncrawl/img/button_pickup_hover.png");
    }

    @FXML
    void initialize() {
        initButtons();

        // if map isn't created, generate new one.
        if (!isMapCreated) initMap();

        context = mainView.getGraphicsContext2D();

        // TODO: extract or move to fxml
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        itemValue.setCellValueFactory(new PropertyValueFactory<>("itemValue"));
        tableView.setPlaceholder(new Label("No items found yet"));

        handleItems();
        handleInventory();

        moveMonsters();

        if (!isMusicPlaying) playSounds();
        checkInventoryAndEquipmentAfterSave();
        updateGameView(pickUpItemBtn, context);
    }

    /**
     * Play Sounds
     * <p></p>
     * Plays all sounds in the game.
     *
     * @see MusicPlayer
     * @see Sounds
     */
    private static void playSounds() {
        if (!map.getMonsters().isEmpty()) {
            animation = new Timeline(new KeyFrame(Duration.seconds(10.0), e -> playRandomMonsterSounds()));
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
     *
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
            if (player.getCell().getItem() instanceof Sword) {
                itemSword.setVisible(true);
            } else if (player.getCell().getItem() instanceof Armor) {
                itemArmor.setVisible(true);
            } else if (player.getCell().getItem() instanceof Helmet) {
                itemHelmet.setVisible(true);
            } else if (player.getCell().getItem() instanceof Shoes) {
                itemShoes.setVisible(true);
            } else if (player.getCell().getItem() instanceof SkeletonSkull){
                eqSkull.setVisible(true);
            }
            map.getPlayer().pickUpItem();
            List<Item> playerInventory = map.getPlayer().getInventory();
            tableView.getItems().add(playerInventory.get(eqNumber));
            eqNumber++;
            updateGameView(pickUpItemBtn, context);
        });

        hidePickUpButton(pickUpItemBtn);
    }

    private void checkInventoryAndEquipmentAfterSave() {
        List<Item> inventory = player.getInventory();
        List<Item> equipment = player.getEquipment();
        for (Item item : inventory) {
            if (item instanceof Sword) {
                itemSword.setVisible(true);
            } else if (item instanceof Armor) {
                itemArmor.setVisible(true);
            } else if (item instanceof Helmet) {
                itemHelmet.setVisible(true);
            } else if (item instanceof Shoes) {
                itemShoes.setVisible(true);
            } else if (item instanceof Shield) {
                itemShield.setVisible(true);
            } else if (item instanceof SkeletonSkull) {
                eqSkull.setVisible(true);
            }
        }

        for (Item item : equipment) {
            if (item instanceof Sword) {
                eqSword1.setVisible(true);
            } else if (item instanceof Armor) {
                eqArmor1.setVisible(true);
            } else if (item instanceof Helmet) {
                eqHelmet1.setVisible(true);
            } else if (item instanceof Shoes) {
                eqShoes1.setVisible(true);
            } else if (item instanceof SkeletonSkull) {
                eqSkull.setVisible(true);
            }
        }
    }

    private EventHandler<MouseEvent> equipItem(Button itemInInventory, Button itemToEquip, String item, Sounds itemSound) {
        return (e) -> {
            itemInInventory.setVisible(false);
            itemToEquip.setVisible(true);
            player.addItemToInventoryFromEQ(item);
            playSound(itemSound.getFile(), (float) 1);
        };
    }

    private EventHandler<MouseEvent> takeOffItem(Button itemToTakeOff, Button itemInInventory, String item, Sounds itemSound) {
        return (e) -> {
            itemToTakeOff.setVisible(false);
            itemInInventory.setVisible(true);
            player.removeItem(item);
            playSound(itemSound.getFile(), (float) 1);
        };
    }

    private void handleInventory() {
        tableView.setVisible(false);
        itemSword.setVisible(false);
        itemArmor.setVisible(false);
        itemHelmet.setVisible(false);
        itemShoes.setVisible(false);
        equipment.setVisible(false);
        eqHelmet1.setVisible(false);
        eqSword1.setVisible(false);
        eqArmor1.setVisible(false);
        eqShoes1.setVisible(false);
        eqSkull.setVisible(false);
        eqShield1.setVisible(false);
        showInventoryBtn.setFocusTraversable(false);
        showInventoryBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (isInventoryVisible == false) {
                equipment.setVisible(true);
                isInventoryVisible = true;
            } else if (isInventoryVisible == true) {
                tableView.setVisible(false);
                equipment.setVisible(false);
                isInventoryVisible = false;
            }
        });
        itemSword.addEventFilter(MouseEvent.MOUSE_CLICKED, takeOffItem(itemSword, eqSword1, "sword", Sounds.EQUIP_SWORD));
        itemArmor.addEventFilter(MouseEvent.MOUSE_CLICKED, takeOffItem(itemArmor, eqArmor1, "Armor", Sounds.EQUIP_ARMOR));
        itemHelmet.addEventFilter(MouseEvent.MOUSE_CLICKED, takeOffItem(itemHelmet, eqHelmet1, "Helmet", Sounds.EQUIP_ARMOR));
        itemShoes.addEventFilter(MouseEvent.MOUSE_CLICKED, takeOffItem(itemShoes, eqShoes1, "Shoes", Sounds.EQUIP_ARMOR));
        eqHelmet1.addEventFilter(MouseEvent.MOUSE_CLICKED, equipItem(eqHelmet1, itemHelmet, "Helmet", Sounds.EQUIP_ARMOR));
        eqSword1.addEventFilter(MouseEvent.MOUSE_CLICKED, equipItem(eqSword1, itemSword, "sword", Sounds.EQUIP_SWORD));
        eqArmor1.addEventFilter(MouseEvent.MOUSE_CLICKED, equipItem(eqArmor1, itemArmor, "Armor", Sounds.EQUIP_ARMOR));
        eqShoes1.addEventFilter(MouseEvent.MOUSE_CLICKED, equipItem(eqShoes1, itemShoes, "Shoes", Sounds.EQUIP_ARMOR));
    }

    /**
     * Initialize Map
     * <p></p>
     * Generates new map for specific level.
     */
    private static void initMap() {
        if (level > LEVELS_AMOUNT) {
            map = MapLoader.loadMap(true, false);
        } else if (SecondQuest.isQuestLevel) {
            map = MapLoader.loadMap(false, true);
        } else {
            map = MapLoader.loadMap(false, false);
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
     *
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
                npcInteraction();
                break;
            case ESCAPE:
                stopSounds();
                stopAllMonstersMoving();
                ViewController.setOptionsView();
                break;
            case E:
                //Skeleton.attacked = true;
                updateGameView(pickUpItemBtn, context);
                break;
            default:
                break;
        }
        //Skeleton.attacked = false;
    }

    private void npcInteraction() {
        if (isNpcAvailable && !FirstQuest.isFirstMissionFinished) {
            FirstQuest.firstMissionAccess(output, input);
        } else if (isNpcAvailable && !SecondQuest.isSecondMissionFinished) {
            SecondQuest.secondMissionAccess(output, input);
            SecondQuest.secondQuestCrud(output);
        } else {
            input.setVisible(false);
        }
    }

    /**
     * Update Game View
     * <p></p>
     * Updates <i>(refreshes)</i> main game display.
     *
     * @param pickUpItemBtn Button used to pickup items.
     * @param context       Graphics Context from main view.
     */
    private void updateGameView(ImageView pickUpItemBtn, GraphicsContext context) {
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
                        // if player go on pentagram with quest access
                        if (cell.getType() == CellType.PENTAGRAM && SecondQuest.isSecondMissionOn && cell.getCreature() instanceof Player) {
                            initMap();
                        }
                        //if player step on blood, show action button and change flag
                         /*SecondQuest.isPlayerOnBlood = cell.getType() == CellType.BLOOD_6 ||
                                cell.getType() == CellType.BLOOD_7 ||
                                cell.getType() == CellType.BLOOD_8 ||
                                cell.getType() == CellType.BLOOD_9;

                          */
                        pentagramEscape(cell);
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
        checkForLvlChange();
        checkForFight();
        // display player main statistics
        showPlayerStats();
    }

    private void pentagramEscape(Cell cell) {
        if (cell.getType() == CellType.BLOOD_6) {
            checkForBlood('W');
        } else if (cell.getType() == CellType.BLOOD_7) {
            checkForBlood('N');
        } else if (cell.getType() == CellType.BLOOD_8) {
            checkForBlood('E');
        } else if (cell.getType() == CellType.BLOOD_9) {
            checkForBlood('S');
        }
    }

    /**
     * Show Player Stats
     * <p></p>
     * Displays actual player character statistics <i>(health, mana, experience, damage, ability power and block power).</i>
     *
     * @see Player
     * @see Creature
     * @see Item
     */
    private void showPlayerStats() {
        playersNameLabel.setText(map.getPlayer().getName());
        levelLabel.setText(String.valueOf(map.getPlayer().calculateLevel()));
        healthBar.setProgress((double) (map.getPlayer().getHealth()) / 6000);
        manaBar.setProgress((double) (map.getPlayer().getMana()) / 100);
        experienceBar.setProgress((double) (map.getPlayer().getExp()) / 10000);
        damageLabel.setText("" + map.getPlayer().getDamage());
        bpLabel.setText("" + map.getPlayer().getBlockPower());
        apLabel.setText("" + map.getPlayer().getAbilityPower());
        if (SecondQuest.isSecondMissionFinished) {
            Image img = new Image("com/codecool/dungeoncrawl/img/sword_upgrade.jpg");
            ImageView view = new ImageView(img);
            itemSword.setGraphic(view);
        }
    }

    /**
     * Hide PickUp Button
     * <p></p>
     * Hides the item pickup button.
     *
     * @param pickUpItemBtn Button for picking items up.
     */

    private static void showPickUpButton(ImageView pickUpItemBtn) {
        pickUpItemBtn.setVisible(true);
    }

    private static void hidePickUpButton(ImageView pickUpItemBtn) {
        pickUpItemBtn.setVisible(false);
    }


    /**
     * Check For Item
     * <p></p>
     * Checks if the player stepped on item and shows/hides pickup item button.
     *
     * @param pickUpItemBtn Button for picking items up.
     * @param playerOnItem  True if player stepped on item.
     */
    private static void checkForItem(ImageView pickUpItemBtn, boolean playerOnItem) {
        if (playerOnItem) {
            showPickUpButton(pickUpItemBtn);
        } else {
            hidePickUpButton(pickUpItemBtn);
        }
    }

    private void checkForBlood(char bloodPosition) {
        if (result.length() < 4 && !result.contains(Character.toString(bloodPosition))){
            result += bloodPosition;
        } else if (result.equals(questStr)) {
            finishBloodLvl();
        } else if (result.length() == 4) {
            wrongResultBloodLvl();
        }
    }

    private void wrongResultBloodLvl() {
        player.setHealth(player.getHealth() - 100);
        result = "";
        if (player.getHealth() <= 0){
            playRandomDeathSound();
            ViewController.setEndView();
        }
    }

    private void finishBloodLvl() {
        map = MapLoader.loadMap(false, false);
        SecondQuest.isBloodLvlFinished = true;
        SecondQuest.isQuestLevel = false;
    }

    /**
     * Check For Stairs
     * <p></p>
     * Check if the player came down the stairs. If yes, then go to the next level.
     */
    private static void checkForLvlChange() {
        if (map.getPlayer().getCell().getType().equals(CellType.STAIRS)) {
            map.getPlayer().getCell().setCreature(null);
            level++;
            initMap();
            if (level > LEVELS_AMOUNT) {
                playSound(bossSound, (float) 0.3);
            }
        }
        else if (map.getPlayer().getCell().getType().equals(CellType.PENTAGRAM) && SecondQuest.isQuestLevel) {
            map.getPlayer().getCell().setCreature(null);
            initMap();
        }
    }

    /**
     * Move Monsters
     * <p></p>
     * Randomly moves monsters on the map.
     *
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
     *
     * @see Creature
     * @see Monster
     * @see GameMap
     * @see Cell
     */
    private void updateMonstersPosition() {
        List<Monster> monsters = map.getMonsters();

        for (Monster monster : monsters) {
            monster.monsterMovement(map);
            monster.setAttacked(false);
        }
        updateGameView(pickUpItemBtn, mainView.getGraphicsContext2D());
    }

    /**
     * Check For Fight
     * <p></p>
     * Checks if player can fight the monster. If yes, then start the fight.
     *
     * @see FightController
     * @see Player
     * @see Monster
     */
    private void checkForFight() {
        if (FightController.isFightAvailable) {

        }
    }

    /**
     * Get Fight
     * <p></p>
     * Get into fight mode.
     *
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
        stopSounds();
        stopAllMonstersMoving();
        getFight();
        FightController.isFightAvailable = false;
    }

    public static void stopAllMonstersMoving() {
        monstersMoving.stop();
    }

}