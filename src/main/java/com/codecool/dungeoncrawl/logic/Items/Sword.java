package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{

    public Sword(Cell cell) {
        super(cell,"Sword", "Sharp Sword", 112);
    }


    @Override
    public String getTileName() {
        return "sword";
    }
}
