package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Armor extends Item {
    public Armor(Cell cell) {
        super(cell, "Armor", "Iron Chest", 100);
    }
    public Armor(Cell cell, String itemName, String itemDescription, int itemValue) {
        super(cell, itemName, itemDescription, itemValue);
    }



    @Override
    public String getTileName() {
        return "armor";
    }
}
