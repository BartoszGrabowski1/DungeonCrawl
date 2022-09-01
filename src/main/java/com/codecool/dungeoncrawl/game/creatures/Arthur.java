package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;

public class Arthur extends Npc {

    public Arthur(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "arthur";
    }
}
