package com.base;


public class World {

    public final byte MAP_SIZE  = 10;

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

    //Update every MapCell and display
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

    //Set cell owner
    public void setCellOwner(int x, int y, int id) {

        map[y][x].setOwner(id);
        mapChar[y][x] = String.valueOf(id).charAt(0);

    }

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

    //Display map to screen
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

    public void movePopulation(int prevX, int prevY, int x, int y, int population){
            map[y][x].setPopulation(map[y][x].getPopulation() + population);
            map[prevY][prevX].setPopulation(map[prevY][prevX].getPopulation() - population);
    }

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

    public boolean checkTurnAvailability(int x, int y, int id) {
        for (int row = y-1; row <= y+1; row++) {
            for (int col = x-1; col <= x+1; col++) {
                try {
                    if (map[row][col].getOwner() == id && map[y][x].getOwner() != id)
                        return true;
                } catch (ArrayIndexOutOfBoundsException error) {
                }
            }
        }
        return false;
    }

    private void clearConsole() {
        for (int i = 0; i < 9; i++){
            System.out.println(' ');
        }
    }

}
