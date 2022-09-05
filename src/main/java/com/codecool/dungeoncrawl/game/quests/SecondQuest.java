package com.codecool.dungeoncrawl.game.quests;

import com.codecool.dungeoncrawl.game.controller.GameController;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Objects;

public class SecondQuest {
    public static boolean isSecondMissionFinished = false;
    public static boolean isSecondMissionOn = false;
    public static void secondMissionAccess(TextArea output, TextField input){
        input.setVisible(true);
        output.clear();
        if (GameController.isNpcAvailable && !isSecondMissionOn && !isSecondMissionFinished && FirstQuest.isFirstMissionFinished) {
            output.appendText("Hello my handsome! Maybe you want do something for me? (mission) \n");
            input.setOnAction(e -> {
                String inputText = input.getText();
                if (!Objects.equals(inputText, "mission")) {
                    output.appendText("Its wrong word honey! \n");
                    secondMissionAccess(output, input);
                    input.setVisible(false);
                } else {
                    isSecondMissionOn = true;
                    output.appendText("Please find something different in this creepy place and use it, here you are. Good luck! \n" +
                            "+Mystery gem \n");
                }
                input.clear();
                input.setVisible(false);
            });
        }
        }
}
