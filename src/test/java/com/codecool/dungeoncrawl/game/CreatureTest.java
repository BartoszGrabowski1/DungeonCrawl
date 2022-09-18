package com.codecool.dungeoncrawl.game;

import com.codecool.dungeoncrawl.game.controller.FightAction;
import com.codecool.dungeoncrawl.game.creatures.Player;
import com.codecool.dungeoncrawl.game.creatures.Skeleton;
import com.codecool.dungeoncrawl.game.creatures.Vampire;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);

    @Test
    void moveUpdatesCells() {
        Player player = new Player(gameMap.getCell(1, 1),"Dev");
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals(null, gameMap.getCell(1, 1).getCreature());
        assertEquals(player, gameMap.getCell(2, 1).getCreature());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(gameMap.getCell(1, 1),"Dev");
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }


    @Test
    void cannotMoveIntoAnotherActor() {
        Player player = new Player(gameMap.getCell(1, 1),"Dev");
        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1),1);
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(2, skeleton.getX());
        assertEquals(1, skeleton.getY());
        assertEquals(skeleton, gameMap.getCell(2, 1).getCreature());
    }

    @Test
    void calculateDamageReturnProperFieldBasedOnProvidedAction(){
        Player player = new Player(gameMap.getCell(1,1),"DEX");
        player.setDamage(10);
        player.setAbilityPower(20);
        player.setBlockPower(30);
        assertEquals(player.getDamage(), player.calcDamage(FightAction.ATTACK));
        assertEquals(player.getAbilityPower(), player.calcDamage(FightAction.ABILITY));
        assertEquals(player.getBlockPower(), player.calcDamage(FightAction.BLOCK));
        assertEquals(0, player.calcDamage(FightAction.SPECIAL));
    }
    @Test
    void getExpReturnsExperienceOfCreature(){
        Player player = new Player(gameMap.getCell(2,2),"Dex");
        player.setExp(10);
        assertEquals(10, player.getExp());
        assertNotEquals(120,player.getExp());
    }

    @Test
    void getCreatureReturnsCreatureStandingOnSpecifiedCell(){
        Player player = new Player(gameMap.getCell(1,1),"Dex");
        Vampire vampire = new Vampire(gameMap.getCell(2,2),1);
        Skeleton skeleton = new Skeleton(gameMap.getCell(0,0),1);
        assertEquals(skeleton,gameMap.getCell(0,0).getCreature());
        assertEquals(vampire,gameMap.getCell(2,2).getCreature());
        assertEquals(player,gameMap.getCell(1,1).getCreature());
    }
}