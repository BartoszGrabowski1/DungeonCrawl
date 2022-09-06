package com.codecool.dungeoncrawl.game.map.generator;

public enum MapConfig {
    LEVELS(3),
    WIDTH(64),
    HEIGHT(64),
    MAX_ROOMS(30),
    MIN_ROOM_XY(3),
    MAX_ROOM_XY(20),
    ROOM_OVERLAP(true),
    RANDOM_CONNECTIONS(5),
    RANDOM_SPURS(10),
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
