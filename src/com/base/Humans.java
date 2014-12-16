package com.base;

public class Humans{

    public int age=500;

    protected byte stick = 0;

    protected boolean tool = false;

    protected byte stone=0;

    protected boolean home = false;

    protected boolean sex= true;

    protected byte wood = 0;

    protected int x,y,resX=0, resY=0;

    public void live(){
        if (!home){
            if (tool)
            bildHome();
            else{
                getTool();
                bildHome();
            }
        }
    }

    public void turn(){
        map[y][x]='_';
        if (x>resX && y>resY)
        {
            x-=1;
            y-=1;
            map[y][x]='P';
        }else if (y>resY){
            y-=1;
            map[y][x]='P';
        }else if (x<resX && y>resY){
            x+=1;
            y-=1;
            map[y][x]='P';
        }else if (x>resX){
            x-=1;
            map[y][x]='P';
        }else if (x<resX){
            x+=1;
            map[y][x]='P';
        }else if (x>resX && y>resY){
            x-=1;
            y+=1;
            map[y][x]='P';
        }else if (y<resY){
            y+=1;
            map[y][x]='P';
        }else {
            x+=1;
            y+=1;
            map[y][x]='P';
        }
        age-=1;
        getMap();
    }

    public void bildHome(){
        if (wood==10){
            map[y][x]='H';
            home=true;
            wood-=10;}
        else{
            getWood();
            map[y][x]='H';
            home=true;
            wood-=10;
        }

    }

    public void findResources(char res){
        for (int i = +2; i < -2; i++)
            for (int j = +2; j < -2; j++) {
                if (map[i][j]==res){
                    resX=j;
                    resY=i;
                }
            }

    }

    public void getWood(){
        findResources('T');
        turn();
    }

    public void getTool(){
        if (stick>=1 && stone>=1){
            tool=true;
            stick-=1;
            stone-=1;
        }
        else{if (wood==0) {
            getWood();
            tool=true;
            stick-=1;
            stone-=1;}
        else {getStone();
            tool=true;
            stick-=1;
            stone-=1;}
        }
    }

    public void getStone(){
        findResources('W');
    }
}
