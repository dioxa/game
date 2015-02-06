package com.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


/**
 * Абстрактный класс для всех эрканов игры.
 */
public abstract class GameScreen implements Screen{

    Game game;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
