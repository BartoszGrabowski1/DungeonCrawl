package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.Items.SkeletonSkull;
import com.codecool.dungeoncrawl.game.map.GameMap;

public class Skeleton extends Monster {
    public Skeleton(Cell cell, int level) {
        super(cell);
        super.setHealth((int) (100 * (1 + 0.25*level)));
        super.setDamage((int) (5 * (1 + 0.25*level)));
        super.setAbilityPower((int) (40 * (1 + 0.25*level)));
        super.setBlockPower((int) (50 * (1 + 0.25*level)));
        super.setExp((int) (100 * (1 + 0.25*level)));
    }

    @Override
    public void lootItems() {
        if (lootChance(100)) {
            this.getCreature().getCell().setItem(new SkeletonSkull(this.getCell()));
        }
    }

    @Override
    public void specialAbility(GameMap map) {

    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}