package com.codecool.dungeoncrawl.logic.map_generator;

import java.io.IOException;

public interface MapGenerator {

    void genLevel();

    void genTilesLevel() throws IOException;

}
