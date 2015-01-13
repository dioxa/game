package com.base;

import java.util.Scanner;

public class Game {

    private Player player;

    private World world;

    private final byte secondsToUpdate = 5;

    private boolean gameOver = false;

    public Game(){

        world = new World();
        player = new Player(world);
        world.setCellOwner(0, 0, player.getId());
    }

    public void start(){
        Scanner sc = new Scanner(System.in);
        do {
            waitSeconds(secondsToUpdate);
            world.update();
            System.out.println("Хотите ли вы сделать ход? y/n");
            if (sc.nextLine().toLowerCase().equals("y")) player.makeTurn();
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
