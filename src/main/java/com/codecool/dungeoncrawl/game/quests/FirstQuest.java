package com.codecool.dungeoncrawl.game.quests;

import com.codecool.dungeoncrawl.game.Items.Item;
import com.codecool.dungeoncrawl.game.Items.SkeletonSkull;
import com.codecool.dungeoncrawl.game.controller.GameController;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Objects;

import static com.codecool.dungeoncrawl.game.controller.GameController.player;

public class FirstQuest {

    public static boolean isSkullInInventory = false;

    public static boolean isFirstMissionFinished = false;

    public static boolean isFirstMissionOn = false;

    public static void firstMissionAccess(TextArea output, TextField input){
        input.setVisible(true);
        if (GameController.isNpcAvailable && !FirstQuest.isFirstMissionOn && !isFirstMissionFinished) {
            output.appendText("What are you looking for? (mission) \n");
            input.setOnAction(e -> {
                String inputText = input.getText();
                if (!Objects.equals(inputText, "mission")){
                    output.appendText("Wrong answer! \n");
                    firstMissionAccess(output, input);
                } else {
                    output.appendText("Bring me skull, my little friend. Good luck!  \n");
                    FirstQuest.isFirstMissionOn = true;
                }
                input.clear();
                input.setVisible(false);
            });

        } else {
            firstMissionFinish(output, input);
        }

    }

    public static void firstMissionFinish(TextArea output, TextField input) {
        input.setVisible(true);
        if(FirstQuest.isFirstMissionOn && !isFirstMissionFinished) {
            output.appendText("Do you have skull? (yes/no) \n");
            input.setOnAction(e -> {
                String inputText = input.getText();
                if (!Objects.equals(inputText, "yes") && !Objects.equals(inputText, "no")) {
                    output.appendText("Wrong answer motherfucker! \n");
                    input.setVisible(false);
                    firstMissionFinish(output, input);
                }else if (Objects.equals(inputText, "no")) {
                    output.appendText("Back when you get it! \n");
                }
                else {
                    for (Item item : player.getInventory()) {
                        if (item instanceof SkeletonSkull) {
                            isSkullInInventory = true;
                        }
                        if (isSkullInInventory) {
                            output.appendText("You did it! Here is your reward \n");
                            player.setExp(player.calculateLevel() + 2000);
                            output.appendText("+2000 exp \n");
                            isFirstMissionFinished = true;
                            GameMap.removeNpc(GameController.npc);
                            GameController.npc.getCreature().getCell().setType(CellType.FLOOR);
                            GameController.npc.getCreature().getCell().setCreature(null);
                            input.setVisible(false);
                        } else {
                            output.appendText("Back when you get this shit! \n");
                        }
                    }
                }
            });
        }
    }
}
