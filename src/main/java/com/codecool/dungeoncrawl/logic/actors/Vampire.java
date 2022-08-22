package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Vampire extends Actor{
    public Vampire(Cell cell){
        super(cell);
        super.setHealth(20);
        super.setDamage(10);
        super.setExp(250);
    }

    @Override
    public String getTileName() {
        return "vampire";
    }

}
