package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * Created by HP PAVILION on 29.06.2015.
 */
public class GameScreen implements Screen{

    final MyGdxGame game;
    public GameScreen(final MyGdxGame gam) {
        this.game = gam;
    }


    @Override
    public void render(float delta) {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}
    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {}

    }
