package com.base;


public class Player {

    private byte id;

    private static byte nextId = 1;

    Player() {
        id = nextId;
        nextId++;
    }

    public void makeTurn() {

    }

    public byte getId() {
        return id;
    }
}
