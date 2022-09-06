package com.codecool.dungeoncrawl.game.quests;

import com.codecool.dungeoncrawl.game.Items.Item;
import com.codecool.dungeoncrawl.game.Items.SkeletonSkull;
import com.codecool.dungeoncrawl.game.creatures.Npc;
import com.codecool.dungeoncrawl.game.map.CellType;
import com.codecool.dungeoncrawl.game.map.GameMap;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Objects;

import static com.codecool.dungeoncrawl.game.controller.GameController.player;

public class FirstQuest {
    public static Npc npc;

    public static boolean isFirstNpcAvailable = false;

    public static boolean isSkullInInventory = false;

    public static boolean isFirstMissionFinished = false;

    public static boolean isFirstMissionOn = false;

    public static void firstMissionAccess(TextArea output, TextField input){
        input.setVisible(true);
        if (isFirstNpcAvailable && !FirstQuest.isFirstMissionOn && !isFirstMissionFinished) {
            output.appendText("What you want? (mission) \n");
            input.setOnAction(e -> {
                String inputText = input.getText();
                if (!Objects.equals(inputText, "mission")){
                    output.appendText("Wrong answer motherfucker! \n");
                    firstMissionAccess(output, input);
                } else {
                    output.appendText("Give me skull suko and I will give you some doświadczenie albo nie wiem, baj baj \n");
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
                    firstMissionFinish(output, input);
                }else if (Objects.equals(inputText, "no")) {
                    output.appendText("Back when you get it! \n");
                }
                else {
                    for (Item item : player.getInventory()) {
                        if (item instanceof SkeletonSkull) {
                            isSkullInInventory = true;
                            // TODO usuwanie itemu po wykonaniu questa
//                            List<Item> toRemove = new ArrayList();
//                            for (Item item : player.getInventory()) {
//                                if (item instanceof SkeletonSkull) {
//                                    toRemove.add(item);
//                                }
//                            }
//                            player.getInventory().removeAll(toRemove);
                        }
                        if (isSkullInInventory) {
                            output.appendText("You did it! Here is your reward \n");
                            player.setExp(player.getExp() + 2000);
                            output.appendText("+2000 exp");
                            isFirstMissionFinished = true;
                            GameMap.removeNpc(npc);
                            npc.getCreature().getCell().setType(CellType.FLOOR);
                            npc.getCreature().getCell().setCreature(null);
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
