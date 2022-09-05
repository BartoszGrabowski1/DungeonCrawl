package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.Items.Armor;
import com.codecool.dungeoncrawl.game.Items.Item;
import com.codecool.dungeoncrawl.game.Items.Key;
import com.codecool.dungeoncrawl.game.Items.Sword;
import com.codecool.dungeoncrawl.game.controller.FightController;
import com.codecool.dungeoncrawl.game.music.MusicPlayer;
import com.codecool.dungeoncrawl.game.quests.FirstQuest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codecool.dungeoncrawl.Main.map;
import static com.codecool.dungeoncrawl.game.music.MusicPlayer.playSound;
import static com.codecool.dungeoncrawl.game.music.MusicPlayer.stepSound;


public class Player extends Creature {
    private List<Item> inventory = new ArrayList<>();

    private String[] developersNames = new String[]{"BARTEK", "DAREK", "MATEUSZ", "SYLWESTER", "KAROL"};

    private String name;


    public Player(Cell cell, String name) {
        super(cell);
        super.setHealth(600);
        super.setDamage(30);
        super.setAbilityPower(70);
        super.setBlockPower(30);
        super.setExp(0);
        super.setMana(100);
        this.name = name.toUpperCase(Locale.ROOT);
    }

    @Override
    public int getExp() {
        return (int) (Math.sqrt(100 * (2 * super.getExp()+25)+50)/50);
    }

    public void pickUpItem() {
        inventory.add(this.getCell().getItem());
        if (this.getCell().getItem() instanceof Sword sword) {
            this.setDamage(this.getDamage() + sword.getItemValue());
            MusicPlayer.playSound("/com/codecool/dungeoncrawl/sounds/equip_sword.wav", (float) 1);
        } else if (this.getCell().getItem() instanceof Armor armor) {
            this.setHealth(this.getHealth() + armor.getItemValue());
            MusicPlayer.playSound("/com/codecool/dungeoncrawl/sounds/equip_armor.wav", (float) 1);
        } else if (this.getCell().getItem() instanceof Key) {
            map.getCell(13, 14).setType(CellType.OPEN_DOORS);
            MusicPlayer.playSound("/com/codecool/dungeoncrawl/sounds/pickup_key.wav", (float) 1);
            MusicPlayer.playSound("/com/codecool/dungeoncrawl/sounds/opened_doors.wav", (float) 1);
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
                this.getCell().getNeighbor(x, y).getType() == CellType.NPC ||
                this.getCell().getNeighbor(x, y).getType() == CellType.CLOSED_DOORS) && !checkIfDeveloper()) {

            return false;
        }
        if (this.getCell().getNeighbor(x, y).getCreature() instanceof Monster) {
            FightController.isFightAvailable = true;
            FightController.monster = (Monster) this.getCell().getNeighbor(x, y).getCreature();
            return false;
        }
        if (this.getCell().getNeighbor(x, y).getCreature() instanceof Npc) {
            FirstQuest.isFirstNpcAvailable = true;
            FirstQuest.npc = (Npc) this.getCell().getNeighbor(x,y).getCreature();
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

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        playSound(stepSound, (float) 0.8); // TODO: maybe extract?
    }
}