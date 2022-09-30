package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.game.creatures.*;
import com.codecool.dungeoncrawl.game.map.GameMap;
import com.codecool.dungeoncrawl.game.music.Sounds;
import com.codecool.dungeoncrawl.game.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import static com.codecool.dungeoncrawl.game.music.MusicPlayer.*;

public class FightController {


    public static boolean isFightAvailable = false;



    public void makeMove(FightAction userAction, Player player, Monster monster) {
        monster.setAttacked(true);
        FightAction monsterAction = makeMonsterMove();
        FightAction.ActionResult result = userAction.checkAgainst(monsterAction);
        fightTurn(userAction, player, monster, monsterAction, result);
        checkBattleResult(player, monster);

    }

    public void fightTurn(FightAction userAction, Player player, Monster monster, FightAction monsterAction, FightAction.ActionResult result) {
        if (result == FightAction.ActionResult.DRAW) {
            playRandomBlockSound();
        } else if (result == FightAction.ActionResult.WIN) {
            dealDamageToMonster(userAction, player, monster);
            playRandomHitSound();
        } else { // LOSE
            dealDamageToPlayer(player, monster, monsterAction);
            playRandomPlayerHittedSound();
        }
    }


    public void dealDamageToPlayer(Player player, Monster monster, FightAction monsterAction) {
        int dmg = monster.calcDamage(monsterAction);
        player.setHealth(player.getHealth() - dmg);
    }

    public void dealDamageToMonster(FightAction userAction, Player player, Monster monster) {
        int dmg = player.calcDamage(userAction);
        if (userAction == FightAction.SPECIAL) {
            player.setHealth(player.getHealth() + 100);
        } else if (userAction == FightAction.ATTACK || userAction == FightAction.BLOCK) {
            monster.setHealth(monster.getHealth() - dmg);
        }
    }

    public void checkBattleResult(Player player, Monster monster) {
        if (monster.getHealth() <= 0) {
            playerWin(monster);
        } else if (player.getHealth() <= 0){
            monsterWin();
        }
    }

    public void playerWin(Monster monster) {
        if (monster instanceof Medusa) playRandomMedusaDeathSound();
        else if (monster instanceof Vampire) playRandomVampireDeathSound();
        else if (monster instanceof Skeleton) playRandomSkeletonDeathSound();
        else if (monster instanceof FinalBoss) playSound(Sounds.BOSS_DEATH.getFile(), (float) 1.0);
        GameController.player.setExp(GameController.player.getExp() + monster.getExp());
        monster.lootItems();
        monster.getCreature().getCell().setCreature(null);
        GameMap.removeMonster(monster);
    }

    public void monsterWin() {
        playRandomDeathSound();
        ViewController.setEndView();
    }

    public FightAction makeMonsterMove() {
        return FightAction.values()[(int) (Utils.RANDOM.nextInt(0, FightAction.values().length))];
    }

}
