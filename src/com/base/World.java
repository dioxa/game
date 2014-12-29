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

    private void display() {
        //Display map to screen
        clearConsole();
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.println();
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(mapChar[i][j] + " ");
            }
        }
    }

    private void clearConsole(){
        for (int i=0; i<9; i++){
            System.out.println(' ');
        }
    }

}
