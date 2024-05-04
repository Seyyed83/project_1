package com.AP.view.myComponents;

import com.AP.view.CharacterView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.AP.controller.Constant.MONITOR_SIZE_DIMENSION;


public class

MyPanel extends JPanel implements Paintable{
    String address;
    boolean hasBackgroundImage;
    private final ArrayList<CharacterView> characters=new ArrayList<>();

    public MyPanel() {
        setLocation(new Point(0, 0));
        setLayout(null);
        setVisible(true);
    }

    public MyPanel(String address) {
        this();
        hasBackgroundImage = true;
        this.address=address;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D=(Graphics2D) g;
        if (hasBackgroundImage) {
            MyPaint(g,address,getWidth(),getHeight());
        }
        for (CharacterView c:characters){
            try {
                c.drawCharacter(graphics2D);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void setMonitorSize(){
        setSize(MONITOR_SIZE_DIMENSION);
    }
    public void myAddAll(Component ... components){
        for (Component component:components){
            add(component);
        }
    }
    public ArrayList getCharacterList(){
        return characters;
    }
}
