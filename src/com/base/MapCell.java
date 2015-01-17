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
        cityResources.put("Wood", new Wood());
        cityResources.put("Food", new Food());
        generate();
    }

    public void update() {
        if (ownerID != 0 && population > 0) {
            population += 1;
        }

    }

    private void generate() {
        Random rand = new Random();
        freeResources.get("Wood").setAmount(rand.nextInt(20) + 40);
        freeResources.get("Food").setAmount(rand.nextInt(20) + 40);
        cityResources.get("Wood").setAmount(0);
        cityResources.get("Food").setAmount(0);

    }

    public void setCityResources(String resourceName, double amount){
        cityResources.get(resourceName).setAmount(amount);
    }

    public void setFreeResources(String resourceName, double amount){
        freeResources.get(resourceName).setAmount(amount);
    }

    public void setOwner(int id) {
        ownerID = id;
        population = 1;
    }

    public int getOwner(){
        return ownerID;
    }

    public int getPopulation() {
        return population;
    }

    public Double getFreeResources(String resourceName) {
        return freeResources.get(resourceName).getAmount();
    }

    public Double getCityResources(String resourceName) {
        return cityResources.get(resourceName).getAmount();
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}