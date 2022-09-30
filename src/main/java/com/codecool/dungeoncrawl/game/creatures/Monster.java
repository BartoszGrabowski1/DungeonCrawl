package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.controller.FightController;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;
import com.codecool.dungeoncrawl.game.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Monster extends Creature {

    protected int specialAbilityCoolDown;
    protected boolean isAttacked;

    public Monster(Cell cell, boolean isAttacked) {
        super(cell);
        this.isAttacked = isAttacked;
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    public void setAttacked(boolean attacked) {
        isAttacked = attacked;
    }

    private final Directions[] possibleDirections = {
            Directions.NORTH, Directions.SOUTH, Directions.WEST, Directions.EAST
    };

    private enum Directions {
        NORTH, SOUTH, WEST, EAST;
    }

    private List<Directions> possibleDirectionWhenChaseAvailable;

    public Directions drawMoves() {
        if (possibleDirectionWhenChaseAvailable == null || possibleDirectionWhenChaseAvailable.isEmpty()) {
            int number = Utils.RANDOM.nextInt(4);
            return possibleDirections[number];
        }
        int number = Utils.RANDOM.nextInt(possibleDirectionWhenChaseAvailable.size());
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
            possibleDirectionWhenChaseAvailable.add(Directions.NORTH);
        } else if (yDifference > 0) {
            possibleDirectionWhenChaseAvailable.add(Directions.SOUTH);
        }
    }

    private void possibleXDirection(int xDifference) {
        if (xDifference < 0) {
            possibleDirectionWhenChaseAvailable.add(Directions.WEST);
        } else if (xDifference > 0) {
            possibleDirectionWhenChaseAvailable.add(Directions.EAST);
        }
    }
    public abstract void lootItems();
    public abstract void specialAbility(GameMap map);

    public boolean lootChance(int ratio){
        int chance = ThreadLocalRandom.current().nextInt(100);
        return chance < ratio;
    }

    public void monsterMovement(GameMap map) {
        if (specialAbilityCoolDown == 0) {
            specialAbility(map);
        }
        followThePlayer(map);
        Directions direction = drawMoves();
        switch (direction) {
            case NORTH:
                this.move(0, -1);
                break;
            case SOUTH:
                this.move(0, 1);
                break;
            case WEST:
                this.move(-1, 0);
                break;
            case EAST:
                this.move(1, 0);
                break;
        }
        specialAbilityCoolDown--;
    }
}
