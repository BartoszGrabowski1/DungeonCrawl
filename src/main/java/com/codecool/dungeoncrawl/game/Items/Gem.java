package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Gem extends Item {
    public Gem() {
        super( "Gem", "Ten gem jest jakis tajemniczy xD");
    }
    public Gem(String itemName, String itemDescription)
    {super( itemName, itemDescription);}

    public String getTileName() {
        return "key";
    }
}
