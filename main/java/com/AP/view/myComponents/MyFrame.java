package com.AP.view.myComponents;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private final JPanel basePanel;
    public MyFrame(int width ,int height){
        setSize(new Dimension(width,height));
        setVisible(true);
        basePanel=(JPanel) this.getContentPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public MyFrame (Dimension dimension){
        this(dimension.width,dimension.height);
    }
    public void myAddAll(Component ... components){
        for (Component component : components){
            basePanel.add(component);
        }
    }
    public JPanel getBasePanel(){
        return basePanel;
    }
}
