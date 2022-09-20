package com.codecool.dungeoncrawl.game;


import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;

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
}