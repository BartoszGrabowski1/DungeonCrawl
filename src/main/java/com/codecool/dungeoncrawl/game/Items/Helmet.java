package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Helmet extends Item{

    public Helmet(Cell cell) {super(cell, "Helmet", "Steel Helmet", 100);}

    @Override
    public String getTileName() {
        return "helmet";
    }
}
