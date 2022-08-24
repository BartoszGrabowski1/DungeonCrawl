package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item {
    public Key(Cell cell) {
        super(cell, "Key", "Key to your mom house", 69);
    }


    @Override
    public String getTileName() {
        return "key";
    }
}
