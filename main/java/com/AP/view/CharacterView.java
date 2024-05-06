package com.AP.view;

import com.AP.controller.pages.StartPage;
import com.AP.view.myComponents.MyPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class CharacterView {
    protected int x, y, width, height,numberOfXp;
    private int[] xs,ys;
    protected Rectangle[] rectangleAngles;
    private boolean isRotated;
    private String addressImage;
    private BufferedImage characterImage;
    private MyPanel characterPanel;
    public MyPanel getCharacterPanel() {
        return characterPanel;
    }

    public CharacterView(int x, int y, int width, int height, String addressImage) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.addressImage = addressImage;
//        try {
//            characterImage = ImageIO.read(new File(addressImage));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        characterPanel= StartPage.getGamePanel();
    }
    public void setCharacterPanel(MyPanel characterPanel){
        this.characterPanel=characterPanel;
        characterPanel.getCharacterList().add(this);
    }
    public abstract void drawCharacter(Graphics2D graphics2D) throws InterruptedException;
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
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public BufferedImage getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(BufferedImage characterImage) {
        this.characterImage = characterImage;
    }

    public boolean isRotated() {
        return isRotated;
    }

    public void setRotated(boolean rotated) {
        isRotated = rotated;
    }
    public int[] getXs() {
        return xs;
    }
    public int[] getYs() {
        return ys;
    }
    public void setAngles(int angle,int a) {
        xs=new int[angle];
        ys=new int[angle];
        state1();
        rectangleAngles=new Rectangle[angle];
        for (int i=0;i< angle;i++){
            rectangleAngles[i]=new Rectangle(xs[i]-(width/a),ys[i]-(height/a),width/(2*a),height/(2*a));
        }
    }
    public void state1() {
        getXs()[0]=x;
        getXs()[1]=x+width;
        getXs()[2]=x+width;
        getXs()[3]=x;
        getYs()[0]=y;
        getYs()[1]=y;
        getYs()[2]=y+height;
        getYs()[3]=y+height;
    }

    public void setRectangleAngles(int[] xAngles,int[] yAngles) {
        for (int i=0;i< xAngles.length;i++){
            rectangleAngles[i].setBounds(xAngles[i]-(width/4),yAngles[i]-(height/4),width/2,height/2);
        }
    }

    public Rectangle[] getRectangleAngles() {
        return rectangleAngles;
    }

    public int getNumberOfXp() {
        return numberOfXp;
    }

    public void setNumberOfXp(int numberOfXp) {
        this.numberOfXp = numberOfXp;
    }
}
