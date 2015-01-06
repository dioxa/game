package com.base;



public class World {

    public final byte MAP_SIZE  = 10;

    private MapCell map[][] = new MapCell[MAP_SIZE][MAP_SIZE];

    private char mapChar[][] = new char[MAP_SIZE][MAP_SIZE];


    World() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = new MapCell();
                mapChar[i][j] = ('*');
            }
        }
    }

    public void update() {
        //Update every MapCell and display
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j].update();
            }
        }
        display();
    }

    public void setCellOwner(int x, int y, byte id) {
        //Set cell owner
        map[y][x].setOwner(id);
        mapChar[y][x] = '1';
    }

    public boolean setCellOwnerByTurn(int x, int y, byte id) {
        if (!checkTurnAvailability(x, y, id)) {
            System.out.println("You have no right to do this turn. Try again");
            return true;
        }

        try {
            setCellOwner(x, y, id);
            return false;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Incorrect coordinates. Try again.");
            return true;
        }
    }

    private void display() {
        //Display map to screen
        clearConsole();
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.println();
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(mapChar[i][j] + " ");
            }
        }
        System.out.println();
    }

    public boolean checkTurnAvailability(int x, int y, byte id) {
        for(int row = y-1; row <= y+1; row++) {
            for(int col = x-1; col <= x+1; col++) {
                try {
                    if (map[row][col].getOwner() == id)
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
