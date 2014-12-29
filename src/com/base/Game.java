package com.base;


import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Player player;

    private World world;

    private final byte secondsToUpdate = 5;

    private Timer worldTimer = new Timer();

    Game(){

        world = new World();
        player = new Player();
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run() {
                start();
            }
        };
        worldTimer.schedule(gameLoop, 0, secondsToUpdate * 1000);
    }

    public void start(){

        player.makeTurn();
        world.update();

    }


}
