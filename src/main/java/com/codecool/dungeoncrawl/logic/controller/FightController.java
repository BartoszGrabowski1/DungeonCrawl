package com.codecool.dungeoncrawl.logic.controller;

import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.Random;

public class FightController {


    @FXML
    private ComboBox<?> boxSpells;

    @FXML
    private Button buttonAbility;

    @FXML
    private Button buttonAttack;

    @FXML
    private Button buttonBlock;

    @FXML
    private Label labelFight;

    @FXML
    private Label monsterDamage;

    @FXML
    private Label monsterHealth;

    @FXML
    private Label monsterLvl;

    @FXML
    private Label monsterAbility;

    @FXML
    private Label monsterName;

    @FXML
    private TextArea output;

    @FXML
    private Label playerAbility;

    @FXML
    private Label playerDamage;

    @FXML
    private Label playerHealth;

    @FXML
    private Label playerLvl;

    @FXML
    private Label playerMana;

    @FXML
    private Label playerName;

    public static Player player;
    public static Monster monster;
    public static boolean isFightAvailable = false;


    public void initialize() {
        playerName.setText(NameController.userName);
        playerHealth.setText("HP: " + player.getHealth());
        playerAbility.setText("ABILITY: " + player.getAbilityPower());
        playerLvl.setText("EXP: " + player.getExp());
        playerDamage.setText("DAMAGE: " + player.getDamage());

        monsterName.setText(monster.getTileName());
        monsterHealth.setText("HP: " + monster.getHealth());
        monsterDamage.setText("DAMAGE: " + monster.getDamage());
        monsterLvl.setText("EXP:" + monster.getExp());
        monsterAbility.setText("ABILITY: " + monster.getAbilityPower());

        buttonAttack.setOnAction(e -> makeMove(Action.ATTACK, player, monster));
        buttonBlock.setOnAction(e -> makeMove(Action.BLOCK, player, monster));
        buttonAbility.setOnAction(e -> makeMove(Action.ABILITY, player, monster));
    }


    private void makeMove(Action userAction, Player player, Monster monster) {
        Action monsterAction = makeMonsterMove();
        Action.ActionResult result = userAction.checkAgainst(monsterAction);
        if (result == Action.ActionResult.DRAW) {
            output.appendText("DRAW\n");
        } else if (result == Action.ActionResult.WIN) {
            int dmg = player.calcDamage(userAction);
            monster.setHealth(monster.getHealth() - dmg);
            output.appendText(String.format(("%s deals %s to %s \n"), NameController.userName, dmg, monster.getTileName()));
        } else { // LOSE
            int dmg = monster.calcDamage(monsterAction);
            player.setHealth(player.getHealth() - dmg);
            output.appendText(String.format(("%s deals %s to %s \n"), monster.getTileName(), dmg, NameController.userName));
        }
        initialize();
        if (monster.getHealth() < 0) {
            Stage stageToClose = (Stage) output.getScene().getWindow();
            stageToClose.close();
        }
    }


    private Action makeMonsterMove() {
        return Action.values()[(int) (Math.random() * Action.values().length)];
    }

    private static Random random = new Random();


}

