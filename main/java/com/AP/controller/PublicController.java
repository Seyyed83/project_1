package com.AP.controller;

import com.AP.model.CharacterModel;
import com.AP.model.EpsilonModel;
import com.AP.model.SquareModel;
import com.AP.view.CharacterView;
import com.AP.view.EpsilonView;
import com.AP.view.SquareView;

import java.util.HashMap;

public abstract class PublicController {
    private static int X,Y;
    private static final HashMap<CharacterModel, CharacterView> modelToView=new HashMap<>();

    public static HashMap<CharacterModel, CharacterView> getModelToView() {
        return modelToView;
    }

    public static void makeEpsilonView(EpsilonModel model){
        modelToView.put(model,EpsilonView.getINSTANCE());
    }
    public static void makeSquareView(SquareModel model,int x,int y,int width,int height,String addressImage){
        modelToView.put(model,new SquareView(x,y,width,height,addressImage));
    }
    private static void getX(CharacterModel model){
        X=model.getX();
    }
    private static void getY(CharacterModel model){
        Y= model.getY();
    }
    public static void balanceViewCharacterX(CharacterModel characterModel){
        getX(characterModel);
        modelToView.get(characterModel).setX(X);
    }
    public static void balanceViewCharacterY(CharacterModel characterModel){
        getY(characterModel);
        modelToView.get(characterModel).setY(Y);
    }
}
