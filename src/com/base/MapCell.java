package com.base;


import com.base.InGameResources.Food;
import com.base.InGameResources.Resource;
import com.base.InGameResources.Wood;

import java.util.HashMap;
import java.util.Random;

import static java.lang.Math.cbrt;

public class MapCell {

    private HashMap<String, Resource> freeResources;

    private HashMap<String, Resource> cityResources;

    private int population;

    private final double foodRate = 0.025;

    private int ownerID = 0;

    // Init resources on map
    public MapCell() {

        cityResources = new HashMap<>();
        freeResources = new HashMap<>();
        freeResources.put("Wood", new Wood());
        freeResources.put("Food", new Food());
        cityResources.put("Wood", new Wood());
        cityResources.put("Food", new Food());
        generate();
    }

    public void update(int turn) {
        if (getCityResources("Food") > 0) {
            population += 1;
        } else {
            population -= 1;
        }
        updateResources(turn);
    }

    private void updateResources(int turn){
        if (turn % 3 == 0) {
            double amount = cbrt(population);
            for (String resource : freeResources.keySet()) {
                if (getFreeResources(resource) > amount){
                    setCityResources(resource, getCityResources(resource) + amount);
                    setFreeResources(resource, getFreeResources(resource) - amount);
                } else {
                    setCityResources(resource, getCityResources(resource) + getFreeResources(resource));
                    setFreeResources(resource, getFreeResources(resource) - getFreeResources(resource));
                }
            }
        }
        setCityResources("Food", getCityResources("Food") - (population * foodRate));
    }

    private void generate() {
        Random rand = new Random();
        for (String resource : freeResources.keySet()) {
            freeResources.get(resource).setAmount(rand.nextInt(20) + 40);
            cityResources.get(resource).setAmount(rand.nextInt(4) + 1);
        }
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

    public double getFreeResources(String resourceName) {
        return freeResources.get(resourceName).getAmount();
    }

    public double getCityResources(String resourceName) {
        return cityResources.get(resourceName).getAmount();
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}