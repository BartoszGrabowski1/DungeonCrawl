package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;

import java.util.Random;

public abstract class Monster extends Creature {

    public Monster(Cell cell) {
        super(cell);
    }

    private String[] possibleDirections = new String[]{"NORTH", "SOUTH", "WEST", "EAST"};

    public String drowMoves() {
        Random random = new Random();
        int number = random.nextInt(4);
        return possibleDirections[number];
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        if (this.getCell().getNeighbor(x, y).getType() == CellType.WALL ||
                this.getCell().getNeighbor(x, y).getType() == CellType.WALL_2 ||
                this.getCell().getNeighbor(x, y).getType() == CellType.WALL_3 ||
                this.getCell().getNeighbor(x, y).getType() == CellType.CLOSED_DOORS ||
                this.getCell().getNeighbor(x, y).getType() == CellType.STAIRS) {
            return false;
        }
        if (this.getCell().getNeighbor(x, y).getCreature() instanceof Monster) {
            return false;
        }
        if (this.getCell().getNeighbor(x, y).getCreature() instanceof Player) {
            return false; // TODO: monster ai
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
