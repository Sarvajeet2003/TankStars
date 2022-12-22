package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class ShowCase implements Screen {

    MyGdxGame game;
    Texture TankBG;
    private Stage stage;
    ArrayList<Tank> TankList = new ArrayList<>();
    private Music BackGroundMusic;
    private Texture Play;
    private Texture SELECT;
    private Sound Click;
    private OrthographicCamera camera;
    int i;
    int j;

    private Skin skin;
    WelcomePage welcomePage;

    public MyGdxGame getGame() {
        return game;
    }

    public void setGame(MyGdxGame game) {
        this.game = game;
    }

    public ShowCase(MyGdxGame game, WelcomePage welcome) throws CloneNotSupportedException {
        this.game = game;
        this.SetImages();
        this.welcomePage = welcome;
        TankBG = new Texture(Gdx.files.internal("images.jpeg"));
        Play = new Texture(Gdx.files.internal("Play.png"));
        SELECT = new Texture(Gdx.files.internal("SELECT.png"));
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Click = Gdx.audio.newSound(Gdx.files.internal("Click.mp3"));
        i = 0;

        skin = new Skin(Gdx.files.internal("metal/skin/metal-ui.json"));
        ImageButton buttonL = new ImageButton(skin);
        buttonL.setSize(25,25);
        buttonL.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("scrollbar-left.png")));
        buttonL.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("scrollbar-left-pressed.png")));
        buttonL.setPosition(37,150);
        buttonL.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                getClick().play();
                if(i==0){
                    i = 2;
                }
                else {
                    i--;
                }
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(buttonL);
        j = 0;

        ImageButton buttonL2 = new ImageButton(skin);
        buttonL2.setSize(25,25);
        buttonL2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("scrollbar-left.png")));
        buttonL2.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("scrollbar-left-pressed.png")));
        buttonL2.setPosition(373,150);
        buttonL2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                getClick().play();
                if(j==0){
                    j = 2;
                }
                else {
                    j--;
                }
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y , int pointer , int button){
                return true;
            }
        });

        stage.addActor(buttonL2);





        ImageButton buttonR = new ImageButton(skin);
        buttonR.setSize(25,25);
        buttonR.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("scrollbar-right.png")));
        buttonR.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("scrollbar-right-pressed.png")));
        buttonR.setPosition(270,150);
        buttonR.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                getClick().play();
                if(i==2){
                    i = 0;
                }
                else {
                    i++;
                }

            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y , int pointer , int button){
                return true;
            }
        });
        stage.addActor(buttonR);
        ImageButton buttonR2 = new ImageButton(skin);
        buttonR2.setSize(25,25);
        buttonR2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("scrollbar-right.png")));
        buttonR2.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("scrollbar-right-pressed.png")));
        buttonR2.setPosition(610,150);
        buttonR2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                getClick().play();
                if(j==2){
                    j = 0;
                }
                else {
                    j++;
                }
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y , int pointer , int button){
                return true;
            }
        });
        stage.addActor(buttonR2);

    }
    public void SetImages() throws CloneNotSupportedException {
        Tank t1 = new Tank();
        Tank t2 = new Tank();
        Tank t3 = new Tank();
        Tank t4 = (Tank) t3.clone();
        t1.setHealth(100);
        t2.setHealth(100);
        t3.setHealth(100);
        t1.setFuel(100);
        t2.setFuel(100);
        t3.setFuel(100);
        t1.setTankImage(new Texture(Gdx.files.internal("Specter.png")));
        t2.setTankImage(new Texture(Gdx.files.internal("AC.png")));
        t3.setTankImage(new Texture(Gdx.files.internal("AD.png")));
        t1.setTankImageMirror(new Texture(Gdx.files.internal("Mirror1.png")));
        t2.setTankImageMirror(new Texture(Gdx.files.internal("Mirror2.png")));
        t3.setTankImageMirror(new Texture(Gdx.files.internal("Mirror3.png")));
        TankList.add(t1);
        TankList.add(t2);
        TankList.add(t3);
        TankList.add(t4);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(TankBG, 0,0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
        game.batch.draw(Play, 238, 14, 200,  80);
        game.batch.draw(SELECT, 91, 164, 450,  300);
//        System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
        getGame().batch.draw(TankList.get(i).getTankImage(),60,100,210,132);
        getGame().batch.draw(TankList.get(j).getTankImageMirror(),400,100,210,132);
        game.batch.end();
        stage.act();
        stage.draw();
        if(Gdx.input.isTouched() && Gdx.input.getX()>259 && Gdx.input.getX()<403 && Gdx.input.getY()>412 && Gdx.input.getY()<434){
            Terrain t1 = new Terrain(getGame(),this,this.i,this.j);
            getGame().setScreen(t1);
            dispose();
        }
    }

    public Texture getPlay() {
        return Play;
    }

    public Texture getSELECT() {
        return SELECT;
    }

    public void setSELECT(Texture SELECT) {
        this.SELECT = SELECT;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public void setPlay(Texture play) {
        Play = play;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Music getBackGroundMusic() {
        return BackGroundMusic;
    }

    public void setBackGroundMusic(Music backGroundMusic) {
        BackGroundMusic = backGroundMusic;
    }
    public Sound getClick() {
        return Click;
    }

    public void setClick(Sound click) {
        Click = click;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
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
}
