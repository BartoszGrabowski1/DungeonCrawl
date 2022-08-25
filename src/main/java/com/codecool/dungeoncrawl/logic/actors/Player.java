package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Items.Armor;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.Items.Key;
import com.codecool.dungeoncrawl.logic.Items.Sword;
import com.codecool.dungeoncrawl.logic.controller.FightController;
import com.codecool.dungeoncrawl.logic.music.MusicPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codecool.dungeoncrawl.Main.map;


public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();

    private String[] developersNames = new String[]{"BARTEK", "DAREK", "MATEUSZ", "SYLWESTER", "KAROL"};

    private boolean developer;

    private String name;

    public Player(Cell cell, String name) {
        super(cell);
        super.setHealth(400);
        super.setDamage(30);
        super.setAbilityPower(70);
        super.setBlockPower(30);
        super.setExp(0);
        super.setMana(100);
        this.name = name.toUpperCase(Locale.ROOT);
    }

    public void pickUpItem() {
        inventory.add(this.getCell().getItem());
        if (this.getCell().getItem() instanceof Sword) {
            this.setDamage(this.getDamage() + 20);
            MusicPlayer.playSound("/equip_sword.wav", (float) 1);
        } else if (this.getCell().getItem() instanceof Armor) {
            this.setHealth(this.getHealth() + 20);
            MusicPlayer.playSound("/equip_armor.wav", (float) 1);
        } else if (this.getCell().getItem() instanceof Key) {
            map.getCell(13, 14).setType(CellType.OPEN_DOORS);
            MusicPlayer.playSound("/pickup_key.wav", (float) 1);
            MusicPlayer.playSound("/opened_doors.wav", (float) 1);
        }
        this.getCell().setItem(null);
    }

    public boolean checkIfDeveloper() {
        for (String developersName : developersNames) {
            if (this.name.equals(developersName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        if ((this.getCell().getNeighbor(x, y).getType() == CellType.WALL ||
                this.getCell().getNeighbor(x, y).getType() == CellType.WALL_2 ||
                this.getCell().getNeighbor(x, y).getType() == CellType.WALL_3 ||
                this.getCell().getNeighbor(x, y).getType() == CellType.CLOSED_DOORS) && !checkIfDeveloper()) {

            return false;
        }
        if (this.getCell().getNeighbor(x, y).getActor() instanceof Monster) {
            FightController.isFightAvailable = true;
            FightController.monster = (Monster) this.getCell().getNeighbor(x, y).getActor();
            return false;
        }
        return true;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String getTileName() {
        return "player";
    }
}