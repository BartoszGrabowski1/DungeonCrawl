package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.Items.*;
import com.codecool.dungeoncrawl.game.controller.FightController;
import com.codecool.dungeoncrawl.game.controller.GameController;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.music.MusicPlayer;
import com.codecool.dungeoncrawl.game.music.Sounds;
import com.codecool.dungeoncrawl.game.quests.FirstQuest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codecool.dungeoncrawl.Main.map;
import static com.codecool.dungeoncrawl.game.music.MusicPlayer.playSound;
import static com.codecool.dungeoncrawl.game.music.MusicPlayer.stepSound;


public class Player extends Creature {
    private List<Item> inventory = new ArrayList<>();

    private List<Item> equipment = new ArrayList<>();

    private String[] developersNames = new String[]{"BARTEK", "DAREK", "MATEUSZ", "SYLWESTER", "KAROL"};

    private String name;

    public String getName() {
        return name;
    }
    public void addToInventory(Item item) { inventory.add(item);}

    public Player(Cell cell, String name) {
        super(cell);
        super.setHealth(6000);
        super.setDamage(130);
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
            MusicPlayer.playSound(Sounds.EQUIP_SWORD.getFile(), (float) 1);
        } else if (this.getCell().getItem() instanceof Armor armor) {
            this.setHealth(this.getHealth() + armor.getItemValue());
            MusicPlayer.playSound(Sounds.EQUIP_ARMOR.getFile(), (float) 1);
        } else if (this.getCell().getItem() instanceof Key) {
            map.getCell(13, 14).setType(CellType.OPEN_DOORS);
            MusicPlayer.playSound(Sounds.PICKUP_KEY.getFile(), (float) 1);
            MusicPlayer.playSound(Sounds.OPEN_DOORS.getFile(), (float) 1);
        }
        this.getCell().setItem(null);
    }

    public void removeItem(String item){
        for (Item ite : inventory){
            if (ite.getItemName() == item){
                equipment.add(ite);
                inventory.remove(ite);
                break;
            }
        }
    }

    public void addItemToInventoryFromEQ(String item){
        for (Item ite : equipment){
            if (ite.getItemName() == item){
                inventory.add(ite);
            }
        }
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
            GameController.isNpcAvailable = true;
            GameController.npc = (Npc) this.getCell().getNeighbor(x,y).getCreature();
            return false;
        }
        return true;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String getTileName() {
        boolean armor = false;
        boolean sword = false;
        boolean helmet = false;
        boolean shield = false;
        for (Item item : getInventory()) {
            if (item instanceof Armor) armor = true;
            if (item instanceof Sword) sword = true;
            if (item instanceof Helmet) helmet = true;
            if (item instanceof Shield) shield = true;
        }

        if (armor && sword && helmet && shield) return "player_armor_helmet_shield_sword";
        if (armor && sword && helmet) return "player_armor_helmet_sword";
        if (armor && sword && shield) return "player_armor_shield_sword";
        if (armor && helmet && shield) return "player_armor_helmet_shield";
        if (sword && helmet && shield) return "player_helmet_shield_sword";
        if (sword && helmet) return "player_helmet_sword";
        if (sword && shield) return "player_sword_shield";
        if (sword && armor) return "player_armor_sword";
        if (shield && armor) return "player_armor_shield";
        if (helmet && armor) return "player_armor_helmet";
        if (helmet && shield) return "player_helmet_shield";
        if (helmet) return "player_helmet";
        if (armor) return "player_armor";
        if (shield) return "player_shield";
        if (sword) return "player_sword";
        return "player";
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        playSound(stepSound, (float) 0.8);
    }
}