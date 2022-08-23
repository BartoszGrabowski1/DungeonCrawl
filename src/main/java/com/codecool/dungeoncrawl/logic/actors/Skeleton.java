package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
        super.setHealth(10);
        super.setDamage(5);
        super.setExp(100);
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        return false;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
