package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Shield extends Item{

    public Shield(Cell cell) {
        super(cell, "Shield", "Wooden Shield", 100);
    }


    @Override
    public String getTileName() {
        return "shield";
    }
}
