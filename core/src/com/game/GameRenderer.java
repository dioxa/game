package com.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.States.GameState;


public class GameRenderer {

    private final Player player;

    private final World world;

    private GameState gameState;

    private int turnCounter = 0;

    private MainScreenUI mainScreenUI;

    public GameRenderer(){
        world = new World();
        player = new Player(world);
        world.setCellOwner(0, 0, player.getId());
        gameState = GameState.inProcess;
        mainScreenUI = new MainScreenUI();

    }

    /**
     * Запуск цикла обработки игры.
     */
    public void update(){
        boolean updateCellsData = false;
        if (gameState == GameState.inProcess) {
            if (player.makeTurn()) {
                turnCounter++;
                updateCellsData = true;
            }
        }
        world.update(turnCounter, updateCellsData);
        mainScreenUI.update(turnCounter);
        Gdx.input.setInputProcessor(mainScreenUI.getStage());
        handle_input();
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
