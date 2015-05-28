package com.game.UI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.GdxRuntimeException;

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

    private ArrayList<Label> cellInfo;

    private Label turnLabel;

    private Label infoPopLabel;

    private Table tableUI;

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
     * @param rawCellInfo Информация для показа.
     */
    public void showCellInfo(HashMap<String, String> rawCellInfo) {
        if (cellInfo == null) {
            try {
                cellInfo = new ArrayList<>();
                float labelX = turnLabel.getX() + 400;
                float labelY = Gdx.graphics.getHeight()-25;
                for (String name: rawCellInfo.keySet()) {
                    infoPopLabel = new Label(name + ": " + rawCellInfo.get(name), labelInfoStyle);
                    infoPopLabel.setPosition(labelX, labelY);
                    labelY -= 25;
                    cellInfo.add(infoPopLabel);
                }
                cellInfo.forEach(stageUI::addActor);

            } catch (GdxRuntimeException ignored) {
                Gdx.app.log("ERROR", "Ошибка при загрузке infoBackground.png");
            }
        }
    }

    /**
     * Скрыть информацию о клетке.
     */
    public void hideCellInfo() {
        if (cellInfo != null) {
            for(Label element: cellInfo){
                element.remove();
            }
            cellInfo.clear();
            cellInfo = null;
        }
    }

    public Stage getStage() {
        return stageUI;
    }

}
