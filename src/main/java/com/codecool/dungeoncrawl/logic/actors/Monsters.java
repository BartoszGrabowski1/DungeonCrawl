package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public abstract class Monsters extends Actor {


    public Monsters(Cell cell) {
        super(cell);
    }

    private String[] possibleDirections = new String[] {"NORTH","SOUTH","WEST","EAST"};

    public String drowMoves(){
        Random random = new Random();
        int number = random.nextInt(4);
        return possibleDirections[number];
    }


}
