package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell);
        super.setHealth(10);
        super.setDamage(5);
        super.setAbilityPower(120);
        super.setBlockPower(50);
        super.setExp(100);
    }

    public Skeleton(int health, int exp, int damage, int abilityPower, int blockPower) {
        super(health, exp, damage, abilityPower, blockPower);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
