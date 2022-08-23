package com.codecool.dungeoncrawl.logic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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

}

