package com.base;

import java.util.Scanner;


public class Player {

    private byte id;

    private World world;

    private static byte nextId = 1;

    Player(World world) {
        id = nextId;
        nextId++;
        this.world = world;
    }


    public void makeTurn() {
        Scanner sc = new Scanner(System.in);
<<<<<<< HEAD
        int x, y;
        do {
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;

        }while ( world.checkTurn(x, y, id) );
=======
        int x = sc.nextInt() - 1;
        int y = sc.nextInt() - 1;
>>>>>>> origin/master
        world.setCellOwner(x, y, id);
    }

    public byte getId() {
        return id;
    }
}
