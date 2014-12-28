package com.base;


import com.base.InGameResources.Food;
import com.base.InGameResources.Resource;
import com.base.InGameResources.Wood;

import java.util.HashMap;

public class MapCell {

    private HashMap<String, Resource> freeResources;

    private HashMap<String, Resource> cityResources;

    private int population;


    MapCell() {
        cityResources = new HashMap<String, Resource>();
        freeResources = new HashMap<String, Resource>();
        freeResources.put("Wood", new Wood());
        freeResources.put("Food", new Food());
    }

    public void update() {

    }

    public Resource getFreeResources(String resourceName) {
        return freeResources.get(resourceName);
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}