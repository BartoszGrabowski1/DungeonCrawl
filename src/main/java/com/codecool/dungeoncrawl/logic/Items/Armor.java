package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Armor extends Item {
    public Armor(Cell cell) {
        super(cell, "Armor", "Iron Chest", 997);
    }


    @Override
    public String getTileName() {
        return "armor";
    }
}
