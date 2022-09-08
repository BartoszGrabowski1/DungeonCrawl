package com.codecool.dungeoncrawl.dao;


import com.codecool.dungeoncrawl.game.Items.Item;
import com.codecool.dungeoncrawl.game.creatures.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;

    private ItemDao itemDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        itemDao = new ItemDaoJdbc(dataSource);
    }

    public void savePlayer(PlayerModel player) {
        playerDao.add(player);
    }
    public void saveGameState(GameState gameState) {
        gameStateDao.add(gameState);
    }
    public void saveAll(Player player, String
            savedGameName, List<Item> inventory,List<Item> equipment) {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        PlayerModel model = new PlayerModel(player, savedGameName);
        GameState gameState = new GameState(currentDate, model);
        savePlayer(model);
        saveGameState(gameState);
        addAllItemsFromInventory(model, inventory);
        addAllItemsFromEquipment(model,equipment);

    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("USER");
        String password = System.getenv("PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
    public List<String> getLoadNames(){
        return playerDao.getSaveNames();
    }
    public List<GameState> allSaves(){
        return gameStateDao.getAll();
    }

    public void updatePlayer(PlayerModel playerModel){
        playerDao.update(playerModel);
    }
    public void updateGameState(GameState gameState){
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        gameState.setSavedAt(currentDate);
        gameStateDao.update(gameState);
    }
    public void addAllItemsFromInventory(PlayerModel model, List<Item> inventory){
        for (Item item : inventory) {
            ItemModel itemModel = new ItemModel(item,true);
            addItem(model, itemModel);
        }
    }
    public void addAllItemsFromEquipment(PlayerModel model, List<Item> inventory){
        for (Item item : inventory) {
            ItemModel itemModel = new ItemModel(item,false);
            addItem(model, itemModel);
        }
    }
    public void addItem(PlayerModel playerModel, ItemModel item){
        itemDao.add(playerModel,item);
    }
    public void deleteAllUsersItems(int playerId){
        itemDao.drop(playerId);
    }
    public List<ItemModel> getUserItems(int player_id){
        return  itemDao.getAll(player_id);
    }
    public PlayerModel getSelectedPlayer(String selectedPlayer){
        return playerDao.get(selectedPlayer);
    }

    public GameState getGameState(int playerId) {
        return gameStateDao.get(playerId);
    }

}
