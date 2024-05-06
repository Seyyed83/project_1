package com.AP.controller;

import com.AP.model.CharacterModel;

import java.awt.*;

public abstract class MovementController {
    public static void handelCollision(){
        for (int i = 0; i< CharacterModel.characters.size(); i++){
            for (int j=i+1;j<CharacterModel.characters.size();j++) {
                if (CharacterModel.characters.get(i).getCollisionRectangle().intersects(CharacterModel.characters.get(i + 1).getCollisionRectangle())) {
                    collisionDamage(CharacterModel.characters.get(i),CharacterModel.characters.get(j));
                    setCollisionBoolean(CharacterModel.characters.get(i),CharacterModel.characters.get(j));
                    CharacterModel.characters.get(j).usualReactCollision(CharacterModel.characters.get(i));
                    CharacterModel.characters.get(i).usualReactCollision(CharacterModel.characters.get(j));
                    if (i==0){
                        CharacterModel.characters.get(i).usualReactCollision(CharacterModel.characters.get(j));
                    }
                }
            }
        }
    }
    private static void setCollisionBoolean(CharacterModel c1,CharacterModel c2){
        Rectangle r1=c1.getCollisionRectangle(),r2=c2.getCollisionRectangle();
        c1.setRightCollision(false);
        c2.setRightCollision(false);
        c1.setUpCollision(false);
        c2.setUpCollision(false);
        c1.setDownCollision(false);
        c2.setDownCollision(false);
        c1.setLeftCollision(false);
        c2.setLeftCollision(false);
        if (r1.getCenterX()-r2.getCenterX()>0){
            c1.setLeftCollision(true);
            c2.setRightCollision(true);
        }else {
            c1.setRightCollision(true);
            c2.setLeftCollision(false);
        }if (r1.getCenterY()-r2.getCenterY()>0){
            c1.setDownCollision(true);
            c2.setUpCollision(true);
        }else {
            c1.setUpCollision(true);
            c2.setDownCollision(true);
        }
    }
    private static void collisionDamage(CharacterModel c1,CharacterModel c2){
        Rectangle[] rc1=PublicController.getModelToView().get(c1).getRectangleAngles(),rc2=PublicController.getModelToView().get(c2).getRectangleAngles();
        if (rc1!=null){
            for (Rectangle rectangle : rc1) {
                if (c2.getCollisionRectangle().intersects(rectangle)){
                    c1.collisionDamage(c2);
                }
            }
        }
        if (rc2!=null){
            for (Rectangle rectangle : rc2) {
                if (c1.getCollisionRectangle().intersects(rectangle)) {
                    c2.collisionDamage(c1);
                }
            }
        }
    }
    public static void  collisionDirection(CharacterModel model){
        if (model.isUpCollision()){
            model.setY(model.getY()-Constant.DELTA_COLLISION);
        }if (model.isDownCollision()){
            model.setY(model.getY()+Constant.DELTA_COLLISION);
        }
        if (model.isRightCollision()){
            model.setX(model.getX()-Constant.DELTA_COLLISION);
        }if (model.isLeftCollision()){
            model.setX(model.getX()+Constant.DELTA_COLLISION);
        }
    }
}
