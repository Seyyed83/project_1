package com.AP.model;

import java.awt.geom.Point2D;

public abstract class EnemyCharacter extends CharacterModel {
    double X,Y, coefficient;
    private Point2D characterPoint, targetPoint;
    protected double acceleration = 0.125;
    public EnemyCharacter(int x, int y, int width, int height, String addressImage) {
        super(x, y, width, height, addressImage);
        this.characterPoint = new Point2D.Double(x,y);
        setTarget();
    }
    private void makeMainDelta(){
        X=targetPoint.getX()-characterPoint.getX();
        Y=targetPoint.getY()-characterPoint.getY();
        setMovement(8);
    }

    private void setCoefficient() {
        coefficient=getMovement()/targetPoint.distance(characterPoint);
    }

    protected void moveCharacter(){
        makeMainDelta();
        setCoefficient();
        if (coefficient!= java.lang.Double.POSITIVE_INFINITY) {
            setX(getX() + (int) (coefficient * X));
            setY(getY() + (int) (coefficient * Y));
        }
        characterPoint.setLocation(getX(), getY());
        setTarget();
    }

    protected abstract void setTarget();
    public Point2D getCharacterPoint() {
        return characterPoint;
    }

    public void setCharacterPoint(Point2D characterPoint) {
        this.characterPoint = characterPoint;
    }

    public Point2D getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(Point2D targetPoint) {
        this.targetPoint = targetPoint;
    }
}
