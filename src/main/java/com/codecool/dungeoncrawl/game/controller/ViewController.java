package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.game.Cell;
import com.codecool.dungeoncrawl.game.CellType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static com.codecool.dungeoncrawl.Main.SCREEN_SIZE;
import static com.codecool.dungeoncrawl.Main.map;

public class ViewController {

    private static FXMLLoader menuViewLoader;
    private static FXMLLoader nameSelectViewLoader;
    private static FXMLLoader gameViewLoader;
    private static FXMLLoader fightViewLoader;
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

    public static void setMenuView() {
        menuViewLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
        setView(menuViewLoader, "Main Menu");
    }

    public static void setNameSelectView() {
        nameSelectViewLoader = new FXMLLoader(Main.class.getResource("name-view.fxml"));
        setView(nameSelectViewLoader, "Select Name");
    }

    public static void setGameView() {
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

//    public static void refreshView() {
////        boolean playerOnItem = false;
////        checkTile();
//        context.setFill(Color.BLACK);
//        context.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
//        for (int x = -SCREEN_SIZE; x < SCREEN_SIZE; x++) {
//            for (int y = -SCREEN_SIZE; y < SCREEN_SIZE; y++) {
//                try {
//                    Cell cell = map.getCell(map.getPlayer().getX() + x - (SCREEN_SIZE / 2), map.getPlayer().getY() + y - (SCREEN_SIZE / 2));
//                    if (cell.getCreature() != null) {
//                        Tiles.drawTile(context, cell.getCreature(), x, y);
////                        if (cell.getItem() != null && cell.getActor() instanceof Player) {
////                            playerOnItem = true;
////                        }
//                    } else if (cell.getItem() != null) {
//                        Tiles.drawTile(context, cell.getItem(), x, y);
//                    } else {
//                        Tiles.drawTile(context, cell, x, y);
//                    }
//                } catch (Exception e) {
//                    Tiles.drawTile(context, new Cell(map, x, y, CellType.EMPTY), x, y);
//                }
//            }
//        }
////        if (playerOnItem) {
////            showButton(pickUpItemBtn);
////        } else {
////            hideButton(pickUpItemBtn);
////        }
////
////        gc.getHealthLabel().setText("" + map.getPlayer().getHealth());
////        animation.play();
//    }

}
