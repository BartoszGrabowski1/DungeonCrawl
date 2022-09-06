package com.codecool.dungeoncrawl.game.Items;

import com.codecool.dungeoncrawl.game.Cell;

public class Gem extends Item {
    public Gem() {
        super( "Gem", "Ten gem jest jakis tajemniczy xD");
    }


    public String getTileName() {
        return "key";
    }
}
