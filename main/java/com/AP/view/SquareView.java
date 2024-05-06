package com.AP.view;

import java.awt.*;
import java.util.concurrent.Semaphore;

public class SquareView extends CharacterView{
    Semaphore semaphore=new Semaphore(1);
    private double angleInRadiance=Math.toRadians(1);
    public SquareView(int x, int y, int width, int height, String addressImage) {
        super(x, y, width, height, addressImage);
        setAngles(4,4);
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
        if (numberOfXp==0) {
            graphics2D.drawPolygon(getXs(), getYs(), 4);
        }else {
            graphics2D.drawLine(x,y,x+1,y+1);
        }
        semaphore.release();
    }
    private void setAngleInRadiance(){
        if (angleInRadiance>6.28){
            angleInRadiance=Math.toRadians(1);
        }else angleInRadiance+=Math.toRadians(1);
    }
}
