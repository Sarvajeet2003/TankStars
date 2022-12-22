package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class InterfaceMenu implements Screen {

    MyGdxGame game;
    private Music BackGroundMusic;
    private Texture BGImage;
    private WelcomePage welcomePage;
    private loading loading;
    Terrain gameScreen;
    private Texture Back;
    InterfaceMenu Menu;
    private Skin skin;
    private Skin skin2;
    private Stage stage;
    OrthographicCamera camera;
    private Texture mute;
    private Texture resume;
    private Texture exit;

    public Texture getMute() {
        return mute;
    }

    public void setMute(Texture mute) {
        this.mute = mute;
    }

    public Texture getResume() {
        return resume;
    }

    public void setResume(Texture resume) {
        this.resume = resume;
    }

    public Texture getExit() {
        return exit;
    }

    public void setExit(Texture exit) {
        this.exit = exit;
    }

    public InterfaceMenu(MyGdxGame game, Terrain gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        BGImage = new Texture(Gdx.files.internal("BG.png"));
        setExit(new Texture(Gdx.files.internal("Exit.png")));
        Back = new Texture(Gdx.files.internal("left.png"));
        BackGroundMusic = Gdx.audio.newMusic(Gdx.files.internal("StartGame.mp3"));
        BackGroundMusic.play();
        game.font.getData().setScale(2.0f);
        skin2 = new Skin(Gdx.files.internal("tubular/skin/tubular-ui.json"));
        ImageButton Resume = new ImageButton(skin2);
        Resume.setSize(100,50);
        Resume.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("button-play-pressed.png")));
        Resume.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("button-play.png")));
        Resume.setPosition(300,200);
        Resume.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
//                SaveGame t1 = new SaveGame(Menu,getGameScreen(),getGame());
//                getGame().setScreen(t1);
                Gdx.app.exit();
                dispose();
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y , int pointer , int button){
                return true;
            }
        });
        stage.addActor(Resume);
        skin = new Skin(Gdx.files.internal("shade/skin/uiskin.json"));
        ImageButton Mute = new ImageButton(skin);
        Mute.setSize(100,100);
        Mute.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("sound-off.png")));
        Mute.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture("sound.png")));
        Mute.setPosition(300,300);
        Mute.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event,float x, float y,int pointer ,int button){
                BackGroundMusic.pause();
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y , int pointer , int button){
                BackGroundMusic.play();
                return true;
            }
        });
        stage.addActor(Mute);

    }






    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(BGImage, 0,0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
//        game.batch.draw(getResume(), 0,0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
        game.batch.draw(getExit(), 300,30, 200,  150);
        game.font.draw(game.batch,"MUTE",200,350);
        game.batch.draw(Back,20,Gdx.graphics.getHeight()-50,50,50);
        game.font.draw(game.batch,"Save & Exit",100,240);
//        game.font.se
        game.batch.end();
        stage.act();
        stage.draw();
//        System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
        if(Gdx.input.isTouched() && Gdx.input.getX()>320 &&Gdx.input.getX()<480 && Gdx.input.getY()>360  && Gdx.input.getY()<426){
            Gdx.app.exit();
        }
//        System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
        if(Gdx.input.isTouched() && Gdx.input.getX()>10 && Gdx.input.getX()<50 && Gdx.input.getY()>10 && Gdx.input.getY()<50){
            WelcomePage w1 = new WelcomePage(getGame(),loading);
            getGame().setScreen(w1);
            dispose();
        }
//        System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
    }

    public Terrain getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(Terrain gameScreen) {
        this.gameScreen = gameScreen;
    }

    public MyGdxGame getGame() {
        return game;
    }

    public void setGame(MyGdxGame game) {
        this.game = game;
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


    public Music getBackGroundMusic() {
        return BackGroundMusic;
    }

    public void setBackGroundMusic(Music backGroundMusic) {
        BackGroundMusic = backGroundMusic;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
