package com.base;


public class Game {

    public final static byte MAP_SIZE  = 5;

    private MapCell map[][] = new MapCell[MAP_SIZE][MAP_SIZE];

    private char mapChar[][] = new char[MAP_SIZE][MAP_SIZE];

    Game(){
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j]= new MapCell();
                mapChar[i][j]=('_');
            }
        }
    }

    public void start(){
        System.out.print(map[0][0].getFreeResources("Wood").getName());
    }

    public void updateWorld(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j].update();
            }
        }
    }

}
