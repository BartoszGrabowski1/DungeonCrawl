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
    public static String opening = Sounds.MAIN.getFile();
    public static String stepSound = Sounds.STEP_SOUNDS.getFile();

    public static String bossSound = Sounds.BOSS_SOUNDS.getFile();

    public static void playRandomMonsterSounds(String[] monsterTypeSounds) {
        int soundNumber = Utils.RANDOM.nextInt(0, 10);
        playSound(monsterTypeSounds[soundNumber], (float) 1);
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