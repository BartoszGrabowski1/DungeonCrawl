package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Gloves extends Item {

    public Gloves(Cell cell) {super(cell, "Gloves", "Steel gloves", 100);}

    @Override
    public String getTileName() {
        return "gloves";
    }
}
