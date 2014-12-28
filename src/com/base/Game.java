package com.base;


import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public final static byte MAP_SIZE  = 10;

    public final byte secondsToUpdate = 5;

    private MapCell map[][] = new MapCell[MAP_SIZE][MAP_SIZE];

    private char mapChar[][] = new char[MAP_SIZE][MAP_SIZE];

    private Timer worldTimer = new Timer();

    Game(){
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j]= new MapCell();
                mapChar[i][j]=('*');
            }
        }
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run() {
                start();
            }
        };
        worldTimer.schedule(gameLoop, 0, secondsToUpdate * 1000);
    }

    public void start(){

        updateWorld();

    }

    private void updateWorld(){

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j].update();
            }
        }
        drawWorld();

    }

    private void drawWorld() {
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.println();
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(mapChar[i][j] + " ");
            }
        }
    }

}
