package com.game.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.game.MapCell;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Класс UI игрового экрана.
 */
public class MainScreenUI {

    private Stage stageUI;

    private BitmapFont gameFontUI;

    private BitmapFont infoFontUI;

    private Label.LabelStyle labelStyle;

    private Label.LabelStyle labelInfoStyle;

    private ArrayList<Label> info;

    private Label turnLabel;

    private Label infoPopLabel;

    private Table tableUI;

    private Image cellInfoBackground;

    public MainScreenUI() {
        stageUI = new Stage();

        gameFontUI = new BitmapFont();
        gameFontUI.setColor(Color.WHITE);
        gameFontUI.scale(2);

        infoFontUI = new BitmapFont();
        infoFontUI.setColor(Color.BLACK);
        infoFontUI.scale(1/2);

        labelInfoStyle = new Label.LabelStyle();
        labelInfoStyle.font = infoFontUI;
        labelStyle = new Label.LabelStyle();
        labelStyle.font = gameFontUI;
        turnLabel = new Label("", labelStyle);
        info = new ArrayList<>();


        tableUI = new Table();
        tableUI.setFillParent(true);
        tableUI.add(turnLabel).expand().fill().left().top().size(64);
        stageUI.addActor(tableUI);

    }

    /**
     * Обновить интерфейс.
     * @param turnCounter Счетчик ходов.
     */
    public void update(int turnCounter) {
        turnLabel.setText("Turn: " + turnCounter);
        stageUI.act(Gdx.graphics.getDeltaTime());
        stageUI.draw();
    }


    /**
     * Показать информацию о клетке.
     * @param cell Клетка для показа.
     */
    public void showCellInfo(MapCell cell) {
        HashMap<String, String> res;
        float x, y;
        if (cellInfoBackground == null) {
            try {
                cellInfoBackground = new Image(new Texture("core/assets/images/ui/infoBackground.png"));
                cellInfoBackground.setX(Gdx.input.getX());
                cellInfoBackground.setY(Gdx.graphics.getHeight() - Gdx.input.getY());
                res = cell.getCellInfo();
                x = cellInfoBackground.getX() + 5;
                y = cellInfoBackground.getY() + 17;
                for (String name: res.keySet()){
                    infoPopLabel = new Label("", labelInfoStyle);
                    infoPopLabel.setText(name + ": " + res.get(name));
                    infoPopLabel.setX(x);
                    infoPopLabel.setY(y);
                    y += 30;
                    info.add(infoPopLabel);
                }
                stageUI.addActor(cellInfoBackground);

                for(Label resources: info){
                    stageUI.addActor(resources);
                }
            } catch (GdxRuntimeException ignored) {
                Gdx.app.log("ERROR", "Ошибка при загрузке infoBackground.png");
            }
        }
    }

    /**
     * Скрыть информацию о клетке.
     */
    public void hideCellInfo() {
        if (cellInfoBackground != null) {
            for(Label el: info){
                el.remove();
            }
            info.clear();
            cellInfoBackground.remove();
            cellInfoBackground = null;

        }
    }

    public Stage getStage() {
        return stageUI;
    }

}
