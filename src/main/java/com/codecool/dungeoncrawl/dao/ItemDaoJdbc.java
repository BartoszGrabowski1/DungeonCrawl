package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.codecool.dungeoncrawl.dao.GameStateDaoJdbc;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoJdbc implements ItemDao {
    private DataSource dataSource;

    public ItemDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(PlayerModel player, ItemModel itemModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO items (x,y,item_name,item_description,item_value,is_item_wore,player_id) VALUES (?, ?, ?, ?, ?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, itemModel.getX());
            statement.setInt(2, itemModel.getY());
            statement.setString(3, itemModel.getItemName());
            statement.setString(4, itemModel.getItemDescription());
            statement.setInt(5, itemModel.getItemValue());
            statement.setBoolean(6, itemModel.isItemWore());
            statement.setInt(7,player.getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            itemModel.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void drop(int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM items WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,player_id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }

    @Override
    public List<ItemModel> getAll(int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,x,y,item_name, item_description, item_value,is_item_wore FROM items WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, player_id);
            ResultSet rs = statement.executeQuery();
            List<ItemModel> result = new ArrayList<>();
            while (rs.next()) {
                ItemModel itemModel = new ItemModel(rs.getString(4),
                        rs.getInt(2),
                        rs.getInt(3),rs.getBoolean(7));
                itemModel.setItemDescription(rs.getString(5));
                itemModel.setItemValue(rs.getInt(6));
                itemModel.setId(rs.getInt(1));
                result.add(itemModel);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }
}