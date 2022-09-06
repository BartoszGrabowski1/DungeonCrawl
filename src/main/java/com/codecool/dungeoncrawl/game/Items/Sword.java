package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Sword extends Item {

    public Sword(Cell cell) {
        super(cell, "Sword", "Sharp Sword", 100);
    }


    @Override
    public String getTileName() {
        return "sword";
    }
}
