package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell);
        super.setHealth(200);
        super.setDamage(5);
        super.setAbilityPower(120);
        super.setBlockPower(50);
        super.setExp(100);
    }


    @Override
    public String getTileName() {
        return "skeleton";
    }
}
