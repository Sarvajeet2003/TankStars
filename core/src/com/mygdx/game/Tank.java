package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
public class Tank extends Exception implements Cloneable {

    private String TankName;
    private int Health;
    private int Fuel;

    public Texture getTankImage() {
        return TankImage;
    }

    public void setTankImage(Texture tankImage) {
        TankImage = tankImage;
    }

    public Texture getTankImageMirror() {
        return TankImageMirror;
    }

    public void setTankImageMirror(Texture tankImageMirror) {
        TankImageMirror = tankImageMirror;
    }

    private Texture TankImage;
    private Texture TankImageMirror;
    Tank(){
    }

    public int getFuel() {
        return Fuel;
    }

    public void setFuel(int fuel) {
        Fuel = fuel;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getTankName() {
        return TankName;
    }

    public void setTankName(String tankName) {
        TankName = tankName;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }


    public <UserNotFoundException> void UserNowtFoundException(){
        try{
            Tank t1 = new Tank();
        }
        catch (Exception e){
            System.out.println("User not found "+e);
        }
    }


}
