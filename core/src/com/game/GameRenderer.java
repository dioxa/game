package com.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.States.GameState;
import com.game.States.PlayerState;
import com.game.UI.MainScreenUI;


public class GameRenderer {

    private final Player player;

    private final World world;

    private GameState gameState;

    private int turnCounter = 0;

    private MainScreenUI mainScreenUI;

    private boolean updateCellsData;


    public GameRenderer(){
        mainScreenUI = new MainScreenUI();
        world = new World();
        player = new Player(world);
        world.setCellOwner(0, 0, player.getId());
        gameState = GameState.inProcess;
    }

    /**
     * Запуск цикла обработки игры.
     */
    public void update(){
        update_player();
        world.update(turnCounter, updateCellsData);
        mainScreenUI.update(turnCounter);
        Gdx.input.setInputProcessor(mainScreenUI.getStage());
        handle_input();
    }

    /**
     * Обработка действий игрока.
     */
    private void update_player() {
        updateCellsData = false;
        if (gameState == GameState.inProcess) {
            if (player.makeTurn()) {
                turnCounter++;
                updateCellsData = true;
            }
        }
        if (player.getState() == PlayerState.gettingInfo) {
            mainScreenUI.showCellInfo(player.getSelectedCell());
        } else {
            mainScreenUI.hideCellInfo();
        }
    }

    /**
     * Обработка нажатий клавиш
     * @return boolean служит для выхода из обработки (временно)
     */
    private boolean handle_input() {

        if (gameState == GameState.inProcess) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                gameState = GameState.paused;
                Gdx.app.log("СОСТОЯНИЕ МИРА", "paused");
                return true;
            }
        }
        if (gameState == GameState.paused) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                gameState = GameState.inProcess;
                Gdx.app.log("СОСТОЯНИЕ МИРА", "inProcess");
                return false;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        return true;
    }
}
