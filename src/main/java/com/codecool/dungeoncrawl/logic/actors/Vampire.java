package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Vampire extends Monsters{

    private final float drainageOfLife = 0.15f;
    public Vampire(Cell cell){
        super(cell);
        super.setHealth(20);
        super.setDamage(10);
        super.setExp(250);
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        return false;
    }

    @Override
    public String getTileName() {
        return "vampire";
    }

}
