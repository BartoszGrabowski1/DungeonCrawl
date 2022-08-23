package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        return false;
    }

    @Override
    public String getTileName() {
        System.out.println("szkielet korzysta");
        return "skeleton";
    }
}
