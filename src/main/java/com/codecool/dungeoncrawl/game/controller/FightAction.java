package com.codecool.dungeoncrawl.game.controller;

import java.util.HashMap;
import java.util.Map;

public enum FightAction {
    ATTACK, ABILITY, BLOCK;

    private static final Map<FightAction, FightAction> winMap = new HashMap<>();

    static {
        winMap.put(ATTACK, ABILITY);
        winMap.put(ABILITY, BLOCK);
        winMap.put(BLOCK, ATTACK);
    }

    ActionResult checkAgainst(FightAction action) {
        if (this == action)
            return ActionResult.DRAW;

        return winMap.get(this) == action ? ActionResult.WIN : ActionResult.LOSE;
    }

    public enum ActionResult {
        WIN, LOSE, DRAW
    }
}
