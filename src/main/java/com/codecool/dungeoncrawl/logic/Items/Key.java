package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item {
    public Key(Cell cell) {
        super(cell);
    }


    @Override
    public String getTileName() {
        return "key";
    }
}
