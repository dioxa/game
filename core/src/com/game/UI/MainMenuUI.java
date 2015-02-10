package com.game.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.Screens.MainScreen;


/**
 * Класс интерфейса главного меню.
 */
public class MainMenuUI{

    private Stage mainMenuStage;

    private Table tableUI;

    public MainMenuUI(Game game) {

        mainMenuStage = new Stage();
        Image startButton = new Image(new Texture("core/assets/images/ui/newGameButton.png"));
        tableUI = new Table();
        tableUI.setFillParent(true);

        startButton.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new MainScreen(game));
                return true;
            }
        });

        tableUI.add(startButton).size(200, 100);
        mainMenuStage.addActor(tableUI);
    }

    public void update() {
        mainMenuStage.act();
        mainMenuStage.draw();
    }

    public Stage getStage() {
        return mainMenuStage;
    }
}
