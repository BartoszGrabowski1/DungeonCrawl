package com.codecool.dungeoncrawl.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameState extends BaseModel {

    private int playerId;
    private Timestamp savedAt;
    private PlayerModel player;



    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }


    public GameState(Timestamp savedAt, PlayerModel player) {
        this.savedAt = savedAt;
        this.player = player;
    }
    public GameState(Timestamp savedAt) {
        this.savedAt = savedAt;
    }

    public Timestamp getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Timestamp savedAt) {
        this.savedAt = savedAt;
    }



    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
