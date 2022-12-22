package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class  SaveGame implements Serializable, Screen {

    InterfaceMenu menu;
    Terrain terrain;
    MyGdxGame game;

    public abstract void LoadSavedGame();
    public abstract void ExitGame();

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
    public SaveGame(InterfaceMenu menu, Terrain terrain, MyGdxGame game) {
        this.menu = menu;
        this.terrain = terrain;
        this.game = game;
    }

    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        // To save the game state:
        FileHandle file = Gdx.files.local("game-state.bin");
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(file.write(false));
            out.writeObject(getGame());
            out.writeObject(getTerrain());
            out.close();
        }
        catch (Exception e){
            System.out.println("Exception Found "+ e);
        }
        // To load the game state:
        try {
            FileHandle file2 = Gdx.files.local("game-state.bin");
            ObjectInputStream in = null;
            in = new ObjectInputStream(file2.read());
            MyGdxGame gameState = (MyGdxGame) in.readObject();
            in.close();
        }
        catch (Exception e){
            System.out.println("Exception Found "+ e);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public InterfaceMenu getMenu() {
        return menu;
    }

    public void setMenu(InterfaceMenu menu) {
        this.menu = menu;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public MyGdxGame getGame() {
        return game;
    }

    public void setGame(MyGdxGame game) {
        this.game = game;
    }
}
