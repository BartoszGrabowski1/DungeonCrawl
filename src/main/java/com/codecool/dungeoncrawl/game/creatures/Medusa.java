package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.map.GameMap;

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
    public void specialAbility(GameMap map) {

    }

    @Override
    public String getTileName() {
        return "medusa";
    }
}
