package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class FinalBoss extends Actor {

    public FinalBoss(Cell cell){
        super(cell);
        super.setHealth(200);
        super.setDamage(80);
        super.setExp(5000);
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        return false;
    }

    @Override
    public String getTileName() {
        return "boss";
    }
}
