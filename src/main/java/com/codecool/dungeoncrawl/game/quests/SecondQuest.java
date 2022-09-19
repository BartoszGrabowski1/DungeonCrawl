package com.codecool.dungeoncrawl.game.quests;

import com.codecool.dungeoncrawl.game.Items.Gem;
import com.codecool.dungeoncrawl.game.Items.Item;
import com.codecool.dungeoncrawl.game.Items.Sword;
import com.codecool.dungeoncrawl.game.controller.GameController;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Objects;

import static com.codecool.dungeoncrawl.game.controller.GameController.player;

public class SecondQuest {
    public static boolean isSecondMissionFinished = false;
    public static boolean isSecondMissionOn = false;
    public static boolean isBloodLvlFinished = false;
    public static String result = "";
    public static boolean isQuestLevel = false;
    public static void secondMissionAccess(TextArea output, TextField input){
        input.setVisible(true);
        output.appendText("Hello my handsome! Maybe you want do something for me? (mission) \n");
        if (GameController.isNpcAvailable && !isSecondMissionOn && !isSecondMissionFinished && FirstQuest.isFirstMissionFinished) {
            input.setOnAction(e -> {
                String inputText = input.getText();
                if (!Objects.equals(inputText, "mission")) {
                    output.appendText("Its wrong word honey! \n");
                    secondMissionAccess(output, input);
                    input.setVisible(false);
                } else {
                    isSecondMissionOn = true;
                    output.appendText("Please find something different in this creepy place... I'm waiting for you. \n");
                    GameController.player.addToInventory(new Gem());
                    isQuestLevel = true;
                }
                input.clear();
                input.setVisible(false);
            });
        }
        }


        public static void secondQuestCrud(TextArea output) {
            if (GameController.isNpcAvailable && isSecondMissionOn && !isSecondMissionFinished && FirstQuest.isFirstMissionFinished && isBloodLvlFinished){
                output.appendText("""
                    Oh, you are alive! Now we can talk like elf and surfer :)\s
                    I enchant sword for you! \s
                    """);
                    for (Item item : GameController.player.getEquipment()){
                        if (item instanceof Sword) {
                            item.setItemValue(item.getItemValue()+100);
                            item.setItemName("enhancedSword");
                            System.out.println(item);
                        }
                    }
                for (Item item : GameController.player.getInventory()){
                    if (item instanceof Sword) {
                        player.setDamage(player.getDamage() + 100);
                        item.setItemName("enhancedSword");
                        System.out.println(item);
                    }
                }
                    output.appendText("Its your upgraded sword");
                isSecondMissionFinished = true;
                }
            }
        }
