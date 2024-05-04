package com.AP.view.myComponents;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel implements Paintable{
    private final MyPanel fatherPanel;
    private String address=null;
    public MyLabel(MyPanel panel,int x,int y,int width,int height){
        fatherPanel=panel;
        setLayout(null);
        setBounds(x,y,width,height);
        panel.add(this);
        setVisible(true);
        repaint();
    }
    public MyLabel(MyPanel panel,int x,int y,int width,int height,String address) {
        this(panel,x,y,width,height);
        this.address=address;
    }

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (address!= null){
            setAnimation(g,address,getWidth(),getHeight());
        }
    }
    public MyPanel getFatherPanel() {
        return fatherPanel;
    }
}
