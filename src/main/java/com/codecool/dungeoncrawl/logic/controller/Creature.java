package com.codecool.dungeoncrawl.logic.controller;

class Creature {
    int hp;
    int attackPower;
    int abilityPower;
    int blockPower;

    public Creature(int hp, int attackPower, int abilityPower, int blockPower) {
        this.hp = hp;
        this.attackPower = attackPower;
        this.abilityPower = abilityPower;
        this.blockPower = blockPower;
    }

    public int calcDamage(Action action) {
        switch (action) {
            case ATTACK:
                return attackPower;
            case ABILITY:
                return abilityPower;
            case BLOCK:
                return blockPower;
        }

        return 0;
    }

}
