package com.game.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.Screens.MainMenuScreen;

/**
 * Created by Андрей on 14.02.2015.
 */
public class MenuOptionsUI {

    private Stage mainMenuStage;

    //private CheckBox checkBox;

    private boolean fullScreenMode;

    public MenuOptionsUI(Game game) {
        mainMenuStage = new Stage();
        fullScreenMode = false;
        Texture checkBoxOn = new Texture("core/assets/images/ui/checkedCheck.png");
        Texture checkBoxOff = new Texture("core/assets/images/ui/emptyCheck.png");

        CheckBox.CheckBoxStyle cbs = new CheckBox.CheckBoxStyle();
        cbs.checkboxOn = new TextureRegionDrawable(new TextureRegion(checkBoxOn));
        cbs.checkboxOff = new TextureRegionDrawable(new TextureRegion(checkBoxOff));
        cbs.font = new BitmapFont();
        cbs.fontColor = Color.BLACK;

        CheckBox checkBox = new CheckBox("FullScreen", cbs);
        Image backButton = new Image(new Texture("core/assets/images/ui/backButton.png"));

        backButton.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
                return true;
            }
        });
        if (Gdx.graphics.isFullscreen()){
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.addListener( new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!Gdx.graphics.isFullscreen()){
                    Gdx.graphics.setDisplayMode(1366, 768, true);
                    checkBox.setChecked(true);
                } else {
                    Gdx.graphics.setDisplayMode(1024, 768, false);
                    checkBox.setChecked(false);
                }
                return true;
            }

        });

        checkBox.setPosition(450, 400);
        backButton.setPosition(50, 50);
        mainMenuStage.addActor(backButton);
        mainMenuStage.addActor( checkBox );
    }

    public void update() {
        mainMenuStage.act();
        mainMenuStage.draw();

    }

    public Stage getStage() {
        return mainMenuStage;
    }
}
