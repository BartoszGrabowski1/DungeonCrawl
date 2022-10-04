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





    public static void dealDamageToPlayer(Player player, Monster monster) {
        int dmg = monster.calcDamage(FightAction.ATTACK);
        player.setHealth(player.getHealth() - dmg);
        checkBattleResult(player, monster);
    }

    public static void dealDamageToMonster(Player player, Monster monster) {
        int dmg = player.calcDamage(FightAction.ATTACK);
        monster.setAttacked(true);
        monster.setHealth(monster.getHealth() - dmg);
        checkBattleResult(player, monster);
    }

    public static void checkBattleResult(Player player, Monster monster) {
        if (monster.getHealth() <= 0) {
            playerWin(monster);
        } else if (player.getHealth() <= 0){
            monsterWin();
        }
    }

    public static void playerWin(Monster monster) {
        if (monster instanceof Medusa) playRandomMedusaDeathSound();
        else if (monster instanceof Vampire) playRandomVampireDeathSound();
        else if (monster instanceof Skeleton) playRandomSkeletonDeathSound();
        else if (monster instanceof FinalBoss) playSound(Sounds.BOSS_DEATH.getFile(), (float) 1.0);
        GameController.player.setExp(GameController.player.getExp() + monster.getExp());
        monster.lootItems();
        monster.getCreature().getCell().setCreature(null);
        GameMap.removeMonster(monster);
    }

    public static void monsterWin() {
        playRandomDeathSound();
        ViewController.setEndView();
    }

    public static FightAction makeMonsterMove() {
        return FightAction.values()[(int) (Utils.RANDOM.nextInt(0, FightAction.values().length))];
    }

}
