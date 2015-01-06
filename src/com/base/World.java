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
        map[y][x].setOwner(id);
        mapChar[y][x] = '1';
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

<<<<<<< HEAD
    public boolean checkTurn(int x, int y, byte id) {
        return (map[x-1][y-1].getOwner() == id || map[x][y-1].getOwner() == id || map[x+1][y-1].getOwner() == id ||
                map[x-1][y].getOwner() == id || map[x+1][y].getOwner() == id ||map[x-1][y+1].getOwner() == id ||
                map[x][y+1].getOwner() == id || map[x+1][y+1].getOwner() == id);
        }

=======
>>>>>>> origin/master
    private void clearConsole() {
        for (int i = 0; i < 9; i++){
            System.out.println(' ');
        }
    }

}
