package com.AP.view;

import com.AP.controller.Constant;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EpsilonView extends CharacterView{
    private static EpsilonView instance;
    private EpsilonView(int x, int y, int width, int height, String addressImage) {
        super(x, y, width, height, addressImage);
    }

    @Override
    public void drawCharacter(Graphics2D graphics2D) {
        if (getXs()!=null){
            setRectangleAngles(getXs(),getYs());
        }
        graphics2D.setColor(Color.BLUE);
        graphics2D.setStroke(new BasicStroke(4));
        graphics2D.draw(new Ellipse2D.Double(x,y, Constant.EPSILON_RADIUS,Constant.EPSILON_RADIUS));
    }

    public static EpsilonView getINSTANCE(){
        if (instance==null){
            instance=new EpsilonView(325,310, Constant.EPSILON_RADIUS,Constant.EPSILON_RADIUS,"src/main/java/com/AP/Images/pngtree-blue-pink-neon-border-circle-png-image_8666308.png");
        }
        return instance;
    }
}
