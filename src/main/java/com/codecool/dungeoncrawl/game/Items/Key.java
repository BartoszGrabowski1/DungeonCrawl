package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Key extends Item {
    public Key(Cell cell) {
        super(cell, "Key", "Key to your mom house", 69);
    }

    public Key(Cell cell,String itemName, String itemDescription, int itemValue)
    {super(cell, itemName, itemDescription, itemValue);}

    @Override
    public String getTileName() {
        return "key";
    }
}
