package com.game.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.Screens.MainScreen;


/**
 * Класс интерфейса главного меню.
 */
public class MainMenuUI{

    private Stage mainMenuStage;

    public MainMenuUI(Game game) {
        mainMenuStage = new Stage();
        Image startButton = new Image(new Texture("core/assets/images/ui/newGameButton.png"));
        Image optionsButton = new Image(new Texture("core/assets/images/ui/optionsButton.png"));
        Image exitButton = new Image(new Texture("core/assets/images/ui/exitButton.png"));
        startButton.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new MainScreen(game));
                return true;
            }
        });
        exitButton.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.exit();
                return true;
            }
        });
        startButton.setPosition(400, 450);
        optionsButton.setPosition(400, 300);
        exitButton.setPosition(400, 150);
        mainMenuStage.addActor(startButton);
        mainMenuStage.addActor(optionsButton);
        mainMenuStage.addActor(exitButton);
    }

    public void update() {
        mainMenuStage.act();
        mainMenuStage.draw();
    }

    public Stage getStage() {
        return mainMenuStage;
    }
}
