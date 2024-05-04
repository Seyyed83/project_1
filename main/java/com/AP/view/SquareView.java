package com.AP.view;

import java.awt.*;
import java.util.concurrent.Semaphore;

public class SquareView extends CharacterView{
    Semaphore semaphore=new Semaphore(1);
    private double angleInRadiance=Math.toRadians(1);
    public SquareView(int x, int y, int width, int height, String addressImage) {
        super(x, y, width, height, addressImage);
        setAngles(4);
    }
    @Override
    public void drawCharacter(Graphics2D graphics2D) {
        setRectangleAngles(getXs(),getYs());
        try {
            semaphore.acquire(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        state1();
        graphics2D.setColor(Color.RED);
        graphics2D.setStroke(new BasicStroke(4));
        setAngleInRadiance();
        if (isRotated()) {
            graphics2D.rotate(angleInRadiance, x + (double) width / 2, y + (double) height / 2);
        }
        graphics2D.drawPolygon(getXs(),getYs(),4);
        semaphore.release();
    }
    private void state1() {
        getXs()[0]=x;
        getXs()[1]=x+width;
        getXs()[2]=x+width;
        getXs()[3]=x;
        getYs()[0]=y;
        getYs()[1]=y;
        getYs()[2]=y+height;
        getYs()[3]=y+height;
    }
    private void setAngleInRadiance(){
        if (angleInRadiance>6.28){
            angleInRadiance=Math.toRadians(1);
        }else angleInRadiance+=Math.toRadians(1);
    }
}
