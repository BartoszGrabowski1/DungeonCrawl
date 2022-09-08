package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.quests.SecondQuest;

public class Sword extends Item {

    public Sword(Cell cell) {
        super(cell, "sword", "Sharp Sword", 100);
    }

    public Sword(Cell cell,String itemName, String itemDescription, int itemValue)
    {super(cell, itemName, itemDescription, itemValue);}
    @Override
    public String getTileName() {
        if (SecondQuest.isSecondMissionFinished){
            return "enhancedSword";
        } else {
            return "sword";
        }
    }
}
