package com.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.States.GameState;

public class GameRenderer {

    private final Player player;

    private final World world;

    private GameState state;

    private int turnCounter = 0;

    public GameRenderer(){
        world = new World();
        player = new Player(world);
        world.setCellOwner(0, 0, player.getId());
        state = GameState.inProcess;
    }

    /**
     * Запуск цикла обработки игры.
     */
    public void update(){

        boolean updateCellsData = false;
        if (state == GameState.inProcess) {
            if (player.makeTurn()) {
                turnCounter++;
                updateCellsData = true;
                Gdx.app.log("ХОД", "" + turnCounter);
            }
        }
        world.update(turnCounter, updateCellsData);
        handle_input();
    }

    /**
     * Обработка нажатий клавиш
     * @return boolean служит для выхода из обработки (временно)
     */
    private boolean handle_input() {
        if (state == GameState.inProcess) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                state = GameState.paused;
                Gdx.app.log("СОСТОЯНИЕ МИРА", "paused");
                return true;
            }
        }
        if (state == GameState.paused) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                state = GameState.inProcess;
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
