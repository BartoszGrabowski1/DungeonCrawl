package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.game.creatures.*;
import com.codecool.dungeoncrawl.game.map.GameMap;
import com.codecool.dungeoncrawl.game.music.Sounds;
import com.codecool.dungeoncrawl.game.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import static com.codecool.dungeoncrawl.game.music.MusicPlayer.*;

public class FightController {

    @FXML
    private ImageView buttonAbility;

    @FXML
    private ImageView buttonAttack;

    @FXML
    private ImageView buttonBlock;

    @FXML
    private ImageView buttonHeal;

    @FXML
    private VBox fightWindow;

    @FXML
    private Label monsterHealth;

    @FXML
    private TextArea output;

    @FXML
    private Label playerHealth;

    public static Player player;
    public static Monster monster;
    public static boolean isFightAvailable = false;

    @FXML
    void initialize() {

        initEnemyGraphics();

        initButtons();
        initBattleButtons();

        updateStats();
    }

    public void updateStats() {
        updatePlayerStats();
        updateMonsterStats();
        if (player.getMana() >= 40){
            buttonAbility.setOnMouseClicked(e -> makeMove(FightAction.ABILITY, player, monster));
        } else {
            buttonAbility.setOnMouseClicked(e -> output.appendText("You dont have enough mana \n"));
        }
    }

    private void updateMonsterStats() {
        monsterHealth.setText(monster.getTileName() + " HP: " + monster.getHealth());
    }

    private void updatePlayerStats() {
        playerHealth.setText(NameController.userName + " HP: " + player.getHealth());
    }

    private void initButton(ImageView button, String buttonImage, String buttonImageHover) {
        button.setImage(new Image(Main.class.getResourceAsStream(buttonImage)));
        button.setOnMouseEntered(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImageHover))));
        button.setOnMouseExited(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImage))));
    }

    private void initButtons() {
        initButton(buttonAttack, "/com/codecool/dungeoncrawl/img/atk-btn.png", "/com/codecool/dungeoncrawl/img/atk-btn-hover.png");
        initButton(buttonBlock, "/com/codecool/dungeoncrawl/img/block-btn.png", "/com/codecool/dungeoncrawl/img/block-btn-hover.png");
        initButton(buttonAbility, "/com/codecool/dungeoncrawl/img/ability-btn.png", "/com/codecool/dungeoncrawl/img/ability-btn-hover.png");
        initButton(buttonHeal, "/com/codecool/dungeoncrawl/img/heal-btn.png", "/com/codecool/dungeoncrawl/img/heal-btn-hover.png");

    }

    private void initBattleButtons() {
        buttonAttack.setOnMouseClicked(e -> makeMove(FightAction.ATTACK, player, monster));
        buttonBlock.setOnMouseClicked(e -> makeMove(FightAction.BLOCK, player, monster));
        buttonHeal.setOnMouseClicked(e -> makeMove(FightAction.SPECIAL, player, monster));
    }

    private void setupFightViewBackgroundGraphic(String s) {
        fightWindow.setBackground(new Background(new BackgroundImage(new Image(s),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    }

    private void initEnemyGraphics() {
        if (monster instanceof Vampire) {
            setupFightViewBackgroundGraphic("/com/codecool/dungeoncrawl/img/fight-view-vampire.jpg");
        } else if (monster instanceof Medusa) {
            setupFightViewBackgroundGraphic("/com/codecool/dungeoncrawl/img/fight-view-medusa.jpg");
        } else if (monster instanceof Skeleton) {
            setupFightViewBackgroundGraphic("/com/codecool/dungeoncrawl/img/fight-view-skeleton.jpg");
        } else if (monster instanceof FinalBoss) {
            setupFightViewBackgroundGraphic("/com/codecool/dungeoncrawl/img/fight-view-boss.jpg");
        } else {
            setupFightViewBackgroundGraphic("/com/codecool/dungeoncrawl/img/fight-view.jpg");
        }
    }

    private void makeMove(FightAction userAction, Player player, Monster monster) {
        FightAction monsterAction = makeMonsterMove();
        FightAction.ActionResult result = userAction.checkAgainst(monsterAction);
        fightTurn(userAction, player, monster, monsterAction, result);
        checkBattleResult(player, monster);
        updateStats();
    }

    private void fightTurn(FightAction userAction, Player player, Monster monster, FightAction monsterAction, FightAction.ActionResult result) {
        if (result == FightAction.ActionResult.DRAW) {
            output.appendText("DRAW\n");
            playRandomBlockSound();
        } else if (result == FightAction.ActionResult.WIN) {
            dealDamageToMonster(userAction, player, monster);
            playRandomHitSound();
        } else { // LOSE
            dealDamageToPlayer(player, monster, monsterAction);
            playRandomPlayerHittedSound();
        }
        regenerateMana(player);
    }

    private static void regenerateMana(Player player) {
        if (player.getMana() < 100){
            player.setMana(player.getMana() + 10);
        }
    }

    private void dealDamageToPlayer(Player player, Monster monster, FightAction monsterAction) {
        int dmg = monster.calcDamage(monsterAction);
        player.setHealth(player.getHealth() - dmg);
        output.appendText(String.format(("%s deals %s to %s \n"), monster.getTileName(), dmg, NameController.userName));
    }

    private void dealDamageToMonster(FightAction userAction, Player player, Monster monster) {
        int dmg = player.calcDamage(userAction);
        if (userAction == FightAction.ABILITY) {
            player.setMana(player.getMana() - 40);
            monster.setHealth(monster.getHealth() - dmg);
        } else if (userAction == FightAction.SPECIAL) {
            player.setHealth(player.getHealth() + 100);
        } else if (userAction == FightAction.ATTACK || userAction == FightAction.BLOCK) {
            monster.setHealth(monster.getHealth() - dmg);
            output.appendText(String.format(("%s deals %s to %s \n"), NameController.userName, dmg, monster.getTileName()));
        }
    }

    private void checkBattleResult(Player player, Monster monster) {
        if (monster.getHealth() <= 0) {
            playerWin(monster);
        } else if (player.getHealth() <= 0){
            monsterWin();
        }
    }

    private void playerWin(Monster monster) {
        if (monster instanceof Medusa) playRandomMedusaDeathSound();
        else if (monster instanceof Vampire) playRandomVampireDeathSound();
        else if (monster instanceof Skeleton) playRandomSkeletonDeathSound();
        else if (monster instanceof FinalBoss) playSound(Sounds.BOSS_DEATH.getFile(), (float) 1.0);
        player.setExp(player.getExp() + monster.getExp());
        monster.lootItems();
        monster.getCreature().getCell().setCreature(null);
        GameMap.removeMonster(monster);
        ViewController.setGameView();
    }

    private void monsterWin() {
        playRandomDeathSound();
        ViewController.setEndView();
    }

    private FightAction makeMonsterMove() {
        return FightAction.values()[(int) (Utils.RANDOM.nextInt(0, FightAction.values().length))];
    }

}
