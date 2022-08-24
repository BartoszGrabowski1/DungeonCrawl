package com.codecool.dungeoncrawl.logic.music;

import com.codecool.dungeoncrawl.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
    public static String opening = "/mainSound.wav";
    public static String stepSound = "/footStepSound.wav";
    public static void playSound(String fileName) {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Main.class.getResourceAsStream(fileName));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
    }
}
