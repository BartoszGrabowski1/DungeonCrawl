package com.codecool.dungeoncrawl.game;


import com.codecool.dungeoncrawl.game.controller.GameController;
import com.codecool.dungeoncrawl.game.creatures.*;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;

import com.codecool.dungeoncrawl.game.map.MapLoader;
import com.codecool.dungeoncrawl.game.map.generator.MapConfig;
import com.codecool.dungeoncrawl.game.quests.FirstQuest;
import com.codecool.dungeoncrawl.game.quests.SecondQuest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    GameMap map = new GameMap(4, 4, CellType.FLOOR);

    @Test
    void getNeighbor() {
        Cell cell = map.getCell(1, 1);
        Cell neighbor = cell.getNeighbor(-1, 0);
        assertEquals(0, neighbor.getX());
        assertEquals(1, neighbor.getY());
    }

    @Test
    void cellOnEdgeHasNoNeighbor() {
        Cell cell  = map.getCell(1, 2);
        assertEquals(map.getCell(1,3), cell.getNeighbor(0, 1));
    }

    @Test
    void isMapLoadedCorrectly() {
        int countMonsters = MapConfig.SKELETONS.getNumber() + MapConfig.VAMPIRES.getNumber() + MapConfig.MEDUSAS.getNumber();
        int result = 0;
        var myMap = MapLoader.loadMap(false,false);
        for(Monster monster: myMap.getMonsters()){
            if (monster instanceof Vampire || monster instanceof Medusa || monster instanceof Skeleton){
                result += 1;
            }
        }
        assertEquals(countMonsters, result);
    }
}