package com.base;


public class World {

   private final byte MAP_SIZE  = 10;

    private MapCell map[][] = new MapCell[MAP_SIZE][MAP_SIZE];

    private char mapChar[][] = new char[MAP_SIZE][MAP_SIZE];

    private int turnCounter = 0;

    public World() {

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = new MapCell();
                mapChar[i][j] = ('*');
            }
        }

    }

    /**
     * Вызвает MapCell.update() для каждой клетки, если у нее есть
     * владелец, после вызывает display().
     */
    public void update() {
        turnCounter++;
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j].getOwner() != 0) {
                    map[i][j].update(turnCounter);
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

        map[y][x].setOwner(id);
        mapChar[y][x] = String.valueOf(id).charAt(0);

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

        try {
            setCellOwner(x, y, id);
            return true;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Неверные координаты. Попробуйте еще раз.");
            return false;
        }

    }

    /**
     * Выводит карту и счетчик ходов на экран.
     */
    private void display() {

        clearConsole();
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.println();
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(mapChar[i][j] + " ");
            }
        }
        System.out.println("\nХод: " + turnCounter);

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
            map[y][x].setPopulation(map[y][x].getPopulation() + population);
            map[prevY][prevX].setPopulation(map[prevY][prevX].getPopulation() - population);
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
            if (map[prevY][prevX].getOwner() == playerId && map[y][x].getOwner() == playerId) {
                if (map[prevY][prevX].getPopulation() > population) {
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
        map[y][x].setCityResources(resourceName, map[y][x].getCityResources(resourceName) + resources);
        map[prevY][prevX].setCityResources(resourceName, map[prevY][prevX].getCityResources(resourceName) - resources);
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
            if (map[prevY][prevX].getOwner() == playerId && map[y][x].getOwner() == playerId) {
                if (map[prevY][prevX].getCityResources(resourceName) > resources) {
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
        for (int row = y-1; row <= y+1; row++) {
            for (int col = x-1; col <= x+1; col++) {
                try {
                    if (map[row][col].getOwner() == id && map[y][x].getOwner() != id)
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return false;
    }


    /**
     * Выодит пустые строки для искусственной очистки экрана.
     */
    private void clearConsole() {
        for (int i = 0; i < 9; i++){
            System.out.println(' ');
        }
    }

    public MapCell getMapCell(int x, int y) {
        return map[y][x];
    }

}
