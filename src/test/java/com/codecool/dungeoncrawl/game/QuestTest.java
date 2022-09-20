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
        assertEquals(false, FirstQuest.isFirstMissionFinished);
        assertEquals(false,FirstQuest.isSkullInInventory);
        assertEquals(false,SecondQuest.isQuestLevel);
        assertEquals(false,SecondQuest.isSecondMissionOn);
        assertEquals(false,SecondQuest.isSecondMissionFinished);
        assertEquals(false,SecondQuest.isBloodLvlFinished);
    }

    @Test
    void isShuffleCorrectly() {
        assertTrue(FirstQuest.shuffleString().contains("W"));
        assertTrue(FirstQuest.shuffleString().contains("S"));
        assertTrue(FirstQuest.shuffleString().contains("E"));
        assertTrue(FirstQuest.shuffleString().contains("N"));
    }
}
