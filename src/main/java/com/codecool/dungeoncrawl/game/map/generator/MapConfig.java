package com.codecool.dungeoncrawl.game.map.generator;

public enum MapConfig {
    LEVELS(3),
    WIDTH(64),
    HEIGHT(64),
    MAX_ROOMS(10),
    MIN_ROOM_XY(3),
    MAX_ROOM_XY(10),
    ROOM_OVERLAP(false),
    RANDOM_CONNECTIONS(1),
    RANDOM_SPURS(3),
    SKELETONS(6),
    VAMPIRES(5),
    MEDUSAS(3),
    NPCS(1),
    ITEMS(new char[]{'1', '3', '4', '5', '6', '7'});

    private int number;
    private boolean roomOverlap = false;
    private char[] items;

    MapConfig(int number) {
        this.number = number;
    }

    MapConfig(boolean roomOverlap) {
        this.roomOverlap = roomOverlap;
    }

    MapConfig(char[] items) {
        this.items = items;
    }

    public int getNumber() {
        return number;
    }

    public boolean isRoomOverlap() {
        return roomOverlap;
    }

    public char[] getItems() {
        return items;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setRoomOverlap(boolean roomOverlap) {
        this.roomOverlap = roomOverlap;
    }

    public void setItems(char[] items) {
        this.items = items;
    }
}
