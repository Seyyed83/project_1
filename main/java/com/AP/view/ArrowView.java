package com.AP.view;

import java.awt.*;

public class ArrowView extends CharacterView{
    private boolean visible;
    public ArrowView(int x, int y, int width, int height, String addressImage) {
        super(x, y, width, height, addressImage);
        setAngles(4,1);
    }

    @Override
    public void drawCharacter(Graphics2D graphics2D) throws InterruptedException {
        graphics2D.setColor(Color.BLUE);
        graphics2D.setStroke(new BasicStroke(4));
        graphics2D.drawLine(EpsilonView.getINSTANCE().x, EpsilonView.getINSTANCE().y, x,y);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
