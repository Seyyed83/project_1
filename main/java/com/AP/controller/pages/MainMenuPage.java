package com.AP.controller.pages;

import com.AP.controller.Constant;
import com.AP.view.myComponents.MyButton;
import com.AP.view.myComponents.MyFrame;
import com.AP.view.myComponents.MyPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MainMenuPage {
    protected void startGame() {
        MyFrame mainMenuPanel = new MyFrame(Constant.MONITOR_SIZE_DIMENSION);
        mainMenuPanel.setTitle("WINDOW KILL");
        mainMenuPanel.setResizable(false);
        setMainMenuPanel(mainMenuPanel).repaint();
    }
    private MyPanel setMainMenuPanel(MyFrame menuFrame) {
        MyPanel menuPanel = new MyPanel("src/main/java/com/AP/Images/6514496427f55dc6b6c7bf9d_8389488190400511845.jpg");
        menuPanel.setMonitorSize();
        menuPanel.myAddAll(setMainMenuButton());
        menuFrame.add(menuPanel);
        return menuPanel;
    }

    private MyButton[] setMainMenuButton() {
        MyButton exitButton = getExitButton();
        MyButton startButton = getStartButton();
        MyButton settingButton = new MyButton(
                "SETTING", Constant.BUTTON_DEFAULT_DIMENSION, "src/main/java/com/AP/Images/SETTING-4-12-2024.jpg", true);
        settingButton.setLocation(new Point((Constant.MONITOR_SIZE_DIMENSION.width * 46) / 100, (Constant.MONITOR_SIZE_DIMENSION.height * 42) / 100));

        MyButton tutorialButton = new MyButton(
                "TUTORIAL", Constant.BUTTON_DEFAULT_DIMENSION, "src/main/java/com/AP/Images/TUTORIAL-4-12-2024.jpg", true);
        tutorialButton.setLocation(new Point((Constant.MONITOR_SIZE_DIMENSION.width * 46) / 100, (Constant.MONITOR_SIZE_DIMENSION.height * 51) / 100));

        MyButton skillTreeButton = new MyButton(
                "SKILL_TREE", Constant.BUTTON_DEFAULT_DIMENSION, "src/main/java/com/AP/Images/SKILL-TREE-4-12-2024.jpg", true);
        skillTreeButton.setLocation(new Point((Constant.MONITOR_SIZE_DIMENSION.width * 46) / 100, (Constant.MONITOR_SIZE_DIMENSION.height * 60) / 100));

        return new MyButton[]{exitButton, startButton, settingButton, tutorialButton, skillTreeButton};
    }

    private static MyButton getStartButton() {
        MyButton startButton = new MyButton(
                "START", new Dimension(200, 120), "src/main/java/com/AP/Images/START-4-12-2024.jpg", true);
        startButton.setLocation(new Point((Constant.MONITOR_SIZE_DIMENSION.width * 43) / 100, (Constant.MONITOR_SIZE_DIMENSION.height * 24) / 100));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartPage.startGamePanel();
            }
        });
        return startButton;
    }

    private static MyButton getExitButton() {
        MyButton exitButton = new MyButton(
                "Exit", Constant.BUTTON_DEFAULT_DIMENSION,
                "src/main/java/com/AP/Images/EXIT-4-12-2024.jpg",
                true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return exitButton;
    }
}
