package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Items.Item;
import java.util.ArrayList;
import java.util.List;



public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();

    private String[] developersNames = new String[]{"BARTEK", "DAREK", "MATEUSZ", "SYLWESTER", "KAROL"};

    private boolean isDeveloper;

    public Player(int health, int exp, int damage, int abilityPower, int blockPower) {
        super(health, exp, damage, abilityPower, blockPower);
    }


    public Player(Cell cell) {
        super(cell);
        super.setHealth(400);
        super.setDamage(100);
        super.setAbilityPower(150);
        super.setBlockPower(100);
        super.setExp(0);
    }

    public void pickUpItem(){
        inventory.add(this.getCell().getItem());
        this.getCell().setItem(null);
        for (Item i : inventory){
            System.out.print(i.getTileName() + " ");
        }

    }

    public void setDeveloper() {
        for(String developersName : developersNames) {
            if (NameController.userName.toUpperCase(Locale.ROOT).equals(developersName)) {
                isDeveloper = true;
                return;
            }
        }
        isDeveloper = false;
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        if(this.getCell().getNeighbor(x,y).getType() == CellType.WALL){
            return false;
        }
        if(this.getCell().getNeighbor(x,y).getActor() instanceof Monster)
        {
            FightController.isFightAvailable = true;
            FightController.monster = (Monster) this.getCell().getNeighbor(x,y).getActor();
            return false;
        }
        return true;
    }

    public String getTileName() {
        return "player";
    }
}
