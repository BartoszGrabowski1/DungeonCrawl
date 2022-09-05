package com.codecool.dungeoncrawl.game.music;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.game.utils.Utils;
import javafx.animation.Timeline;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import static com.codecool.dungeoncrawl.game.controller.GameController.isMusicPlaying;

public class MusicPlayer {

    public static Clip mainClip;
    public static Thread playMainClipDelay;
    public static Timeline monstersSounds;
    private static int mainClipFramePosition = 0;
    public static String[] monsterSounds = {
            Sounds.MONSTERS_SOUNDS_1.getFile(),
            Sounds.MONSTERS_SOUNDS_2.getFile(),
            Sounds.MONSTERS_SOUNDS_3.getFile(),
            Sounds.MONSTERS_SOUNDS_4.getFile(),
            Sounds.MONSTERS_SOUNDS_5.getFile(),
            Sounds.MONSTERS_SOUNDS_6.getFile(),
            Sounds.MONSTERS_SOUNDS_7.getFile(),
            Sounds.MONSTERS_SOUNDS_8.getFile(),
            Sounds.MONSTERS_SOUNDS_9.getFile(),
            Sounds.MONSTERS_SOUNDS_10.getFile()
    };
    public static String[] hitSounds = {
            Sounds.HIT_1.getFile(),
            Sounds.HIT_2.getFile(),
            Sounds.HIT_3.getFile(),
            Sounds.HIT_4.getFile(),
            Sounds.HIT_5.getFile()
    };
    public static String[] playerHittedSounds = {
            Sounds.PLAYER_HITTED_1.getFile(),
            Sounds.PLAYER_HITTED_2.getFile(),
            Sounds.PLAYER_HITTED_3.getFile(),
            Sounds.PLAYER_HITTED_4.getFile()
    };
    public static String[] blockSounds = {
            Sounds.BLOCK_1.getFile(),
            Sounds.BLOCK_2.getFile()
    };
    public static String[] deathSounds = {
            Sounds.DEAD_1.getFile(),
            Sounds.DEAD_2.getFile(),
            Sounds.DEAD_3.getFile(),
            Sounds.DEAD_4.getFile()
    };
    public static String[] medusaDeathSounds = {
            Sounds.MEDUSA_DEATH_1.getFile(),
            Sounds.MEDUSA_DEATH_2.getFile()
    };
    public static String[] skeletonDeathSounds = {
            Sounds.SKELETON_DEATH_1.getFile(),
            Sounds.SKELETON_DEATH_2.getFile(),
            Sounds.SKELETON_DEATH_3.getFile(),
            Sounds.SKELETON_DEATH_4.getFile()
    };
    public static String[] vampireDeathSounds = {
            Sounds.VAMP_DEATH_1.getFile(),
            Sounds.VAMP_DEATH_2.getFile(),
            Sounds.VAMP_DEATH_3.getFile()
    };
    public static String opening = Sounds.MAIN.getFile();
    public static String stepSound = Sounds.STEP_SOUNDS.getFile();

    public static String bossSound = Sounds.BOSS_SOUNDS.getFile();

    public static void playRandomMonsterSounds() {
        int soundNumber = Utils.RANDOM.nextInt(0, 10);
        playSound(monsterSounds[soundNumber], (float) 1);
    }

    public static void playRandomHitSound() {
        int soundNumber = Utils.RANDOM.nextInt(0, 5);
        playSound(hitSounds[soundNumber], (float) 1);
    }

    public static void playRandomPlayerHittedSound() {
        int soundNumber = Utils.RANDOM.nextInt(0, 4);
        playSound(playerHittedSounds[soundNumber], (float) 1);
    }

    public static void playRandomBlockSound() {
        int soundNumber = Utils.RANDOM.nextInt(0, 2);
        playSound(blockSounds[soundNumber], (float) 1);
    }

    public static void playRandomDeathSound() {
        int soundNumber = Utils.RANDOM.nextInt(0, 4);
        playSound(deathSounds[soundNumber], (float) 1);
    }

    public static void playRandomMedusaDeathSound() {
        int soundNumber = Utils.RANDOM.nextInt(0, 2);
        playSound(medusaDeathSounds[soundNumber], (float) 1);
    }

    public static void playRandomSkeletonDeathSound() {
        int soundNumber = Utils.RANDOM.nextInt(0, 4);
        playSound(skeletonDeathSounds[soundNumber], (float) 1);
    }

    public static void playRandomVampireDeathSound() {
        int soundNumber = Utils.RANDOM.nextInt(0, 3);
        playSound(vampireDeathSounds[soundNumber], (float) 1);
    }

    public static void playSound(String fileName,float volume) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Main.class.getResourceAsStream(fileName));
            clip.open(inputStream);
            setVolume(volume,clip);
            if (mainClipFramePosition > 0) clip.setFramePosition(mainClipFramePosition);
            clip.start();
            if (fileName.equals(Sounds.MAIN.getFile())) {
                mainClip = clip;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public static void setVolume(float volume, Clip clip) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public static void stopSounds() {
        if (mainClipFramePosition > 0 && mainClipFramePosition < Sounds.MAIN.getLengthInFrames()) mainClipFramePosition = mainClip.getFramePosition();
        else mainClipFramePosition = 0;
        mainClip.stop();
        monstersSounds.stop();
        isMusicPlaying = false;
        playMainClipDelay.stop();
    }
}