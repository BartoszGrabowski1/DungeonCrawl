package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{

    public Sword(Cell cell) {
        super(cell);
    }


    @Override
    public String getTileName() {
        System.out.println("czemu ten pierdolony miecz sie nei wyswietla");
        return "sword";
    }
}
