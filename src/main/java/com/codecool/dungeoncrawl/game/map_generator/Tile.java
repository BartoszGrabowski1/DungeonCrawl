package com.codecool.dungeoncrawl.game.map_generator;

public enum Tile {
    STONE(" "),
    FLOOR("."),
    WALL("#");

    private final String tile;

    Tile(String tile) {
        this.tile = tile;
    }

    public String getTile() {
        return tile;
    }

    @Override
    public String toString() {
        return getTile();
    }
}
