package com.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.States.PlayerState;


public class Player {

    private final int id;

    private final World world;

    private static int nextId = 1;

    private PlayerState state;

    private boolean turnIsOver;

    /**
     * Устанавливает игроку уникальный id и присваевает ему сссылку на мир.
     * @param world Мир игры.
     */
    public Player(World world) {

        id = nextId;
        nextId++;
        this.world = world;
        state = PlayerState.pause;
        turnIsOver = false;
    }

    /**
     * Выбор действия при ходе и вызов соответствующей обработки.
     */
    public boolean makeTurn() {
        turnIsOver = false;
        if (Gdx.input.justTouched()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && state != PlayerState.gettingInfo) {
                state = PlayerState.owning;
                int turnX = (Gdx.input.getX() - world.getWorldDrawOffset().get("x")) / world.CELL_SIZE;
                int turnY = (Gdx.input.getY() - world.getWorldDrawOffset().get("y")) / world.CELL_SIZE;
                Gdx.app.log("КЛЕТКА", turnX + " " + turnY);
                if (makeCellTurn(turnX, turnY)) {

                    turnIsOver = true;
                }
            }
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                if (state ==PlayerState.gettingInfo) {
                    state = PlayerState.owning;
                    Gdx.app.log("INFO", "Выход из режима информации.");
                } else {
                    state = PlayerState.gettingInfo;
                    Gdx.app.log("INFO", "На экране информации. Правый клик, чтобы выйти.");
                }
            }
            Gdx.app.log("СОСТОЯНИЕ ИГРОКА", state.toString());
        }

        return turnIsOver;

    }

    /**
     * Обработка хода захвата клетки.
     */
    private boolean makeCellTurn(int x, int y) {
        return world.setCellOwnerByTurn(x, y, id);
    }

    /**
     * Обработка хода перемещения популяции.
     */
    private void makeMovePopulationTurn() {
        int prevX, prevY, x, y, population;
        // world.checkMovePopulationAvailability(prevX, prevY, x, y, id, population);
        turnIsOver = true;
    }

    /**
     * Обарботка хода перемещения ресурсов.
     */
    private void makeMoveResourcesTurn() {
        int prevX, prevY, x, y, resources;
        int actionNumber = 0;
        String resourceName = null;
        actionNumber = 1;
        switch (actionNumber) {
            case 1:
                resourceName = "Wood";
                break;
            case 2:
                resourceName = "Food";
                break;
        }
        //world.checkMoveResourcesAvailability(prevX, prevY, x, y, id, resourceName, resources);
        turnIsOver = true;
    }

    public PlayerState getState() {
        return state;
    }

    public int getId() {
        return id;
    }
}
