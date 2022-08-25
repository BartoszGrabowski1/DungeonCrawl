package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.controller.Action;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health = 10;
    private int exp;
    private int damage;
    private int abilityPower;
    private int blockPower;

    public int getExp() {
        return exp;
    }

    public int getDamage() {
        return damage;
    }

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public Actor getActor() {
        return this.cell.getActor();
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (checkIfMovePossible(dx, dy)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int calcDamage(Action action) {
        switch (action) {
            case ATTACK:
                return damage;
            case ABILITY:
                return abilityPower;
            case BLOCK:
                return blockPower;
        }

        return 0;
    }


    public int getBlockPower() {
        return blockPower;
    }

    public void setBlockPower(int blockPower) {
        this.blockPower = blockPower;
    }

    public int getHealth() {
        return health;
    }

    public abstract boolean checkIfMovePossible(int x, int y);

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(int abilityPower) {
        this.abilityPower = abilityPower;
    }
}
