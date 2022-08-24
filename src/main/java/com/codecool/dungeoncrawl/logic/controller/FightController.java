package com.codecool.dungeoncrawl.logic.controller;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Random;

public class FightController {


    @FXML
    private ComboBox<?> boxSpells;

    @FXML
    private Button buttonAttack;

    @FXML
    private Button buttonBlock;

    @FXML
    private Button buttonAbility;

    @FXML
    private Label labelFight;

    @FXML
    private Label monsterAbility;

    @FXML
    private Label monsterHealth;

    @FXML
    private Label monsterMana;

    @FXML
    private Label monsterName;

    @FXML
    private Label playerHealth;

    @FXML
    private Label playerLvl;

    @FXML
    private Label playerMana;

    @FXML
    private Label playerName;
    @FXML
    private TextArea output;






        @FXML
        void fightMode(ActionEvent event) {
            Player player = new Player(100, 50, 50, 50, 50);
            Skeleton monster = new Skeleton( 40, 40, 40, 40, 40);
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

                output.appendText("Player deals " + dmg + " to AI \n");

            } else { // LOSE

                int dmg = monster.calcDamage(monsterAction);

                player.setHealth(player.getHealth() - dmg);

                output.appendText("Monster deals " + dmg + " to player \n");

            }
        }

        private Action makeMonsterMove() {
            return Action.values()[(int) (Math.random() * Action.values().length)];
        }

        private static Random random = new Random();



    }

