package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Medusa extends Actor {

    public Medusa(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "medusa";
    }
}
