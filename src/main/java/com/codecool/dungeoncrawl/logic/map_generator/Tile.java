package com.codecool.dungeoncrawl.logic.map_generator;

public enum Tile {
    STONE(" "),
    FLOOR("."),
    WALL("#"),

    STAIRS("H");

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
