package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Armor extends Item {
    public Armor(Cell cell) {
        super(cell, "Armor", "Iron Chest", 997);
    }


    @Override
    public String getTileName() {
        return "armor";
    }
}
