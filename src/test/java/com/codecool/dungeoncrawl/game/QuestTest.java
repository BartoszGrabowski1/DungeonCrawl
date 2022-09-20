package com.codecool.dungeoncrawl.game;


import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;

import com.codecool.dungeoncrawl.game.quests.FirstQuest;
import com.codecool.dungeoncrawl.game.quests.SecondQuest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest{

    @Test
    void isAllFlagOnFalse() {
        assertFalse(FirstQuest.isFirstMissionOn);
        assertFalse(FirstQuest.isFirstMissionFinished);
        assertFalse(FirstQuest.isSkullInInventory);
        assertFalse(SecondQuest.isQuestLevel);
        assertFalse(SecondQuest.isSecondMissionOn);
        assertFalse(SecondQuest.isSecondMissionFinished);
        assertFalse(SecondQuest.isBloodLvlFinished);
    }

    @Test
    void isShuffleCorrectly() {
        assertTrue(FirstQuest.shuffleString().contains("W"));
        assertTrue(FirstQuest.shuffleString().contains("S"));
        assertTrue(FirstQuest.shuffleString().contains("E"));
        assertTrue(FirstQuest.shuffleString().contains("N"));
    }
}
