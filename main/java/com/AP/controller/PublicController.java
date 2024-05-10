package com.AP.controller;

import com.AP.model.ArrowModel;
import com.AP.model.CharacterModel;
import com.AP.model.EpsilonModel;
import com.AP.model.SquareModel;
import com.AP.view.ArrowView;
import com.AP.view.CharacterView;
import com.AP.view.EpsilonView;
import com.AP.view.SquareView;
import com.AP.view.myComponents.MyPanel;

import javax.swing.*;
import java.util.HashMap;

public abstract class PublicController {
    private static int X, Y;
    private static final HashMap<CharacterModel, CharacterView> modelToView = new HashMap<>();
    private static final HashMap<CharacterView, Integer> charactersHp = new HashMap<>();

    public static HashMap<CharacterModel, CharacterView> getModelToView() {
        return modelToView;
    }

    public static void makeEpsilonView(EpsilonModel model) {
        modelToView.put(model, EpsilonView.getINSTANCE());
        charactersHp.put(EpsilonView.getINSTANCE(), model.getHp());
    }

    public static void makeSquareView(SquareModel model, int x, int y, int width, int height, String addressImage) {
        SquareView squareView = new SquareView(x, y, width, height, addressImage);
        modelToView.put(model, new SquareView(x, y, width, height, addressImage));
        charactersHp.put(squareView, model.getHp());
    }

    public static void makeArrowView(ArrowModel model, int x, int y, int width, int height, String addressImage) {
        ArrowView arrowView = new ArrowView(x, y, width, height, addressImage);
        modelToView.put(model, arrowView);
        charactersHp.put(arrowView, model.getHp());
    }

    private static void getX(CharacterModel model) {
        X = model.getX();
    }

    private static void getY(CharacterModel model) {
        Y = model.getY();
    }

    public static void balanceViewCharacterX(CharacterModel characterModel) {
        getX(characterModel);
        modelToView.get(characterModel).setX(X);
    }

    public static void balanceViewCharacterY(CharacterModel characterModel) {
        getY(characterModel);
        modelToView.get(characterModel).setY(Y);
    }

    public static void balanceArrowLaserVisible(ArrowModel model) {
        ((ArrowView) modelToView.get(model)).setVisible(model.isVisible());
    }

    public static void setLabelText(JLabel label, String string) {
        label.setText(string);
    }

    public static void deadCharacter(CharacterModel model) {
        if (model.getHp() <= 0) {
//            if (model instanceof EpsilonModel) {
//                //todo
//            }
            PublicController.modelToView.get(model).setDead(true);
            model.setDead(true);
        }
    }
    public static void WipeOut(MyPanel panel, CharacterModel model){
        panel.getCharacterList().remove(modelToView.get(model));
    }
}
