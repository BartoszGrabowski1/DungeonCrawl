package com.codecool.dungeoncrawl.game;

import com.codecool.dungeoncrawl.game.creatures.Player;
import com.codecool.dungeoncrawl.game.map.GameMap;
import com.codecool.dungeoncrawl.game.map.MapLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapLoaderTest {

    @Test
    void twoDifferentMapsShouldntBeTheSame(){
        GameMap map = MapLoader.loadMap(true,false);;
        GameMap map1 = MapLoader.loadMap(false,true);
        assertNotEquals(map,map1);
    }

}
