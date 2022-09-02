package com.codecool.dungeoncrawl.game.map;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.creatures.Monster;
import com.codecool.dungeoncrawl.game.creatures.Npc;
import com.codecool.dungeoncrawl.game.creatures.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    private static List<Monster> monsters = new ArrayList<>();

    private static List<Npc> npcs = new ArrayList<>();

    public List<Monster> getMonsters() {
        return monsters;
    }


    public void addNpc(Npc npc) { npcs.add(npc); }

    public void removeNpc(Npc npc) { npcs.remove(npc);}

    public void addMonsters(Monster monster) {
        monsters.add(monster);
    }


    public static void removeMonster(Monster monster) {
        monsters.remove(monster);
    }

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
