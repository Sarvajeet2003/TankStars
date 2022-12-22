package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
public class Terrain extends Game implements Screen, InputProcessor {

    private Texture TankImage;
    private Texture BG;
    private SpriteBatch spriteBatch;
    private ImageButton SettingButton;
//    private Shoot gun;

    private Texture Weapon1;
    private final int theta=50;
    private Music bgm;
    private Texture Weapon3;
    private Texture Weapon2;
    private Texture HealthBar;
    Vector2 gravity,gravity2;
    private boolean a;
    float y,x;
    Vector2 velocity, velocity2;
    private Texture FuelIndicator;
    private Texture Settings;
    private final float time=10;
    private Texture TankMirror;
    private TextureAtlas atlas;
    private OrthographicCamera camera;
    private float range2;
    @Test
    public void testIncr() {
        Terrain t1 = new Terrain();
        a=t1.testKeys();
        assertEquals(a,true);
    }
    Terrain terrain;
    private Label scoreLabel;
    private Label scoreLabel2;
    private Label Health1;
    private Label Health2;
    private Stage stage;
    private Stage stage2;
    private Sound mmm;
    private int Speed1;
    private boolean isFired,isFired2;
    private Skin skin;
    private Sprite ball,ball2;
    int k,z;
    int m,n;
    MyGdxGame game;
    int fuel1,fuel2;
    int h1,h2;
    ShowCase showTanks;
    float range;
    int i,j;


    public Terrain(MyGdxGame game, Terrain t11){
        super();
        this.terrain = t11;
        this.game = game;
    }
    public Terrain(){

    }

    public Terrain(final MyGdxGame game, ShowCase showTanks, int i, int j) {
        this.game = game;
        this.showTanks = showTanks;
        this.i=i;
        this.j = j;
        TankImage = showTanks.TankList.get(i).getTankImage();
        TankMirror = showTanks.TankList.get(j).getTankImageMirror();
        this.Speed1=0;
        BG = new Texture(Gdx.files.internal("Terrain.png"));
        FuelIndicator = new Texture(Gdx.files.internal("Fuel.png"));
        HealthBar = new Texture(Gdx.files.internal("Health.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        //Setting Button
        stage2 = new Stage(new ScreenViewport());
        stage = new Stage();
        Gdx.input.setInputProcessor(stage2);
        skin = new Skin(Gdx.files.internal("skin-composer/skin/skin-composer-ui.json"));
        SettingButton = new ImageButton(skin);
        SettingButton.setSize(50,50);
        SettingButton.getStyle().imageUp=new TextureRegionDrawable(new TextureRegion(new Texture("button-settings-pressed.png")));
        SettingButton.getStyle().imageDown=new TextureRegionDrawable(new TextureRegion(new Texture("button-settings-over.png")));
        SettingButton.setPosition(30,468-70);
        SettingButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer , int button){
                InterfaceMenu i1 = new InterfaceMenu(game,terrain);
                game.setScreen(i1);
                dispose();
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y , int pointer , int button){
                return true;
            }
        });
        stage2.addActor(SettingButton);

        //FUEL
        this.fuel1 = showTanks.TankList.get(i).getFuel();
        this.fuel2 = showTanks.TankList.get(j).getFuel();
        //HEALTH
        this.h1 = showTanks.TankList.get(j).getHealth();
        this.h2 = showTanks.TankList.get(j).getHealth();

        //Fuel
        scoreLabel = new Label(String.valueOf(showTanks.TankList.get(i).getFuel()), new Label.LabelStyle(game.font, Color.GOLD));
        scoreLabel.setPosition(112, 300);
        scoreLabel2 = new Label(String.valueOf(showTanks.TankList.get(j).getFuel()), new Label.LabelStyle(game.font, Color.GOLD));
        scoreLabel2.setPosition(500, 300);

        //Health
        Health1 = new Label(String.valueOf(showTanks.TankList.get(i).getHealth()),new Label.LabelStyle(game.font,Color.RED));
        Health1.setPosition(112,258);
        Health2 = new Label(String.valueOf(showTanks.TankList.get(i).getHealth()),new Label.LabelStyle(game.font,Color.RED));
        Health2.setPosition(500,250);
        k=10;
        z=472-372;
        m=550;
        n=472-372;
        mmm=(Gdx.audio.newSound(Gdx.files.internal("StartGame.mp3")));
//        bgm   =  Gdx.audio.newSound(Gdx.files.internal("LostGame.mp3"));
        mmm.play();





        spriteBatch=new SpriteBatch();
        Weapon1 = new Texture(Gdx.files.internal("Weapon1.png"));
        ball=new Sprite(Weapon1);
        ball.setSize(50,50);
        ball.setPosition(k,z);
        Gdx.input.setInputProcessor(this);
        gravity=new Vector2(0, -Gdx.graphics.getHeight()*.05f);
        float throwVelocity=Gdx.graphics.getWidth()*.3f;
        velocity=new Vector2((float)(throwVelocity*Math.sin(theta * Math.PI / 180)),(float)(throwVelocity*Math.cos(theta * Math.PI / 180)));


        Weapon2 = new Texture(Gdx.files.internal("Weapon1Mirror.png"));
        ball2 = new Sprite(Weapon2);
        ball2.setSize(50,50);
        ball2.setPosition(m,n);
        Gdx.input.setInputProcessor(this);
        gravity2 = new Vector2(0, -Gdx.graphics.getHeight() * .05f);
        float throwVelocity2=Gdx.graphics.getWidth()*.3f;
        velocity2 = new Vector2((float) (throwVelocity2 * Math.sin(180-theta * Math.PI / 180)), (float) (throwVelocity2 * Math.cos(180-theta * Math.PI / 180)));

    }
    private void updateBall(){
        if(isFired){
            float delta=Gdx.graphics.getDeltaTime();
            velocity.x=velocity.x+gravity.x*delta*time;
            velocity.y=velocity.y+gravity.y*delta*time;
            ball.setPosition(ball.getX()+velocity.x * delta * 2,ball.getY()+velocity.y * delta * 2);
        }
    }
    private void updateBall2(){
        if(isFired2){
            float delta=Gdx.graphics.getDeltaTime();
            velocity2.x=velocity.x+gravity.x*delta*time;
            velocity2.y=velocity.y+gravity.y*delta*time;
            ball2.setPosition(ball2.getX()+velocity2.x * delta * 2,ball2.getY()+velocity2.y * delta * 2);
        }
    }
    public Boolean testKeys(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            return true;
        }
        return false;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
//        time +=Gdx.graphics.getDeltaTime();

//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);
//        gun.update(deltaTime);
        game.batch.begin();
//        gun.update(delta);
//        Gdx.gl.glClearColor(1,1,1,1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        gun.draw(game.batch);
        game.batch.draw(BG,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.draw(FuelIndicator,-50,468-168-50,220,110);
        game.batch.draw(HealthBar,-50,468-221-50,220,100);
//        System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
//        game.batch.draw(Settings,50,600,50,30);
//        font.draw(game.batch, sb, 112, 258);
//        font2.draw(game.batch,sb2,500,250);
        game.batch.draw(TankImage,k,z,TankImage.getWidth()/5,TankImage.getHeight()/5);
        game.batch.draw(TankMirror,m,n,TankMirror.getWidth()/5,TankMirror.getHeight()/5);

        stage2.act();
        stage2.draw();

        //Left Tank
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if(fuel1>0){
                k+=2;
                fuel1 -= 2;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if(fuel1>0){
                k-=2;
                fuel1 -= 2;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            if(fuel1>0){
                z+=2;
                fuel1 -= 2;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            if(fuel1>0){
                z-=2;
                fuel1 -= 2;
            }
        }


        if(fuel1>=0){
            scoreLabel.setText(String.valueOf(fuel1));
        }
        else{
            scoreLabel.setText("Not Enough Fuel");
        }
        Health1.setText(String.valueOf(h1));
        Health2.setText(String.valueOf(h1));


        //RIGHT TANK / Mirror Tank
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(fuel2>0){
                m+=2;
                fuel2-=2;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(fuel2>0){

                m-=2;
                fuel2-=2;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            if(fuel2>0){
                n+=2;
                fuel2-=2;
            }

        }
//        System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
//        if(Gdx.input.isKeyPressed()&& Gdx.input.getY()>)
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if(fuel2>0){
                n-=2;
                fuel2-=2;
            }
        }

        if(fuel2>=0){
            scoreLabel2.setText(String.valueOf(fuel2));
        }
        else{
            scoreLabel2.setText("Not Enough Fuel");
        }


//        // SET ANGLE
//        if(Gdx.input.isKeyPressed(Input.Keys.Z)&&theta<90){
//            theta+=15;
//        }
if(Gdx.input.isTouched() && Gdx.input.getX()>50 && Gdx.input.getY()>50 && Gdx.input.getX()<60){
    InterfaceMenu i1 = new InterfaceMenu(game,this);
    game.setScreen(i1);
    dispose();
}
        //SET Speed
        if(Gdx.input.isKeyPressed(Input.Keys.X)){
            Speed1+=10;
        }

        // TANK 1

        // Fire 1
        if(Gdx.input.isKeyPressed(Input.Keys.M)){

            fuel1 =100;
            fuel2 = 100;
            isFired = true;
            h1 -= 2;
//            game.batch.draw(Weapon1, k+GetYcoordinate(37,200),  (z+GetXcoordinate(37,200)),50,50);
        }

//        // Fire 2
//        if(Gdx.input.isKeyPressed(Input.Keys.N)){
//            fuel1 =100;
//            fuel2 = 100;
//            isFired = true;
//        }
//        // Fire 3
//        if(Gdx.input.isKeyPressed(Input.Keys.B)){
//            fuel1 = 100;
//            fuel2 = 100;
//        }


        // TANK 2

//         Fire 1
        if(Gdx.input.isKeyPressed(Input.Keys.N)){
            fuel1 =100;
            fuel2 = 100;
            isFired2 = true;
            h2 -= 2;

        }
        updateBall();
        updateBall2();
        spriteBatch.begin();
//        spriteBatch2.begin();
        ball.draw(spriteBatch);
        ball2.draw(spriteBatch);


//        // Fire 2
//        if(Gdx.input.isKeyPressed(Input.Keys.N)){
//            fuel1 =100;
//            fuel2 = 100;
//        }
//        // Fire 3
//        if(Gdx.input.isKeyPressed(Input.Keys.B)){
//            k+=2;
//            fuel1 = 100;
//            fuel2 = 100;
//        }




//        range = (float) (20*4*Math.sin(theta)*Math.cos(theta));
//        if(range2<=m+50 && range>=m-50){
//            h1-=20;
//        }
        range2 = (float) (20*4*Math.sin(theta)*Math.cos(theta));
        if(range2<=k+50 && range2>=k-50){
            h2-=20;
        }
        stage.addActor(scoreLabel2);
        stage.addActor(Health1);
        stage.addActor(Health2);
        stage.addActor(scoreLabel);
        stage.act();
        stage.draw();
        spriteBatch.end();
        game.batch.end();
//        font.dispose();
//        font2.dispose();


    }
//    public float GetYcoordinate(int theta,int velocity){
//        y = (float) (velocity*Math.sin(theta)*Gdx.graphics.getDeltaTime() - 5*Gdx.graphics.getDeltaTime()*Gdx.graphics.getDeltaTime());
//        return y;
//    }
//    public float GetXcoordinate(int theta,int velocity){
//        x = (float) (velocity*Math.cos(theta)*Gdx.graphics.getDeltaTime());
//        return x;
//    }

    @Override
    public void create() {


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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
