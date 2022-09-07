package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;

public class Crudy extends Npc{
    public Crudy(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "crudy";
    }
}
