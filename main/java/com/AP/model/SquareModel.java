package com.AP.model;

import com.AP.controller.MovementController;
import com.AP.controller.PublicController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import static com.AP.controller.Constant.MONITOR_SIZE_DIMENSION;

public class SquareModel extends EnemyCharacter{

    public SquareModel(int x, int y, int width, int height, String addressImage) {
        super(x, y, width, height, addressImage);
        makeViewCharacter();
    }

    @Override
    protected void makeViewCharacter() {
        PublicController.makeSquareView(this, getX(), getY(),getWidth(),getHeight(),getAddressImage());
    }

    @Override
    protected void setTarget() {
        setTargetPoint(new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY()));
        setHp(10);
    }

    Timer moveTimer=new Timer(30, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (acceleration<3){
                acceleration*=1.1;
            }
            setMovement(getMovement()*acceleration);
            moveCharacter();
            getClientCharacterPanel().repaint();
        }
    });
    public void startMoveTimer(){
        moveTimer.start();
    }
    public void setCollisionTimer(){
        Timer collisionTimer=new Timer(2, e -> {
//                setX(getX() + Constant.DELTA_COLLISION);
//                setY(getY() + Constant.DELTA_COLLISION);
            MovementController.collisionDirection(this);
            if (getX()> MONITOR_SIZE_DIMENSION.width) {
                setX(MONITOR_SIZE_DIMENSION.width);
            }
            else if (getX()<0) {
                setX(0);
            }
            if (getY()> MONITOR_SIZE_DIMENSION.height){
                setY(MONITOR_SIZE_DIMENSION.height);
            }
            else if(getY()<0){
                setY(0);
            }

        });
        Timer collisionStopper=new Timer(delayCollisionStopper, e -> collisionTimer.stop());
        collisionTimer.start();
        collisionStopper.setRepeats(false);
        collisionStopper.start();
    }
    @Override
    public void usualReactCollision(CharacterModel other) {
        if (other instanceof EnemyCharacter){
            delayCollisionStopper =30;
        }else {
            delayCollisionStopper =90;
        }
        setCollisionTimer();
    }

    @Override
    public void collisionDamage(CharacterModel other) {
        if (other instanceof EpsilonModel){
            EpsilonModel.getINSTANCE().setHp(EpsilonModel.getINSTANCE().getHp()-6);
        }
    }
}
