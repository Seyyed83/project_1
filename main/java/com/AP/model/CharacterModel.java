package com.AP.model;

import com.AP.controller.PublicController;
import com.AP.controller.pages.StartPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class CharacterModel{
    private JPanel ClientCharacterPanel;
    private String addressImage;
    private int x,y,width, height,Hp;
    private double movement;
    private CharacterModel otherForCollision;
    private final Rectangle collisionRectangle;
    protected int delayCollisionStopper;
    private boolean isUpCollision,isRightCollision;
    public final static ArrayList<CharacterModel> characters=new ArrayList<>();
    public CharacterModel(int x, int y, int width, int height, String addressImage) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.addressImage = addressImage;
        makeViewCharacter();
        setClientCharacterPanel(StartPage.getGamePanel());
        collisionRectangle=new Rectangle(x,y,width,height);
        characters.add(this);
    }

    protected abstract void makeViewCharacter();

    public String getAddressImage() {
        return addressImage;
    }

    public void setAddressImage(String addressImage) {
        this.addressImage = addressImage;
    }

    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
        PublicController.balanceViewCharacterX(this);
        collisionRectangle.x=x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        PublicController.balanceViewCharacterY(this);
        collisionRectangle.y=y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public double getMovement() {
        return movement;
    }
    public void setMovement(double movement) {
        this.movement = movement;
    }
    public JPanel getClientCharacterPanel() {
        return ClientCharacterPanel;
    }

    public void setClientCharacterPanel(JPanel clientCharacterPanel) {
        this.ClientCharacterPanel = clientCharacterPanel;
    }

    public CharacterModel getOtherForCollision() {
        return otherForCollision;
    }

    public void setOtherForCollision(CharacterModel otherForCollision) {
        this.otherForCollision = otherForCollision;
    }
    public abstract void usualReactCollision(CharacterModel other);

    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    public boolean isUpCollision() {
        return isUpCollision;
    }

    public void setUpCollision(boolean upCollision) {
        isUpCollision = upCollision;
    }

    public boolean isRightCollision() {
        return isRightCollision;
    }

    public void setRightCollision(boolean rightCollision) {
        isRightCollision = rightCollision;
    }
    public abstract void collisionDamage(CharacterModel other);

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
    }
}