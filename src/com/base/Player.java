package com.base;

import java.util.Scanner;


public class Player {

    private int id;

    private World world;

    private static byte nextId = 1;

    public Player(World world) {

        id = nextId;
        nextId++;
        this.world = world;

    }


    public void makeTurn() {

        Scanner sc = new Scanner(System.in);
        int x, y;
        do {
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!world.setCellOwnerByTurn(x, y, id));

    }

    public int getId() {
        return id;
    }
}
