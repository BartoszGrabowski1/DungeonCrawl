package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Shoes extends Item {

    public Shoes(Cell cell) {
        super(cell, "Shoes", "Leather shoes", 100);
    }


    @Override
    public String getTileName() {
        return "shoes";
    }
}
