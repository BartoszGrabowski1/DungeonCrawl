package com.codecool.dungeoncrawl.game.creatures;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.GameMap;

public class FinalBoss extends Monster {

    public FinalBoss(Cell cell) {
        super(cell);
        super.setHealth(200);
        super.setDamage(80);
        super.setAbilityPower(120);
        super.setBlockPower(150);
        super.setExp(5000);
    }

    @Override
    public void monsterMovement(GameMap map) {
        // boss have no move
    }

    @Override
    public String getTileName() {
        return "boss";
    }
}
