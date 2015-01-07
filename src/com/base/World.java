package com.base;



public class World {

    public final byte MAP_SIZE  = 10;

    private MapCell map[][] = new MapCell[MAP_SIZE][MAP_SIZE];

    private char mapChar[][] = new char[MAP_SIZE][MAP_SIZE];


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

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j].update();
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
            System.out.println("You have no right to do this turn. Try again");
            return false;
        }

        try {
            setCellOwner(x, y, id);
            return true;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Incorrect coordinates. Try again.");
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
        System.out.println();

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
