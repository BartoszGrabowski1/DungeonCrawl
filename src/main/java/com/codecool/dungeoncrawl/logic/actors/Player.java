package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        if(this.getCell().getNeighbor(x,y).getType() == CellType.WALL){
            return false;
        }
        if(this.getCell().getNeighbor(x,y).getActor() instanceof Monster)
        {
            return false;
        }
        return true;
    }

    public String getTileName() {
        return "player";
    }
}
