package com.codecool.dungeoncrawl.game.map;

import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.Items.*;
import com.codecool.dungeoncrawl.game.creatures.*;
import com.codecool.dungeoncrawl.game.controller.GameController;
import com.codecool.dungeoncrawl.game.controller.NameController;
import com.codecool.dungeoncrawl.game.map.generator.MapGenerator;
import com.codecool.dungeoncrawl.game.map.generator.MapGeneratorImpl;
import com.codecool.dungeoncrawl.game.utils.Utils;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    private static String generateMap() {
        char[] items = {'1', '3', '4', '5', '6', '7'};
        MapGenerator mapGenerator = new MapGeneratorImpl(
                64,
                64,
                30,
                3,
                20,
                false,
                5,
                10,
                6,
                5,
                3,
                1,
                items
        );
        mapGenerator.genLevel();
        return mapGenerator.genTilesLevel();
    }

    public static GameMap loadMap(boolean isBossLevel) {
        Scanner scanner;
        if (isBossLevel) {
            InputStream mapLevel = MapLoader.class.getResourceAsStream("/com/codecool/dungeoncrawl/levels/boss.txt");
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
                            addWalls(cell);
                            break;
                        case '.':
                            addFloor(cell);
                            break;
                        case 's':
                            addFloor(cell);
                            map.addMonsters(new Skeleton(cell));
                            break;
                        case 'v':
                            addFloor(cell);
                            map.addMonsters(new Vampire(cell));
                            break;
                        case 'm':
                            addFloor(cell);
                            map.addMonsters(new Medusa(cell));
                            break;
                        case 'n':
                            addFloor(cell);
                            map.addNpc(new Arthur(cell));
                            cell.setType(CellType.NPC);
                            break;
                        case 'b':
                            addFloor(cell);
                            map.addMonsters(new FinalBoss(cell));
                            break;
                        case '@':
                            addFloor(cell);
                            if (GameController.player == null) {
                                GameController.player = new Player(cell, NameController.getUserName());
                            } else {
                                GameController.player.setCell(cell);
                                GameController.player.getCell().setCreature(GameController.player);
                            }
                            map.setPlayer(GameController.player);
                            break;
                        case '1':
                            addFloor(cell);
                            new Sword(cell);
                            break;
                        case '2':
                            addFloor(cell);
                            new Key(cell);
                            break;
                        case '3':
                            addFloor(cell);
                            new Armor(cell);
                            break;
                        case '4':
                            addFloor(cell);
                            new Helmet(cell);
                            break;
                        case '5':
                            addFloor(cell);
                            new Shoes(cell);
                            break;
                        case '6':
                            addFloor(cell);
                            new Shield(cell);
                            break;
                        case '7':
                            addFloor(cell);
                            new Gloves(cell);
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

    private static void addFloor(Cell cell) {
        switch (Utils.RANDOM.nextInt(3)) {
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

    private static void addWalls(Cell cell) {
        switch (Utils.RANDOM.nextInt(3)) {
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
