package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    FLOOR_2("floor2"),
    FLOOR_3("floor3"),
    WALL("wall"),
    WALL_2("wall2"),
    WALL_3("wall3"),
    CLOSED_DOORS("closed_doors"),
    OPEN_DOORS("open_doors"),
    STAIRS("stairs");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
