package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.Items.SkeletonSkull;
import com.codecool.dungeoncrawl.game.map.GameMap;

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
    public void lootItems() {
        if (lootChance(50)) {
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
