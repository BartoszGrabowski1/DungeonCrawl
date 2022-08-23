package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medusa extends Actor {

    private final float chanceOfFreeze = 0.10f;
    public Medusa(Cell cell){
        super(cell);
        super.setHealth(30);
        super.setDamage(15);
        super.setExp(500);
    }

    @Override
    public String getTileName() {
        return "medusa";
    }
}
