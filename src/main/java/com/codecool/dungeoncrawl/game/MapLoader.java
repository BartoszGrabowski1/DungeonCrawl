package com.codecool.dungeoncrawl.game;

import com.codecool.dungeoncrawl.game.Items.Armor;
import com.codecool.dungeoncrawl.game.Items.Key;
import com.codecool.dungeoncrawl.game.Items.Sword;
import com.codecool.dungeoncrawl.game.creatures.*;
import com.codecool.dungeoncrawl.game.controller.NameController;
import com.codecool.dungeoncrawl.game.map_generator.MapGenerator;
import com.codecool.dungeoncrawl.game.map_generator.MapGeneratorImpl;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class MapLoader {

    private static String generateMap() {
        char[] items = {'1', '3'};
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
                5,
                3,
                items
        );
        mapGenerator.genLevel();
        return mapGenerator.genTilesLevel();
    }

    public static GameMap loadMap(boolean isBossLevel) {
        Random random = new Random();
        Scanner scanner;
        if (isBossLevel) {
            InputStream mapLevel = MapLoader.class.getResourceAsStream("/boss.txt");
            scanner = new Scanner(mapLevel);
        } else {
            String mapLevel = generateMap();
            scanner = new Scanner(mapLevel);
        }
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
                            addWalls(random, cell);
                            break;
                        case '.':
                            addFloor(random, cell);
                            break;
                        case 's':
                            addFloor(random, cell);
                            map.addMonsters(new Skeleton(cell));
                            break;
                        case 'v':
                            addFloor(random, cell);
                            map.addMonsters(new Vampire(cell));
                            break;
                        case 'm':
                            addFloor(random, cell);
                            map.addMonsters(new Medusa(cell));
                            break;
                        case 'b':
                            addFloor(random, cell);
                            map.addMonsters(new FinalBoss(cell));
                            break;
                        case '@':
                            addFloor(random, cell);
                            map.setPlayer(new Player(cell, NameController.getUserName()));
                            break;
                        case '1':
                            addFloor(random, cell);
                            new Sword(cell);
                            break;
                        case '2':
                            addFloor(random, cell);
                            new Key(cell);
                            break;
                        case '3':
                            addFloor(random, cell);
                            new Armor(cell);
                            break;
                        case 'H':
                            cell.setType(CellType.STAIRS);
                            break;
                        case 'd':
                            cell.setType(CellType.CLOSED_DOORS);
                            break;
                        case ',':
                            cell.setType(CellType.GOLD_1);
                            break;
                        case '/':
                            cell.setType(CellType.GOLD_2);
                            break;
                        case '\\':
                            cell.setType(CellType.GOLD_3);
                            break;
                        case '-':
                            cell.setType(CellType.GOLD_4);
                            break;
                        case '+':
                            cell.setType(CellType.GOLD_5);
                            break;
                        case '*':
                            cell.setType(CellType.GOLD_6);
                            break;
                        case '[':
                            cell.setType(CellType.BLOOD_1);
                            break;
                        case ']':
                            cell.setType(CellType.BLOOD_2);
                            break;
                        case '{':
                            cell.setType(CellType.BLOOD_3);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    private static void addFloor(Random random, Cell cell) {
        switch (random.nextInt(3)) {
            case 0:
                cell.setType(CellType.FLOOR);
                break;
            case 1:
                cell.setType(CellType.FLOOR_2);
                break;
            default:
                cell.setType(CellType.FLOOR_3);
                break;
        }
    }

    private static void addWalls(Random random, Cell cell) {
        switch (random.nextInt(3)) {
            case 0:
                cell.setType(CellType.WALL_2);
                break;
            case 1:
                cell.setType(CellType.WALL_3);
                break;
            default:
                cell.setType(CellType.WALL);
                break;
        }
    }

}
