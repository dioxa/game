package com.base;

public class Main {

    public static void main(String[] args) {
	    Map world= new Map();
        world.generationMap();
        world.getMap();
        Humans Stas= new Humans();
        do{
            Stas.live();
        }while(Stas.age!=0);
    }
}
