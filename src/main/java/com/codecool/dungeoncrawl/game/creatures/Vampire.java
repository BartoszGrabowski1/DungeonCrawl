package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.map.GameMap;

import java.util.Random;

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
    @Override
    public void specialAbility(GameMap map) {


    }

}
