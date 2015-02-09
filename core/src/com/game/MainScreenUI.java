package com.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MainScreenUI {

    private Stage stageUI;

    private BitmapFont gameFontUI;

    private Label.LabelStyle labelStyle;

    private Label turnLabel;

    private Table tableUI;

    public MainScreenUI() {
        stageUI = new Stage();

        gameFontUI = new BitmapFont();
        gameFontUI.setColor(Color.WHITE);
        gameFontUI.scale(2);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = gameFontUI;
        turnLabel = new Label("", labelStyle);

        tableUI = new Table();
        tableUI.setFillParent(true);
        tableUI.add(turnLabel).expand().fill().left().top().size(64);
        stageUI.addActor(tableUI);

    }

    public void update(int turnCounter) {
        turnLabel.setText("Turn: " + turnCounter);
        stageUI.act(Gdx.graphics.getDeltaTime());
        stageUI.draw();
    }

    public Stage getStage() {
        return stageUI;
    }

}
