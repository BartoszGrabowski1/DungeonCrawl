package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import javafx.scene.input.KeyEvent;

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


    @Override
    public boolean checkIfMovePossible(int x, int y) {
        if(this.getCell().getNeighbor(x,y).getType() == CellType.WALL){
            return false;
        }
        if(this.getCell().getNeighbor(x,y).getActor() instanceof Monsters) {
            return false;
        }
        if(this.getCell().getNeighbor(x,y).getActor() instanceof Player) {
            return false;
        }
        return true;
    }

    public void monsterMovement(GameMap map) {
        String direction = drowMoves();
        switch (direction) {
            case "NORTH":
                this.move(0, -1);
                break;
            case "SOUTH":
                this.move(0, 1);
                break;
            case "WEST":
                this.move(-1, 0);
                break;
            case "EAST":
                this.move(1, 0);
                break;
        }
    }
}
