package com.codecool.dungeoncrawl.game.map;

import com.codecool.dungeoncrawl.game.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/com/codecool/dungeoncrawl/img/tiles_player.png", 2048, 3040, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH);
            y = j * (TILE_WIDTH);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 14));
        tileMap.put("wall2", new Tile(11, 14));
        tileMap.put("wall3", new Tile(12, 14));
        tileMap.put("floor", new Tile(4, 3));
        tileMap.put("floor2", new Tile(5, 3));
        tileMap.put("floor3", new Tile(6, 3));
        tileMap.put("player", new Tile(0, 78));
        tileMap.put("player_armor", new Tile(1, 78));
        tileMap.put("player_shield", new Tile(2, 78));
        tileMap.put("player_sword", new Tile(3, 78));
        tileMap.put("player_armor_shield", new Tile(4, 78));
        tileMap.put("player_armor_shield_sword", new Tile(5, 78));
        tileMap.put("player_helmet", new Tile(6, 78));
        tileMap.put("player_helmet_sword", new Tile(7, 78));
        tileMap.put("player_helmet_shield", new Tile(8, 78));
        tileMap.put("player_armor_helmet_shield", new Tile(9, 78));
        tileMap.put("player_helmet_shield_sword", new Tile(10, 78));
        tileMap.put("player_armor_helmet_shield_sword", new Tile(11, 78));
        tileMap.put("player_armor_helmet", new Tile(12, 78));
        tileMap.put("player_armor_helmet_sword", new Tile(13, 78));
        tileMap.put("player_sword_shield", new Tile(14, 78));
        tileMap.put("player_armor_sword", new Tile(15, 78));
        tileMap.put("skeleton", new Tile(18, 74));
        tileMap.put("vampire", new Tile(13, 77));
        tileMap.put("medusa", new Tile(6, 77));
        tileMap.put("arthur", new Tile(10,68));
        tileMap.put("crudy", new Tile(44, 77));
        tileMap.put("boss", new Tile(0, 66));
        tileMap.put("sword", new Tile(1, 46));
        tileMap.put("enhancedSword", new Tile(2,46));
        tileMap.put("key", new Tile(55, 40));
        tileMap.put("gold1", new Tile(54, 40));
        tileMap.put("gold2", new Tile(42, 40));
        tileMap.put("gold3", new Tile(43, 40));
        tileMap.put("gold4", new Tile(44, 40));
        tileMap.put("gold5", new Tile(46, 40));
        tileMap.put("gold6", new Tile(47, 40));
        tileMap.put("blood1", new Tile(0, 52));
        tileMap.put("blood2", new Tile(1, 52));
        tileMap.put("blood3", new Tile(2, 52));
        tileMap.put("armor", new Tile(9, 38));
        tileMap.put("helmet", new Tile(55, 36));
        tileMap.put("shoes", new Tile(27, 36));
        tileMap.put("shield", new Tile(23, 37));
        tileMap.put("gloves", new Tile(32, 36));
        tileMap.put("stairs", new Tile(53, 11));
        tileMap.put("closed_doors", new Tile(52, 10));
        tileMap.put("open_doors", new Tile(48, 10));
        tileMap.put("skull", new Tile(32,28));
        tileMap.put("pentagram", new Tile(3,13));
        tileMap.put("gem", new Tile(44, 24));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
//        System.out.println("tile " + d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}