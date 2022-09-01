package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Monster extends Creature {

    protected int specialAbilityCoolDown;

    public Monster(Cell cell) {
        super(cell);
    }

    private final String[] possibleDirections = new String[]{"NORTH", "SOUTH", "WEST", "EAST"};

    private List<String> possibleDirectionWhenChaseAvailable;

    public String drawMoves() {
        Random random = new Random();
        if (possibleDirectionWhenChaseAvailable == null || possibleDirectionWhenChaseAvailable.isEmpty()) {
            int number = random.nextInt(4);
            return possibleDirections[number];
        }
        int number = random.nextInt(possibleDirectionWhenChaseAvailable.size());
        return possibleDirectionWhenChaseAvailable.get(number);
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        if (this.getCell().getNeighbor(x, y).getCreature() instanceof Player) {
            return false;
        } else if (this.getCell().getNeighbor(x, y).getCreature() instanceof Monster) {
            return false;
        } else if (this.getCell().getNeighbor(x, y).getType() == CellType.WALL ||
                this.getCell().getNeighbor(x, y).getType() == CellType.WALL_2 ||
                this.getCell().getNeighbor(x, y).getType() == CellType.NPC ||
                this.getCell().getNeighbor(x, y).getType() == CellType.WALL_3 ||
                this.getCell().getNeighbor(x, y).getType() == CellType.CLOSED_DOORS ||
                this.getCell().getNeighbor(x, y).getType() == CellType.STAIRS) {
            return false;
        }
        return true;
    }

    public void followThePlayer(GameMap map) {
        Player player = map.getPlayer();
        int xCordDifference = player.getX() - this.getX();
        int yCordDifference = player.getY() - this.getY();
        if ((xCordDifference < 5 && xCordDifference > -5) && (yCordDifference < 5 && yCordDifference > -5)) {
            possibleDirectionsWhenChaseIsAvailable(xCordDifference, yCordDifference);
        }

    }

    public void possibleDirectionsWhenChaseIsAvailable(int xDifference, int yDifference) {
        possibleDirectionWhenChaseAvailable = new ArrayList<>();
        possibleXDirection(xDifference);
        possibleYDirection(yDifference);

    }

    private void possibleYDirection(int yDifference) {
        if (yDifference < 0) {
            possibleDirectionWhenChaseAvailable.add("NORTH");
        } else if (yDifference > 0) {
            possibleDirectionWhenChaseAvailable.add("SOUTH");
        }
    }

    private void possibleXDirection(int xDifference) {
        if (xDifference < 0) {
            possibleDirectionWhenChaseAvailable.add("WEST");
        } else if (xDifference > 0) {
            possibleDirectionWhenChaseAvailable.add("EAST");
        }
    }

    public abstract void specialAbility(GameMap map);

    public void monsterMovement(GameMap map) {
        if (specialAbilityCoolDown == 0) {
            specialAbility(map);
        }
        followThePlayer(map);
        String direction = drawMoves();
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
        specialAbilityCoolDown--;
    }
}
