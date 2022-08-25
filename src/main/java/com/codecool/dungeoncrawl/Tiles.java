package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 2048, 3040, true, false);
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
        tileMap.put("player", new Tile(19, 68));
        tileMap.put("skeleton", new Tile(18, 74));
        tileMap.put("vampire", new Tile(13,77));
        tileMap.put("medusa", new Tile(6,77));
        tileMap.put("boss", new Tile(0,66));
        tileMap.put("sword", new Tile(1,46));
        tileMap.put("key", new Tile(55,40));
        tileMap.put("armor", new Tile(9,38));
        tileMap.put("stairs", new Tile(53,11));
        tileMap.put("closed_doors", new Tile(52,10));
        tileMap.put("open_doors", new Tile(48,10));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
//        System.out.println("tile " + d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}