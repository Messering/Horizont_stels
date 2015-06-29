package com.mygdx.game;

/**
 * Created by HP PAVILION on 29.06.2015.
 */
    import com.badlogic.gdx.Application;
    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.InputProcessor;
    import com.badlogic.gdx.Screen;
    import com.badlogic.gdx.graphics.GL20;
    import com.badlogic.gdx.graphics.OrthographicCamera;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;

    import java.util.HashMap;
    import java.util.Map;

public class MainMenuScreen implements Screen, InputProcessor {

        MyGdxGame game;

        OrthographicCamera camera;
        private int width, height;
        private Texture bgTexture;
        private SpriteBatch spriteBatch;
        public float ppuX;	// pixels per unit on the X axis
        public float ppuY;	// pixels per unit on the Y axis
        float CAMERA_WIDTH = 800F;
        float CAMERA_HEIGHT = 480F;
        public Map<String, Texture> textures;
        boolean downBtn;

         @Override
         public void show() {

        //CAMERA_WIDTH =  Math.max(CAMERA_HEIGHT* Gdx.graphics.getWidth()/Gdx.graphics.getHeight(),CAMERA_WIDTH);
        textures = new HashMap<String, Texture>();
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
        downBtn = false;
        spriteBatch = new SpriteBatch();
        this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        //SetCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
        loadTextures();
        Gdx.input.setInputProcessor(this);
    }

    private void loadTextures(){

        bgTexture = new Texture(Gdx.files.internal("prodecular.jpg"));
        textures.put("cover_button_start_up", new Texture(Gdx.files.internal("dw_game.jpg")));
        textures.put("cover_button_start_down", new Texture(Gdx.files.internal("st_game.jpg")));
    }

    public void showMenu(){
        if(downBtn)
            spriteBatch.draw(textures.get("cover_button_start_down"),653, 183, 256 , 128);
        else
            spriteBatch.draw(textures.get("cover_button_start_up"),653, 183, 256 , 128);

    }
    public void showBG(){
        spriteBatch.draw(bgTexture,0, -32, 1024 , 512);

    }

    public MainMenuScreen(MyGdxGame game) {
        this.game = game;}
    @Override
    public boolean keyUp(int keycode) {

        return true;
    }
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }



    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
        //this.world.setSize (w,h);
    }

    @Override
    public void resize(int width, int height) {
        setSize(width, height);
        this.width = width;
        this.height = height;
    }
        @Override
        public void render(float delta) {
           // SetCamera(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2f);
            //this.cam.update();
            spriteBatch.setProjectionMatrix(this.camera.combined);
            //timeLeft+=delta;
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            //Gdx.gl.glViewport(0, 0,1200,480);

            spriteBatch.begin();
            game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
            game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);

            showBG();
            showMenu();
            spriteBatch.end();
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void dispose() {
            Gdx.input.setInputProcessor(null);

            try{

                spriteBatch.dispose();


                bgTexture.dispose();
                textures.clear();
            }
            catch(Exception e){

            }
        }

    @Override
    public boolean keyDown(int keycode) {
        //sound.stop();
        //game.setScreen(game.stage);
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if((height-screenY)/ppuY >= 213 && (height-screenY)/ppuY <= 283 && screenX/ppuX>=660 && screenX/ppuX<=780)
            downBtn = true;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
            return false;
        if(downBtn){
            dispose();
            game.setScreen(new GameScreen(game));
        }

        downBtn = false;
        return true;
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
    public boolean scrolled(int amount) {
        return false;
    }
}

