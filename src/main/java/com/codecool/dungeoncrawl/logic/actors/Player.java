package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.controller.NameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Player extends Actor {
    private List<Item> inventory = new ArrayList<>();

    private String[] developersNames = new String[]{"BARTEK", "DAREK", "MATEUSZ", "SYLWESTER", "KAROL"};

    private boolean isDeveloper;

    public Player(Cell cell) {
        super(cell);
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
        if(this.getCell().getNeighbor(x,y).getType() == CellType.WALL && !isDeveloper){
            return false;
        }
        if(this.getCell().getNeighbor(x,y).getActor() instanceof Monster)
        {
            return false;
        }
        return true;
    }

    public String getTileName() {
        return "player";
    }
}
