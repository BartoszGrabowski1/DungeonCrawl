package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.controller.GameController;
import javafx.scene.control.TextArea;

public class Arthur extends Npc {

    public Arthur(Cell cell) {
        super(cell);
    }

    @Override
    public void npcTalk() {
        System.out.println("Arthur");
    }

    @Override
    public String getTileName() {
        return "arthur";
    }
}
