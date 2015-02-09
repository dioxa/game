package com.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.game.GameInputProcessor;
import com.game.GameRenderer;


/**
 * Класс основного процесса игры.
 */
public class MainScreen extends GameScreen {

    private GameRenderer gameRenderer;

    public MainScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        gameRenderer = new GameRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(new GameInputProcessor());
        gameRenderer.update();
    }

    @Override
    public void resize(int width, int height) {
    }

}
