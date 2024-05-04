package com.AP.view.myComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public interface Paintable {
    default void MyPaint(Graphics g,String address,int width,int height){
        BufferedImage background;
        try {
            background= ImageIO.read(new File(address));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Graphics2D graphics2D=(Graphics2D) g;
        graphics2D.drawImage(background,0,0,width,height,null);
    }
    default void setAnimation(Graphics g,String address,int width,int height){
        ImageIcon background;
        try {
            background=new ImageIcon(new File(address).toURI().toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        background.paintIcon((MyLabel)this,g,0,0);
    }
}
