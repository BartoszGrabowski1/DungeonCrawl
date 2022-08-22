package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Vampire extends Actor{

    public Vampire(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "vampire";
    }

}
