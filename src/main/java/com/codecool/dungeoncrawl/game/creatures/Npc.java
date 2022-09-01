package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.Drawable;

public class Npc extends Creature {

    public Npc(Cell cell) {
        super(cell);
    }

    @Override
    public boolean checkIfMovePossible(int x, int y) {
        return false;
    }

    @Override
    public String getTileName() {
        return "npc";
    }
}
