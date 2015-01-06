package com.base;

public class Game {

    private Player player;

    private World world;

    private final byte secondsToUpdate = 5;

    private boolean gameOver = false;

    Game(){

        world = new World();
        player = new Player(world);
        world.setCellOwner(0, 0, player.getId());
    }

    public void start(){

        do {

            waitSeconds(secondsToUpdate);
            world.update();
            player.makeTurn();

        } while(!gameOver);
    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }
    }

}
