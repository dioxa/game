package com.base;


import com.base.InGameResources.Food;
import com.base.InGameResources.Resource;
import com.base.InGameResources.Wood;

import java.util.HashMap;
import java.util.Random;

public class MapCell {

    private HashMap<String, Resource> freeResources;

    private HashMap<String, Resource> cityResources;

    private int population;

    private byte ownerID = 0;

    MapCell() {
        // Init resources on map
        cityResources = new HashMap<String, Resource>();
        freeResources = new HashMap<String, Resource>();
        freeResources.put("Wood", new Wood());
        freeResources.put("Food", new Food());
        generate();
    }

    public void update() {

    }

    private void generate() {
        Random rand = new Random(40);
        freeResources.get("Wood").setAmount(rand.nextInt(60));
        freeResources.get("Food").setAmount(rand.nextInt(60));
    }

    public void setOwner(byte id) {
        ownerID = id;
    }

    public byte getOwner(){
        return ownerID;
    }

    public Resource getFreeResources(String resourceName) {
        return freeResources.get(resourceName);
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}