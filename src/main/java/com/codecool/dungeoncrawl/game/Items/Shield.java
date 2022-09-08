package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Shield extends Item{

    public Shield(Cell cell) {
        super(cell, "Shield", "Wooden Shield", 100);
    }

    public Shield(Cell cell,String itemName, String itemDescription, int itemValue)
    {super(cell, itemName, itemDescription, itemValue);}
    @Override
    public String getTileName() {
        return "shield";
    }
}
