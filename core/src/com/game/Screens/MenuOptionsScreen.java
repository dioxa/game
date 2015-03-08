package com.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.game.UI.MenuOptionsUI;

/**
 * Created by Андрей on 14.02.2015.
 */
public class MenuOptionsScreen extends GameScreen {

    private MenuOptionsUI menuUI;

    public MenuOptionsScreen(Game game) {
        super(game);

    }

    @Override
    public void show() {
        menuUI = new MenuOptionsUI(game);
        Gdx.input.setInputProcessor(menuUI.getStage());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        menuUI.update();
    }

}
