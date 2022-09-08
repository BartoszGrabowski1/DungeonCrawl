package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface ItemDao {
    void add(PlayerModel playerModel,ItemModel item);
    void drop(int player_id);

    List<ItemModel> getAll(int playerId);
}