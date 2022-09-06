package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Helmet extends Item {


    public Helmet(Cell cell, String itemName, String itemDescription, int itemValue) {
        super(cell, "Helmet", "For empty head", 100);
    }

    @Override
    public String getTileName() {
        return "helmet";
    }
}
