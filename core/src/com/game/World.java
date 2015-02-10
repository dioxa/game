package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;


public class World {

    public final int CELL_SIZE = 64;

    private final int WIDTH_MAP_SIZE  = 12;

    private final int HEIGHT_MAP_SIZE = 9;

    private MapCell map[][] = new MapCell[WIDTH_MAP_SIZE][HEIGHT_MAP_SIZE];

    private OrthographicCamera worldCamera;

    private SpriteBatch worldBatch;

    private Texture emptyCellTexture;

    private Texture availableCellTexture;

    private Texture ownedCellTexture;

    private final HashMap<String, Integer> worldDrawOffset = new HashMap<>();


    public World() {
        try {
            emptyCellTexture = new Texture(
                    Gdx.files.internal("core/assets/images/map/emptyCell.png"));
            ownedCellTexture = new Texture(
                    Gdx.files.internal("core/assets/images/map/ownedCell.png"));
            availableCellTexture = new Texture(
                    Gdx.files.internal("core/assets/images/map/availableCell.png"));
        } catch (GdxRuntimeException ignored) {
            Gdx.app.error("LOAD", "Texture load exception.");
        }
        worldDrawOffset.put("x", 125);
        worldDrawOffset.put("y", 150);

        worldBatch = new SpriteBatch();
        worldCamera = new OrthographicCamera();
        worldCamera.setToOrtho(true);
        for (int i = 0; i < WIDTH_MAP_SIZE; i++) {
            for (int j = 0; j < HEIGHT_MAP_SIZE; j++) {
                map[i][j] = new MapCell();
            }
        }

    }

    /**
     * Вызвает MapCell.update() для каждой клетки, если у нее есть
     * владелец, после вызывает display().
     */
    public void update(int turnCounter, boolean updateCells) {
        if (updateCells) {
            for (int i = 0; i < WIDTH_MAP_SIZE; i++) {
                for (int j = 0; j < HEIGHT_MAP_SIZE; j++) {
                    if (map[i][j].getOwner() != 0) {
                        map[i][j].update(turnCounter);
                    }
                }
            }
        }

        display();

    }

    /**
     * Устанавливает владельца данной клетки. Используется
     * для установки владельца без проверок.
     * @param x Координата клетки.
     * @param y Координата клетки.
     * @param id ID игрока.
     */
    public void setCellOwner(int x, int y, int id) {

        map[x][y].setOwner(id);

    }


    /**
     * Вызывает checkTurnAvailability(). Если проверка проходит,
     * пытается установить владельца клетки. Если выбрасывается
     * ArrayIndexOutOfBoundsException возвращает false.
     * @param x Координата клетки
     * @param y Координата клетки
     * @param id ID игрока, совершающего ход
     * @return true, если ход выполнен, false, если нет
     */
    public boolean setCellOwnerByTurn(int x, int y, int id) {

        if (!checkTurnAvailability(x, y, id)) {
            System.out.println("Вы не можете сделать этот ход. Попробуйте еще раз.");
            return false;
        }

        if (map[x][y].getOwner() != 0) {
            return false;
        }

        try {
            setCellOwner(x, y, id);
            return true;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Неверные координаты. Попробуйте еще раз.");
            return false;
        }

    }

    /*
     * Выводит карту и счетчик ходов на экран.
     */
    private void display() {

        worldCamera.update();
        worldBatch.setProjectionMatrix(worldCamera.combined);
        worldBatch.begin();
        for (int i = 0; i < WIDTH_MAP_SIZE; i++) {
            for (int j = 0; j < HEIGHT_MAP_SIZE; j++) {
                if (map[i][j].getOwner() != 0) {
                    worldBatch.draw(ownedCellTexture, i * CELL_SIZE + worldDrawOffset.get("x"),
                            j * CELL_SIZE + worldDrawOffset.get("y"));
                } else {
                    worldBatch.draw(emptyCellTexture, i * CELL_SIZE + worldDrawOffset.get("x"),
                            j * CELL_SIZE + worldDrawOffset.get("y"));
                }
                if (checkAvailableCells(i,j)){
                    worldBatch.draw(availableCellTexture, i * CELL_SIZE + worldDrawOffset.get("x"),
                            j * CELL_SIZE + worldDrawOffset.get("y"));
                }
            }
        }
        worldBatch.end();
    }

    /**
     * Проверяет соседствует ли пустая клетка с населенной
     * @param x
     * @param y
     * @return
     */
    private boolean checkAvailableCells(int x, int y) {
        for (int row = x - 1; row <= x + 1; row++) {
            for (int col = y - 1; col <= y + 1; col++) {
                try {
                    if (map[row][col].getOwner() != 0 && map[x][y].getOwner() == 0) {
                        return true;
                    }
                }
                catch(ArrayIndexOutOfBoundsException ignored){}
            }

        }
        return false;
    }

    /**
     * Без проверок вычитает популяцию из одной клетки, и присваивает такое же значение
     * в новую клетку.
     * @param prevX x Координаты из которой происходит перемещение.
     * @param prevY y Координаты из которой происходит перемещение.
     * @param x x Координаты в которую происходит перемещение.
     * @param y y Координаты в которую происходит перемещение.
     * @param population Количество человек.
     */
    public void movePopulation(int prevX, int prevY, int x, int y, int population){
        map[x][y].setPopulation(map[x][y].getPopulation() + population);
        map[prevX][prevY].setPopulation(map[prevX][prevY].getPopulation() - population);
    }


    /**
     * Проверяет возможно ли перемещение населения из одной клетки в другую на:
     * Принадлежность обеих клеток одному игроку, ход в рамках карты,
     * остаток человек на первой клетке больше или равно 1;
     * @param prevX x Координаты из которой происходит перемещение.
     * @param prevY y Координаты из которой происходит перемещение.
     * @param x x Координаты в которую происходит перемещение.
     * @param y y Координаты в которую происходит перемещение.
     * @param playerId ID игрока.
     * @param population Количесвто человек.
     * @return true, если перемещение совершено, false, если нет.
     */
    public boolean checkMovePopulationAvailability(int prevX, int prevY, int x, int y, int playerId, int population) {
        try {
            if (map[prevX][prevY].getOwner() == playerId && map[x][y].getOwner() == playerId) {
                if (map[prevX][prevY].getPopulation() > population) {
                    movePopulation(prevX, prevY, x, y, population);
                    return true;
                } else {
                    System.out.println("На клетке не может остаться меньше одного человека.");
                }
            } else {
                System.out.println("Обе клетки должны принадлежать вам.");
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Ход за границы карты.");
        }
        return false;

    }

    /**
     * Без проверок вычитает ресурсы из одной клетки, и присваивает такое же значение
     * в новую клетку.
     * @param prevX x Координаты из которой происходит перемещение.
     * @param prevY y Координаты из которой происходит перемещение.
     * @param x x Координаты в которую происходит перемещение.
     * @param y y Координаты в которую происходит перемещение.
     * @param resourceName Название ресурса, как ключ в HashMap, используемый для хранения
     *                     ресурсов в клетке.
     * @param resources Количество ресурсов для перемещения.
     */
    public void moveResources(int prevX, int prevY, int x, int y, String resourceName, int resources){
        map[x][y].setCityResources(resourceName, map[x][y].getCityResources(resourceName) + resources);
        map[prevX][prevY].setCityResources(resourceName, map[prevX][prevY].getCityResources(resourceName) - resources);
    }

    /**
     * Проверяет возможно ли перемещение ресурсов из одной клетки в другую на:
     * Принадлежность обеих клеток одному игроку, ход в рамках карты,
     * остаток ресурсов на первой клетке больше или равно 1;
     * @param prevX x Координаты из которой происходит перемещение.
     * @param prevY y Координаты из которой происходит перемещение.
     * @param x x Координаты в которую происходит перемещение.
     * @param y y Координаты в которую происходит перемещение.
     * @param playerId ID игрока.
     * @param resourceName Название ресурса, как ключ в HashMap, используемый для хранения
     *                     ресурсов в клетке.
     * @param resources Количество ресурсов для перемещения.
     * @return true, если перемещение совершено, false, если нет.
     */
    public boolean checkMoveResourcesAvailability(int prevX, int prevY, int x, int y, int playerId, String resourceName, int resources) {
        try {
            if (map[prevX][prevY].getOwner() == playerId && map[x][y].getOwner() == playerId) {
                if (map[prevX][prevY].getCityResources(resourceName) > resources) {
                    moveResources(prevX, prevY, x, y, resourceName, resources);
                    return true;
                } else {
                    System.out.println("В клетке не хватает ресурсов.");
                }
            } else {
                System.out.println("Обе клетки должны принадлежать вам.");
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Ход за границы карты.");
        }
        return false;

    }


    /**
     * Проверяет клетку на которую совершается ход на:
     * наличие соведней клетки принадлежащей игроку, совершающему ход.
     * @param x Координата клетки
     * @param y Координата клетки
     * @param id ID игрока, совершающего ход
     * @return true, если ход возможен, false, если нет
     */
    public boolean checkTurnAvailability(int x, int y, int id) {
        for (int row = x-1; row <= x+1; row++) {
            for (int col = y-1; col <= y+1; col++) {
                try {
                    if (map[row][col].getOwner() == id && map[x][y].getOwner() != id)
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return false;
    }

    public MapCell getMapCell(int x, int y) {
        return map[x][y];
    }


    /**
     * Возвращает ширину мира в клетках.
     * @return int ширина.
     */
    public int getWorldWidth() {
        return WIDTH_MAP_SIZE;
    }

    /**
     * Возвращает высоту мира в клетках.
     * @return int высота.
     */
    public int getWorldHeight() {
        return HEIGHT_MAP_SIZE;
    }

    /**
     * Возвращает смещение отрисовки карты мира от границ окна в
     * виде HashMap<String, Integer> с ключами x, y.
     * @return Смещение мира.
     */
    public HashMap<String, Integer> getWorldDrawOffset() {
        return worldDrawOffset;
    }
}
