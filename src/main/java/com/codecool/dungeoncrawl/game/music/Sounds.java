package com.codecool.dungeoncrawl.game.music;

public enum Sounds {
    MAIN("/com/codecool/dungeoncrawl/sounds/mainSound.wav", 510_000, 28_050_000),
    STEP_SOUNDS("/com/codecool/dungeoncrawl/sounds/footStepSound.wav"),
    EQUIP_SWORD("/com/codecool/dungeoncrawl/sounds/equip_sword.wav"),
    EQUIP_ARMOR("/com/codecool/dungeoncrawl/sounds/equip_armor.wav"),
    PICKUP_KEY("/com/codecool/dungeoncrawl/sounds/pickup_key.wav"),
    OPEN_DOORS("/com/codecool/dungeoncrawl/sounds/opened_doors.wav"),
    BOSS_SOUNDS("/com/codecool/dungeoncrawl/sounds/bossSound.wav"),
    MONSTERS_SOUNDS_1("/com/codecool/dungeoncrawl/sounds/monsters/1.wav"),
    MONSTERS_SOUNDS_2("/com/codecool/dungeoncrawl/sounds/monsters/2.wav"),
    MONSTERS_SOUNDS_3("/com/codecool/dungeoncrawl/sounds/monsters/3.wav"),
    MONSTERS_SOUNDS_4("/com/codecool/dungeoncrawl/sounds/monsters/4.wav"),
    MONSTERS_SOUNDS_5("/com/codecool/dungeoncrawl/sounds/monsters/5.wav"),
    MONSTERS_SOUNDS_6("/com/codecool/dungeoncrawl/sounds/monsters/6.wav"),
    MONSTERS_SOUNDS_7("/com/codecool/dungeoncrawl/sounds/monsters/7.wav"),
    MONSTERS_SOUNDS_8("/com/codecool/dungeoncrawl/sounds/monsters/8.wav"),
    MONSTERS_SOUNDS_9("/com/codecool/dungeoncrawl/sounds/monsters/9.wav"),
    MONSTERS_SOUNDS_10("/com/codecool/dungeoncrawl/sounds/monsters/10.wav");

    private final String FILE;
    private int lengthInMilliseconds;
    private int lengthInFrames;

    Sounds(String fileSource, int lengthInMilliseconds, int lengthInFrames) {
        this.FILE = fileSource;
        this.lengthInMilliseconds = lengthInMilliseconds;
        this.lengthInFrames = lengthInFrames;
    }

    Sounds(String fileSource){
        this.FILE = fileSource;
    }

    public String getFile() {
        return FILE;
    }

    public int getLengthInMilliseconds() {
        return lengthInMilliseconds;
    }

    public int getLengthInFrames() {
        return lengthInFrames;
    }
}
