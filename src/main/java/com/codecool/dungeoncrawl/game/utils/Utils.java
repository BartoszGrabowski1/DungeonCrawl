package com.codecool.dungeoncrawl.game.utils;

import java.util.Random;

public class Utils {

    public static final Random RANDOM = new Random();

    public static Thread setTimeout(Runnable runnable, int delay){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        thread.start();
        return thread;
    }

}
