package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.Items.Armor;
import com.codecool.dungeoncrawl.logic.Items.Key;
import com.codecool.dungeoncrawl.logic.Items.Sword;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.controller.NameController;
import com.codecool.dungeoncrawl.logic.map_generator.MapGenerator;
import com.codecool.dungeoncrawl.logic.map_generator.MapGeneratorImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    private static String generateMap() throws IOException {
        char[] items = {'1', '2', '3'};
        MapGenerator mapGenerator = new MapGeneratorImpl(
                64,
                64,
                15,
                5,
                10,
                false,
                1,
                3,
                6,
                items
        );
        mapGenerator.genLevel();
        return mapGenerator.genTilesLevel();
    }

    public static GameMap loadMap() throws IOException {

        Scanner scanner = new Scanner(generateMap());
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addMonsters(new Skeleton(cell));
                            break;
                        case 'v':
                            cell.setType(CellType.FLOOR);
                            map.addMonsters(new Vampire(cell));
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            map.addMonsters(new Medusa(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.addMonsters(new FinalBoss(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell, NameController.getUserName()));
                            break;
                        case '1':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case '2':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case '3':
                            cell.setType(CellType.FLOOR);
                            new Armor(cell);
                            break;
                        case 'H':
                            cell.setType(CellType.STAIRS);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
