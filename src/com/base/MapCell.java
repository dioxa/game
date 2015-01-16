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

    private int ownerID = 0;

    // Init resources on map
    public MapCell() {

        cityResources = new HashMap<String, Resource>();
        freeResources = new HashMap<String, Resource>();
        freeResources.put("Wood", new Wood());
        freeResources.put("Food", new Food());
        generate();

    }

    public void update() {
        if (ownerID != 0 && population > 1) population += 1;
    }

    private void generate() {

        Random rand = new Random();
        freeResources.get("Wood").setAmount(rand.nextInt(20) + 40);
        freeResources.get("Food").setAmount(rand.nextInt(20) + 40);

    }

    public void setOwner(int id) {
        ownerID = id;
        population = 1;
    }

    public void shippingPopulation(int populationToShipping){
        population += populationToShipping;
    }

    public int getOwner(){
        return ownerID;
    }

    public int getPopulation() {
        return population;
    }

    public Resource getFreeResources(String resourceName) {
        return freeResources.get(resourceName);
    }

    public Resource getCityResources(String resourceName) {
        return cityResources.get(resourceName);
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}