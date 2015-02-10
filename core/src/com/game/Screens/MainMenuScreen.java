package com.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.game.UI.MainMenuUI;

/**
 * Экран главного меню игры.
 */
public class MainMenuScreen extends GameScreen{

    private MainMenuUI menuUI;

    public MainMenuScreen(Game game) {
        super(game);

    }

    @Override
    public void show() {
        menuUI = new MainMenuUI(game);
        Gdx.input.setInputProcessor(menuUI.getStage());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        menuUI.update();
    }

}
