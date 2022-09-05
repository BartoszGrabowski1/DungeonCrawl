package com.codecool.dungeoncrawl.game.music;

public enum Sounds {
    MAIN("/com/codecool/dungeoncrawl/sounds/mainSound.wav", 510_000, 28_050_000),
    STEP_SOUNDS("/com/codecool/dungeoncrawl/sounds/footStepSound.wav"),
    EQUIP_SWORD("/com/codecool/dungeoncrawl/sounds/equip_sword.wav"),
    EQUIP_ARMOR("/com/codecool/dungeoncrawl/sounds/equip_armor.wav"),
    PICKUP_KEY("/com/codecool/dungeoncrawl/sounds/pickup_key.wav"),
    OPEN_DOORS("/com/codecool/dungeoncrawl/sounds/opened_doors.wav"),
    HIT_1("/com/codecool/dungeoncrawl/sounds/hit_1.wav"),
    HIT_2("/com/codecool/dungeoncrawl/sounds/hit_2.wav"),
    HIT_3("/com/codecool/dungeoncrawl/sounds/hit_3.wav"),
    HIT_4("/com/codecool/dungeoncrawl/sounds/hit_4.wav"),
    HIT_5("/com/codecool/dungeoncrawl/sounds/hit_5.wav"),
    BLOCK_1("/com/codecool/dungeoncrawl/sounds/block_1.wav"),
    BLOCK_2("/com/codecool/dungeoncrawl/sounds/block_2.wav"),
    DEAD_1("/com/codecool/dungeoncrawl/sounds/dead_1.wav"),
    DEAD_2("/com/codecool/dungeoncrawl/sounds/dead_2.wav"),
    DEAD_3("/com/codecool/dungeoncrawl/sounds/dead_3.wav"),
    DEAD_4("/com/codecool/dungeoncrawl/sounds/dead_4.wav"),
    PLAYER_HITTED_1("/com/codecool/dungeoncrawl/sounds/dostalismy_hita_1.wav"),
    PLAYER_HITTED_2("/com/codecool/dungeoncrawl/sounds/dostalismy_hita_2.wav"),
    PLAYER_HITTED_3("/com/codecool/dungeoncrawl/sounds/dostalismy_hita_3.wav"),
    PLAYER_HITTED_4("/com/codecool/dungeoncrawl/sounds/dostalismy_hita_4.wav"),
    MEDUSA_DEATH_1("/com/codecool/dungeoncrawl/sounds/medusa_dead_1.wav"),
    MEDUSA_DEATH_2("/com/codecool/dungeoncrawl/sounds/medusa_dead_2.wav"),
    SKELETON_DEATH_1("/com/codecool/dungeoncrawl/sounds/skeleton_dead_1.wav"),
    SKELETON_DEATH_2("/com/codecool/dungeoncrawl/sounds/skeleton_dead_2.wav"),
    SKELETON_DEATH_3("/com/codecool/dungeoncrawl/sounds/skeleton_dead_3.wav"),
    SKELETON_DEATH_4("/com/codecool/dungeoncrawl/sounds/skeleton_dead_4.wav"),
    VAMP_DEATH_1("/com/codecool/dungeoncrawl/sounds/vamp_dead_1.wav"),
    VAMP_DEATH_2("/com/codecool/dungeoncrawl/sounds/vamp_dead_2.wav"),
    VAMP_DEATH_3("/com/codecool/dungeoncrawl/sounds/vamp_dead_3.wav"),
    BOSS_SOUNDS("/com/codecool/dungeoncrawl/sounds/bossSound.wav"),
    BOSS_DEATH("/com/codecool/dungeoncrawl/sounds/boss_dead.wav"),
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
