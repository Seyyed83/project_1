package com.AP.model;

public abstract class ClientCharacter extends CharacterModel {
    protected double acceleration = 0.125;
    protected ClientCharacter(int x, int y, int width, int height, String addressImage) {
        super(x, y, width, height, addressImage);
    }
}