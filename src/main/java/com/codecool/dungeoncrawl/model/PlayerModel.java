package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.game.creatures.Player;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int x;
    private int y;

    private int damage;

    private int abilityPower;

    private int blockPower;

    private int experience;

    private int mana;

    private String savedGameName;

    public PlayerModel(String playerName, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }

    public PlayerModel(Player player,String savedGameName) {
        this.savedGameName = savedGameName;
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();
        this.damage = player.getDamage();
        this.abilityPower = player.getAbilityPower();
        this.blockPower = player.getBlockPower();
        this.experience = player.getExp();
        this.mana = player.getMana();

    }
    public PlayerModel(String savedGameName){
        this.playerName = savedGameName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDamage() {
        return damage;
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

    public int getBlockPower() {
        return blockPower;
    }

    public void setBlockPower(int blockPower) {
        this.blockPower = blockPower;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
