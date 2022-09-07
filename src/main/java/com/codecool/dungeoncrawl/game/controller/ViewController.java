package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;
import java.sql.SQLException;

public class ViewController {

    private static FXMLLoader menuViewLoader;
    private static FXMLLoader nameSelectViewLoader;
    private static FXMLLoader gameViewLoader;
    private static FXMLLoader fightViewLoader;

    private static FXMLLoader loadGameViewLoader;
    private static FXMLLoader endViewLoader;
    public static GraphicsContext context;

    public static void closeView() {
        Main.stage.close();
    }

    private static void setView(FXMLLoader view, String title) {
        try {
            Main.scene = new Scene(view.load());
            Main.stage.setScene(Main.scene);
            Main.stage.setTitle(title);
            Main.stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setLoadGameView(){
        loadGameViewLoader = new FXMLLoader(Main.class.getResource("loadGame-view.fxml"));
        setView(loadGameViewLoader, "Load Game");
    }
    public static void setMenuView() {
        menuViewLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
        setView(menuViewLoader, "Main Menu");
    }

    public static void setNameSelectView() {
        nameSelectViewLoader = new FXMLLoader(Main.class.getResource("name-view.fxml"));
        setView(nameSelectViewLoader, "Select Name");
    }

    public static void setGameView(){
        gameViewLoader = new FXMLLoader(Main.class.getResource("game-view.fxml"));
        setView(gameViewLoader, "Dungeon Crawl");
        ((GameController) gameViewLoader.getController()).setupKeys();
    }

    public static void setFightView() {
        fightViewLoader = new FXMLLoader(Main.class.getResource("fight-view.fxml"));
        setView(fightViewLoader, "Dungeon Crawl - Fight");
    }

    public static void setEndView() {
        endViewLoader = new FXMLLoader(Main.class.getResource("end-view.fxml"));
        setView(endViewLoader, "Game Over");
    }

    public static void setConfView() {
        endViewLoader = new FXMLLoader(Main.class.getResource("conf-view.fxml"));
        setView(endViewLoader, "Game Over");
    }
}
