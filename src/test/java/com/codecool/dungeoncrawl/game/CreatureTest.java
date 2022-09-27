package com.codecool.dungeoncrawl.game;

import com.codecool.dungeoncrawl.game.Items.*;
import com.codecool.dungeoncrawl.game.controller.FightAction;
import com.codecool.dungeoncrawl.game.creatures.*;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;
import com.codecool.dungeoncrawl.game.map.generator.MapGeneratorImpl;
import com.codecool.dungeoncrawl.game.quests.FirstQuest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);

    @Test
    void moveUpdatesCells() {
        Player player = new Player(gameMap.getCell(1, 1), "Dev");
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals(null, gameMap.getCell(1, 1).getCreature());
        assertEquals(player, gameMap.getCell(2, 1).getCreature());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(gameMap.getCell(1, 1), "Dev");
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void monsterCannotMoveIntoNpc() {
        gameMap.getCell(2, 1).setType(CellType.NPC);
        Skeleton skeleton = new Skeleton(gameMap.getCell(1, 1), 1);
        skeleton.move(1, 0);

        assertEquals(1, skeleton.getX());
        assertEquals(1, skeleton.getY());
    }


    @Test
    void cannotMoveIntoAnotherActor() {
        Player player = new Player(gameMap.getCell(1, 1), "Dev");
        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1), 1);
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(2, skeleton.getX());
        assertEquals(1, skeleton.getY());
        assertEquals(skeleton, gameMap.getCell(2, 1).getCreature());
    }

    @Test
    void calculateDamageReturnProperFieldBasedOnProvidedAction() {
        Player player = new Player(gameMap.getCell(1, 1), "DEX");
        player.setDamage(10);
        player.setAbilityPower(20);
        player.setBlockPower(30);
        assertEquals(player.getDamage(), player.calcDamage(FightAction.ATTACK));
        assertEquals(player.getAbilityPower(), player.calcDamage(FightAction.ABILITY));
        assertEquals(player.getBlockPower(), player.calcDamage(FightAction.BLOCK));
        assertEquals(0, player.calcDamage(FightAction.SPECIAL));
    }

    @Test
    void getExpReturnsExperienceOfCreature() {
        Player player = new Player(gameMap.getCell(2, 2), "Dex");
        player.setExp(10);
        assertEquals(10, player.getExp());
        assertNotEquals(120, player.getExp());
    }

    @Test
    void getCreatureReturnsCreatureStandingOnSpecifiedCell() {
        Player player = new Player(gameMap.getCell(1, 1), "Dex");
        Vampire vampire = new Vampire(gameMap.getCell(2, 2), 1);
        Skeleton skeleton = new Skeleton(gameMap.getCell(0, 0), 1);
        Medusa medusa = new Medusa(gameMap.getCell(1, 2), 1);
        FinalBoss finalBoss = new FinalBoss(gameMap.getCell(2, 1));
        assertEquals(skeleton, gameMap.getCell(0, 0).getCreature());
        assertEquals(vampire, gameMap.getCell(2, 2).getCreature());
        assertEquals(player, gameMap.getCell(1, 1).getCreature());
        assertEquals(medusa, gameMap.getCell(1, 2).getCreature());
        assertEquals(finalBoss, gameMap.getCell(2, 1).getCreature());
    }

    @Test
    void getMapLoadedWithCreatures() {
        MapGeneratorImpl basicMap = new MapGeneratorImpl(66,66,66,66, 10, false,1, 3,1 ,1,1,1,new char[]{'1', '3', '4', '5'});
        assertEquals(1, basicMap.getSKELETONS());
        assertEquals(1, basicMap.getMEDUSAS());
        assertEquals(1, basicMap.getVAMPIRES());
        assertEquals(1, basicMap.getNPCS());

    }

    @Test
    void creatureSetCellShouldChangeCreaturesCellValues(){
        Player player = new Player(gameMap.getCell(1,1),"dev");
        player.setCell(gameMap.getCell(2,2));
        assertEquals(2, player.getX());
        assertEquals(2, player.getY());
    }

    @Test
    void getManaAndGetHealthShouldReturnCurrentManaAndHealthValues(){
        Player player = new Player(gameMap.getCell(1,1),"dev");
        assertEquals(6000, player.getHealth());
        assertEquals(100, player.getMana());
    }

    @Test
    void monsterMovementFunctionShouldChangeMonstersPosition(){
        Vampire vampire = new Vampire(gameMap.getCell(1,1),1);
        Cell startingCell = vampire.getCell();
        Player player = new Player(gameMap.getCell(0,0),"dev");
        gameMap.setPlayer(player);
        vampire.monsterMovement(gameMap);
        assertNotEquals(startingCell,vampire.getCell());
    }
    @Test
    void pickUpAndRemoveSwordShouldModifyPlayersDamageAndAlsoChangePlayersTileName(){
        Sword sword = new Sword(gameMap.getCell(0,0));
        Player player = new Player(gameMap.getCell(0,0),"dev");
        player.pickUpItem();
        assertEquals(230, player.getDamage());
        assertNotEquals(1000, player.getDamage());
        assertNotEquals(0, player.getDamage());
        assertEquals("player_sword_shield",player.getTileName());
        player.removeItem("sword");
        assertEquals(130, player.getDamage());
    }

    @Test
    void pickUpAndRemoveArmorShouldModifyPlayersHealthAndAlsoChangePlayersTileName(){
        Armor armor = new Armor(gameMap.getCell(0,0));
        Player player = new Player(gameMap.getCell(0,0),"dev");
        player.pickUpItem();
        assertEquals(6100, player.getHealth());
        assertNotEquals(1000, player.getHealth());
        assertNotEquals(0, player.getHealth());
        assertEquals("player_armor_shield",player.getTileName());
        player.removeItem("Armor");
        assertEquals(6000, player.getHealth());
    }

    @Test
    void pickUpAndRemoveHelmetShouldModifyPlayersHealthAndAlsoChangePlayersTileName(){
        Helmet helmet = new Helmet(gameMap.getCell(0,0));
        Player player = new Player(gameMap.getCell(0,0),"dev");
        player.pickUpItem();
        assertEquals(6100, player.getHealth());
        assertNotEquals(1000, player.getHealth());
        assertNotEquals(0, player.getHealth());
        assertEquals("player_helmet_shield",player.getTileName());
        player.removeItem("Helmet");
        assertEquals(6000, player.getHealth());

    }

    @Test
    void pickUpAndRemoveShieldShouldModifyPlayersBlockPowerAndAlsoChangePlayersTileName(){
        Shield shield = new Shield(gameMap.getCell(0,0));
        Player player = new Player(gameMap.getCell(0,0),"dev");
        player.pickUpItem();
        assertEquals(130, player.getBlockPower());
        assertNotEquals(1000, player.getBlockPower());
        assertNotEquals(0, player.getBlockPower());
        assertEquals("player_shield",player.getTileName());
        player.removeItem("Shield");
        assertEquals(30,player.getBlockPower());
    }
    @Test
    void pickUpAndRemoveShoesShouldModifyPlayersHealth(){
        Shoes shoes = new Shoes(gameMap.getCell(0,0));
        Player player = new Player(gameMap.getCell(0,0),"dev");
        player.pickUpItem();
        assertEquals(6100, player.getHealth());
        assertNotEquals(1000, player.getHealth());
        assertNotEquals(0, player.getHealth());
        player.removeItem("Shoes");
        assertEquals(6000,player.getHealth());
    }

    @Test
    void playersCheckIfMovePossibleShouldReturnFalseWhenMeetsNPC(){
        Player player = new Player(gameMap.getCell(0,0),"dev");
        assertTrue(player.checkIfMovePossible(1,1));
        Arthur arthur = new Arthur(gameMap.getCell(1,1));
        assertFalse(player.checkIfMovePossible(1,1));
    }


}