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
        int i = sc.nextInt() - 1;
        int j = sc.nextInt() - 1;
        world.setCellPlayer(i, j, id);
    }

    public byte getId() {
        return id;
    }
}
