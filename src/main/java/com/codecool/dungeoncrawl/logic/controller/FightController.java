package com.codecool.dungeoncrawl.logic.controller;

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
            buttonAttack.setOnAction(e -> makeMove(Action.ATTACK));
            buttonBlock.setOnAction(e -> makeMove(Action.BLOCK));
            buttonAbility.setOnAction(e -> makeMove(Action.ABILITY));
        }


        private void makeMove(Action userAction) {

            Creature player = new Creature(100, 20, 50, 10);
            Creature monster = new Creature(60, 10, 10, 10);

            Action monsterAction = makeMonsterMove();

            Action.ActionResult result = userAction.checkAgainst(monsterAction);

            if (result == Action.ActionResult.DRAW) {

                output.appendText("DRAW\n");

            } else if (result == Action.ActionResult.WIN) {

                int dmg = player.calcDamage(userAction);

                monster.hp -= dmg;

                output.appendText("Player deals " + dmg + " to AI \n");

            } else { // LOSE

                int dmg = monster.calcDamage(monsterAction);

                player.hp -= dmg;

                output.appendText("Monster deals " + dmg + " to player \n");

            }
        }

        private Action makeMonsterMove() {
            return Action.values()[(int) (Math.random() * Action.values().length)];
        }

        private static Random random = new Random();



    }

