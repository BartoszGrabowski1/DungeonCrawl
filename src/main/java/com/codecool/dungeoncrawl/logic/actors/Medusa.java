package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medusa extends Monsters {

    private final float chanceOfFreeze = 0.10f;
    public Medusa(Cell cell){
        super(cell);
        super.setHealth(30);
        super.setDamage(15);
        super.setExp(500);
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        return false;
    }

    @Override
    public String getTileName() {
        return "medusa";
    }
}
