package com.codecool.dungeoncrawl.logic.controller;

import java.util.HashMap;
import java.util.Map;

public enum Action {
    ATTACK, ABILITY, BLOCK;

    private static final Map<Action, Action> winMap = new HashMap<>();

    static {
        winMap.put(ATTACK, ABILITY);
        winMap.put(ABILITY, BLOCK);
        winMap.put(BLOCK, ATTACK);
    }

    ActionResult checkAgainst(Action action) {
        if (this == action)
            return ActionResult.DRAW;

        return winMap.get(this) == action ? ActionResult.WIN : ActionResult.LOSE;
    }

    public enum ActionResult {
        WIN, LOSE, DRAW
    }
}
