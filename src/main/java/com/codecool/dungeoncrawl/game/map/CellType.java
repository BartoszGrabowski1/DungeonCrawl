package com.codecool.dungeoncrawl.game.map;

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
    GOLD_1("gold1"),
    GOLD_2("gold2"),
    GOLD_3("gold3"),
    GOLD_4("gold4"),
    GOLD_5("gold5"),
    GOLD_6("gold6"),
    BLOOD_1("blood1"),
    BLOOD_2("blood2"),
    BLOOD_3("blood3"),
    NPC("npc"),
    PENTAGRAM("pentagram"),
    STAIRS("stairs");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
