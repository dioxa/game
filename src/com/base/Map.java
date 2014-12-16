package com.base;

public class Map {

    private static final int MAP_SIZE=15;

    public char[][] map = new char[MAP_SIZE][MAP_SIZE];

    public void generationMap () {
        double perc;
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++) {
                perc = Math.round(Math.random() * 9);
                if (perc == 2) {
                    map[i][j] ='S';
                } else {
                    if (perc == 4) {
                        map[i][j] ='T';
                    } else {
                        map[i][j] ='_';
                    }
                }
            }
    }

    public void getMap(){
        for (int i=0;i<MAP_SIZE;i++){
            for (int j=0;j<MAP_SIZE;j++){
                System.out.print("|" + map[i][j] + "|");
            }
            System.out.println();
        }
    }
}
