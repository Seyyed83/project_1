package com.AP.view.myComponents;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton implements Paintable{
    String address;
    boolean hasBackgroundImage;
    public MyButton(String label,Dimension size,String address,boolean isFile){
        this(label,size);
        hasBackgroundImage =isFile;
        this.address=address;
    }
    public MyButton(String label,Dimension size){
        setText(label);
        setSize(size);
//        setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (hasBackgroundImage) {
            MyPaint(g,address,getWidth(),getHeight());
        }
    }
}
