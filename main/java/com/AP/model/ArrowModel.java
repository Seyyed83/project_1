package com.AP.model;

import com.AP.controller.PublicController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrowModel extends ClientCharacter{
    private boolean visible;
    public ArrowModel(int x, int y, int width, int height, String addressImage) {
        super(x, y, width, height, addressImage);
        setHp(1);
    }

    @Override
    protected void makeViewCharacter() {
        PublicController.makeArrowView(this,getX(),getY(),getWidth(),getHeight(),"");
    }

    @Override
    public void usualReactCollision(CharacterModel other) {
        //TODO
    }

    @Override
    public void collisionDamage(CharacterModel other) {
        if (other instanceof EnemyCharacter&&visible) {
            other.setHp(other.getHp()-5);
            PublicController.deadCharacter(other);
//            PublicController.WipeOut((MyPanel) getClientCharacterPanel(),other);
        }
    }


    public void setCollisionTimer(){
        setVisible(true);
        Timer shootingStopper=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        shootingStopper.setRepeats(false);
        shootingStopper.start();
    }
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        PublicController.balanceArrowLaserVisible(this);
    }
}
