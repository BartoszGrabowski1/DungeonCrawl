package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.map.GameMap;

public class Medusa extends Monster {

    private final float chanceOfFreeze = 0.10f;

    public Medusa(Cell cell, int level) {
        super(cell, false);
        super.setHealth((int) (700 * (1 + 0.25*level)));
        super.setDamage((int) (30 * (1 + 0.25*level)));
        super.setAbilityPower((int) (20 * (1 + 0.25*level)));
        super.setBlockPower((int) (50 * (1 + 0.25*level)));
        super.setExp((int) (500 * (1 + 0.25*level)));
    }

    @Override
    public void lootItems() {

    }

    @Override
    public void specialAbility(GameMap map) {

    }

    @Override
    public String getTileName() {
        return "medusa";
    }
}