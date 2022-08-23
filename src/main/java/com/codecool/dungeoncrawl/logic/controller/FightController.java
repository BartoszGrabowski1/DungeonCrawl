package com.codecool.dungeoncrawl.logic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class FightController {

    @FXML
    private ComboBox<?> boxSpells;

    @FXML
    private Button buttonAttack;

    @FXML
    private Button buttonBlock;

    @FXML
    private Button buttonCharge;

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
    void test(ActionEvent event) {
        System.out.println("dupa");
    }


    class Creature {
        int hp;
        int attackPower;
        int mana;
        int blockPower;

        public Creature(int hp, int attackPower, int mana, int blockPower) {
            this.hp = hp;
            this.attackPower = attackPower;
            this.mana = mana;
            this.blockPower = blockPower;
        }


        Creature player = new Creature(100,20,50,10);
        Creature monster = new Creature(60,10,10,10);

    }


    private enum Action {
        ATTACK, CHARGE, BLOCK;

        private static final Map<Action, Action> winMap = new HashMap<>();

        static {
            winMap.put(ATTACK, CHARGE);
            winMap.put(CHARGE, BLOCK);
            winMap.put(BLOCK, ATTACK);
        }

        ActionResult checkAgainst(Action action) {
            if (this == action)
                return ActionResult.DRAW;

            return winMap.get(this) == action ? ActionResult.WIN : ActionResult.LOSE;
        }

        private enum ActionResult {
            WIN, LOSE, DRAW
        }
    }
}

