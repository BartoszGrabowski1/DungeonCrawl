package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Vampire extends Monster {

    private final float drainageOfLife = 0.15f;

    public Vampire(Cell cell) {
        super(cell);
        super.setHealth(200);
        super.setDamage(10);
        super.setAbilityPower(20);
        super.setBlockPower(50);
        super.setExp(250);
    }

    @Override
    public String getTileName() {
        return "vampire";
    }

}
