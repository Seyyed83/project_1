package com.AP.controller.pages;

import com.AP.controller.Constant;
import com.AP.controller.MovementController;
import com.AP.controller.PublicController;
import com.AP.model.ArrowModel;
import com.AP.model.CharacterModel;
import com.AP.model.EpsilonModel;
import com.AP.model.SquareModel;
import com.AP.view.EpsilonView;
import com.AP.view.myComponents.MyFrame;
import com.AP.view.myComponents.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class StartPage {
    private final static EpsilonModel epsilonModel = EpsilonModel.getINSTANCE();
    private final static EpsilonView epsilonView = EpsilonView.getINSTANCE();
    private static final MyFrame gameFrame = new MyFrame(Constant.MONITOR_SIZE_DIMENSION);
    private static final MyPanel gamePanel = new MyPanel();
    public static final JLabel epsilonHealth=new JLabel("♥ : "+epsilonModel.getHp());
    public static final JLabel epsilonXP=new JLabel("🍽 : "+epsilonModel.getXP());
    private static final Timer collisionCharacterTimer=new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MovementController.handelCollision();
        }
    });
    public static MyFrame getGameFrame() {
        return gameFrame;
    }

    public static MyPanel getGamePanel() {
        return gamePanel;
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException interrupted) {
            interrupted.printStackTrace();
        }
    }

    private static void minimizingWindows() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.delay(20);
            robot.keyPress(KeyEvent.VK_D);
            robot.delay(20);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.delay(20);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.delay(20);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_WINDOWS);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setGameFrameAndGamePanel() {
        gameFrame.setBounds(Constant.MONITOR_SIZE_DIMENSION.width / 4, 0, 700, 700);
        gameFrame.setTitle("GAME");
        gameFrame.getBasePanel().setLayout(new FlowLayout(FlowLayout.CENTER));
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gamePanel.setPreferredSize(new Dimension(700, 700));
        gamePanel.setBackground(Color.BLACK);
        epsilonView.setCharacterPanel(getGamePanel());
        gameFrame.myAddAll(gamePanel);
        gameFrame.setResizable(false);
        setResize(gameFrame, gamePanel, 5, epsilonModel);
        epsilonHealth.setVisible(true);
        epsilonHealth.setBounds(190,10,80,20);
        gamePanel.add(epsilonHealth);
        epsilonXP.setVisible(true);
        epsilonXP.setBounds(270,10,80,20);
        gamePanel.add(epsilonXP);
        gamePanel.repaint();
        sleep(300);
        Timer resizeTimer=new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameFrame.getWidth()>400) {
                    setResize(gameFrame, gamePanel, 5, epsilonModel);
                }
            }
        });
        resizeTimer.start();
    }

    private static boolean windowSizeChecker(Component component, int width, int height) {
        return component.getWidth() > width && component.getHeight() > height;
    }

    private static void setResize(JFrame frame, JPanel panel, int resize, CharacterModel... charactersModel) {
        frame.setBounds(gameFrame.getX() + resize, gameFrame.getY() + resize, gameFrame.getWidth() - (2 * resize), gameFrame.getHeight() - (2 * resize));
        panel.setSize(gameFrame.getWidth(), gameFrame.getHeight());
        for (CharacterModel character : charactersModel) {
            character.setY(epsilonModel.getY() - resize);
        }
    }

    private static void setGameFrameListener() {
        epsilonModel.startMoveTimer();
        gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W) {
                    epsilonModel.setMoveUpTimer(true);
                } else if (keyCode == KeyEvent.VK_S) {
                    epsilonModel.setMoveDownTimer(true);
                } else if (keyCode == KeyEvent.VK_D) {
                    epsilonModel.setMoveRightTimer(true);
                } else if (keyCode == KeyEvent.VK_A) {
                    epsilonModel.setMoveLeftTimer(true);
                }
                gamePanel.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W) {
                    epsilonModel.setMoveUpTimer(false);
                } else if (keyCode == KeyEvent.VK_S) {
                    epsilonModel.setMoveDownTimer(false);
                } else if (keyCode == KeyEvent.VK_D) {
                    epsilonModel.setMoveRightTimer(false);
                } else if (keyCode == KeyEvent.VK_A) {
                    epsilonModel.setMoveLeftTimer(false);
                }
                gamePanel.repaint();
            }
        });
    }

    public static void startGamePanel() {
        minimizingWindows();
        try {
            SwingUtilities.invokeLater(() -> {
                setGameFrameAndGamePanel();
                SquareModel squareModel=new SquareModel(20,20, Constant.SQUARE_SIDE,Constant.SQUARE_SIDE,"src/main/java/com/AP/Images/square.png");
                PublicController.getModelToView().get(squareModel).setCharacterPanel(gamePanel);
                SquareModel squareModel1=new SquareModel(100,20,Constant.SQUARE_SIDE,Constant.SQUARE_SIDE,"src/main/java/com/AP/Images/square.png");
                PublicController.getModelToView().get(squareModel1).setCharacterPanel(gamePanel);
                SquareModel squareModel2=new SquareModel(100,100,Constant.SQUARE_SIDE,Constant.SQUARE_SIDE,"src/main/java/com/AP/Images/square.png");
                PublicController.getModelToView().get(squareModel2).setCharacterPanel(gamePanel);

                ArrowModel arrowModel=new ArrowModel(0,0, Constant.ARROW_SIDE,Constant.ARROW_SIDE,"");
                PublicController.getModelToView().get(arrowModel).setCharacterPanel(gamePanel);
                PublicController.getModelToView().get(arrowModel).getCharacterPanel().getCharacterList().remove(arrowModel);

                gamePanel.repaint();
                epsilonModel.setClientCharacterPanel(gamePanel);
                setGameFrameListener();
                squareModel.startMoveTimer();
                squareModel1.startMoveTimer();
                squareModel2.startMoveTimer();

                gamePanel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton()==MouseEvent.BUTTON3 && !arrowModel.isVisible()){
                            arrowModel.setX(e.getX());
                            arrowModel.setY(e.getY());
                            arrowModel.setCollisionTimer();
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

                collisionCharacterTimer.start();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setEpsilonHealth(){
        epsilonHealth.setText("♥ : "+epsilonModel.getHp());
    }
}
