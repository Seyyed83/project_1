package com.AP.model;

import com.AP.controller.Constant;
import com.AP.controller.MovementController;
import com.AP.controller.PublicController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpsilonModel extends ClientCharacter {
    private static EpsilonModel INSTANCE;
    private boolean isMoveUpTimer, isMoveDownTimer, isMoveRightTimer, isMoveLeftTimer;
    private JPanel epsilonPanel;

    public static EpsilonModel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new EpsilonModel(325, 310, Constant.EPSILON_RADIUS, Constant.EPSILON_RADIUS, "src/main/java/com/AP/Images/pngtree-blue-pink-neon-border-circle-png-image_8666308.png");
        }
        return INSTANCE;
    }

    private EpsilonModel(int x, int y, int width, int height, String addressImage) {
        super(x, y, width, height, addressImage);
        delayCollisionStopper =12;
        setHp(100);
    }
    private final Timer moveTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (acceleration < 6) {
                acceleration *= 1.2;
            }
            if (isMoveUpTimer && !isMoveDownTimer&&getY()>5) {
                if (isMoveRightTimer&&!isMoveLeftTimer){
                    setY((int) (getY() + (-Constant.EPSILON_SIDE_DIRECTION-acceleration) ));
                    setX((int) (getX() + (Constant.EPSILON_SIDE_DIRECTION+acceleration) ));
                } else if (isMoveLeftTimer&&!isMoveRightTimer) {
                    setY((int) (getY() + (-Constant.EPSILON_SIDE_DIRECTION-acceleration) ));
                    setX((int) (getX() + (-Constant.EPSILON_SIDE_DIRECTION-acceleration) ));
                } else {
                    setY(getY() +(int) (-Constant.EPSILON_MAIN_DIRECTION-acceleration));
                }
                getClientCharacterPanel().repaint();
            } else if (isMoveDownTimer && !isMoveUpTimer&&getY()<315) {
                if (isMoveRightTimer&&!isMoveLeftTimer) {
                    setY((int) (getY() + (Constant.EPSILON_SIDE_DIRECTION + acceleration) ));
                    setX((int) (getX() + (Constant.EPSILON_SIDE_DIRECTION + acceleration) ));
                } else if (isMoveLeftTimer&&!isMoveRightTimer) {
                    setY((int) (getY() + (Constant.EPSILON_SIDE_DIRECTION + acceleration) ));
                    setX((int) (getX() - Constant.EPSILON_SIDE_DIRECTION - acceleration ));
                } else {
                    setY(getY() + Constant.EPSILON_MAIN_DIRECTION + (int) acceleration);
                }
                getClientCharacterPanel().repaint();
            } else if (isMoveRightTimer && !isMoveLeftTimer&&getX()<508) {
                setX(getX() + Constant.EPSILON_MAIN_DIRECTION + (int) acceleration);
                getClientCharacterPanel().repaint();
            } else if (isMoveLeftTimer && !isMoveRightTimer&&getX()>163) {
                setX(getX() - Constant.EPSILON_MAIN_DIRECTION - (int) acceleration);
                getClientCharacterPanel().repaint();
            }
            if (!isMoveUpTimer&&!isMoveDownTimer&&!isMoveRightTimer&&!isMoveLeftTimer) acceleration=0.125;
        }
    });

    public void startMoveTimer() {
        moveTimer.start();
    }
    public void setMoveUpTimer(boolean moveUpTimer) {
        isMoveUpTimer = moveUpTimer;
    }

    public void setMoveDownTimer(boolean moveDownTimer) {
        isMoveDownTimer = moveDownTimer;
    }

    public void setMoveRightTimer(boolean moveRightTimer) {
        isMoveRightTimer = moveRightTimer;
    }

    public void setMoveLeftTimer(boolean moveLeftTimer) {
        isMoveLeftTimer = moveLeftTimer;
    }
    private void setCollisionTimer() {
        Timer collisionTimer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovementController.collisionDirection(getINSTANCE());
            }
        });
        Timer collisionStopper=new Timer(delayCollisionStopper, e -> collisionTimer.stop());
        collisionTimer.start();
        collisionStopper.setRepeats(false);
        collisionStopper.start();
    }

    @Override
    protected void makeViewCharacter() {
        PublicController.makeEpsilonView(this);
    }

    @Override
    public void usualReactCollision(CharacterModel other) {
        if (other instanceof EnemyCharacter){
            setCollisionTimer();
            if (other.isDead()){

            }
        }
    }

    @Override
    public void collisionDamage(CharacterModel other) {
        //TODO
    }
}
