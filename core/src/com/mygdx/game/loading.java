package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class loading implements Screen{
    OrthographicCamera camera;

    SpriteBatch batch;
    MyGdxGame game;
    private Texture gif;
    private Texture bg;
    private TextureRegion region;
    BitmapFont font;

    public Texture getGif() {
        return gif;
    }

    public void setGif(Texture gif) {
        this.gif = gif;
    }
    public Texture getBg() {
        return bg;
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }
    public loading(MyGdxGame game) {
        this.game = game;
        setBg(new Texture(Gdx.files.internal("loading.jpg")));
        setRegion(new TextureRegion(getBg(), 0, 0, 0 ,  0));
        setGif(new Texture(Gdx.files.internal("Loadingimg.jpeg")));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 0);
        camera.update();
        camera.setToOrtho(false, getBg().getWidth(), getBg().getHeight());
        game.batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont();
        game.batch.begin();
        game.batch.draw(getBg(), 0,0, 416, 208);
        game.batch.draw(getGif(), 0,0, 416, 150);
        game.font.draw(game.batch, "Press Enter To Continue",50,50);
        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            WelcomePage w1 = new WelcomePage(getGame(), this);
            getGame().setScreen(w1);
            dispose();
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
    public MyGdxGame getGame() {
        return game;
    }

    public void setGame(MyGdxGame game) {
        this.game = game;
    }



}