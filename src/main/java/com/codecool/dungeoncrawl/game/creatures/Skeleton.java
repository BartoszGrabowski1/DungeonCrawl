package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell);
        super.setHealth(100);
        super.setDamage(5);
        super.setAbilityPower(40);
        super.setBlockPower(50);
        super.setExp(100);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
