package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Monsters;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class Main extends Application {

    private final int SCREEN_SIZE = 20;
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            SCREEN_SIZE * Tiles.TILE_WIDTH,
            SCREEN_SIZE * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> incrementLabel()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void incrementLabel() {
        List<Monsters> monsters = map.getMonsters();

        for(int i =0; i<monsters.size(); i++){
            System.out.println(monsters.get(i).drowMoves());
            monsters.get(i).monsterMovement(map);
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                incrementLabel();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = -SCREEN_SIZE; x < SCREEN_SIZE; x++) {
            for (int y = -SCREEN_SIZE; y < SCREEN_SIZE; y++) {
                try {
                    Cell cell = map.getCell(map.getPlayer().getX() + x - (SCREEN_SIZE / 2), map.getPlayer().getY() + y - (SCREEN_SIZE / 2));
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                } catch (Exception e) {
                    Tiles.drawTile(context, new Cell(map, x, y, CellType.EMPTY), x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}
