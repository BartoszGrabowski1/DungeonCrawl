package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class SkeletonSkull extends Item {
    public SkeletonSkull(Cell cell) {
        super(cell, "Skull", "Skeleton Bone", 50);
    }

    public SkeletonSkull(Cell cell,String itemName, String itemDescription, int itemValue)
    {super(cell, itemName, itemDescription, itemValue);}

    @Override
    public String getTileName() {
        return "skull";
    }
}
