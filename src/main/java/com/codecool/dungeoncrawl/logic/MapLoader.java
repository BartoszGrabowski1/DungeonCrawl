package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.Items.Armor;
import com.codecool.dungeoncrawl.logic.Items.Key;
import com.codecool.dungeoncrawl.logic.Items.Sword;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.map_generator.MapGenerator;
import com.codecool.dungeoncrawl.logic.map_generator.MapGeneratorImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class MapLoader {

    private static String generateMap() {
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

    public static GameMap loadMap() {
        Random random = new Random();
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
                            map.setPlayer(new Player(cell));
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

}
