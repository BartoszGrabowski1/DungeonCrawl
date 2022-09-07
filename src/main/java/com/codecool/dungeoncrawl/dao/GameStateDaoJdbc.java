package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;
    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(GameState state) {

        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (saved_at, player_id) VALUES ( ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, state.getSavedAt());
            statement.setInt(2, state.getPlayer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game_state SET saved_at=?, player_id=? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(3,state.getId());
            statement.setTimestamp(1,state.getSavedAt());
            statement.setInt(2,state.getPlayer().getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }

    @Override
    public GameState get(int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, saved_at, player_id FROM game_state WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,playerId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            GameState gameState = new GameState(rs.getTimestamp(2));
            gameState.setId(rs.getInt(1));
            return gameState;  //TODO: set player to this state at upper level
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }

    @Override
    public List<GameState> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, saved_at, player_id FROM game_state";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<GameState> result = new ArrayList<>();
            while (rs.next()) {
                GameState gameState = new GameState(rs.getTimestamp(2));
                gameState.setId(rs.getInt(1));
                gameState.setPlayerId(rs.getInt(3));
                result.add(gameState);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }

}
