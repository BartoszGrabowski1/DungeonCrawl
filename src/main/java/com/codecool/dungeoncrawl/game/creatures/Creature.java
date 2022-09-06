package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.Drawable;
import com.codecool.dungeoncrawl.game.controller.FightAction;

public abstract class Creature implements Drawable {
    protected Cell cell;
    private int health;

    private int exp;

    private int damage;

    private int mana;

    private int abilityPower;

    private int blockPower;

    public int getExp() {
        return exp;
    }

    public int getDamage() {
        return damage;
    }

    public Creature(Cell cell) {
        this.cell = cell;
        this.cell.setCreature(this);
    }

    public Creature getCreature() {
        return this.cell.getCreature();
    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);
        if (checkIfMovePossible(dx, dy)){
            cell.setCreature(null);
            nextCell.setCreature(this);
            cell = nextCell;
        }
    }

    public int calcDamage(FightAction action) {
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

    public void setCell(Cell cell) {
        this.cell = cell;
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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(int abilityPower) {
        this.abilityPower = abilityPower;
    }
}