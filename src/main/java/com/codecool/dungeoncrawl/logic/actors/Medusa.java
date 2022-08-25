package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medusa extends Monster {

    private final float chanceOfFreeze = 0.10f;

    public Medusa(Cell cell) {
        super(cell);
        super.setHealth(250);
        super.setDamage(30);
        super.setAbilityPower(20);
        super.setBlockPower(50);
        super.setExp(500);
    }

    @Override
    public String getTileName() {
        return "medusa";
    }
}
