package com.game.UI;

import com.badlogic.gdx.scenes.scene2d.Stage;


/**
 * Класс интерфейса главного меню.
 */
public class MainMenuUI{

    private Stage mainMenuStage;

    public void MainMenuUI() {

        mainMenuStage = new Stage();

    }

    public void update(int turnCounter) {

    }

    public Stage getStage() {
        return mainMenuStage;
    }
}
