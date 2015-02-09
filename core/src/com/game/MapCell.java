package com.game;


import com.game.InGameResources.Food;
import com.game.InGameResources.Resource;
import com.game.InGameResources.Wood;

import java.util.HashMap;
import java.util.Random;


public class MapCell {

    private HashMap<String, Resource> freeResources;

    private HashMap<String, Resource> cityResources;

    private HashMap<String,String> resources;

    private int population;

    private final double foodRate = 0.02;

    private int ownerID = 0;

    /**
     * Инициализация ресурсов на карте.
     */
    public MapCell() {

        cityResources = new HashMap<>();
        freeResources = new HashMap<>();
        resources = new HashMap<>();
        freeResources.put("Wood", new Wood());
        freeResources.put("Food", new Food());
        cityResources.put("Wood", new Wood());
        cityResources.put("Food", new Food());
        generate();
    }

    /**
     * Обработка данных связанных с клеткой. Вызывает updateResources(int turn).
     * @param turn Номер хода.
     */
    public void update(int turn) {
        if (getCityResources("Food") > 0) {
            population += 1;
        } else {
            population -= 1;
        }
        updateResources(turn);
    }

    /**
     * Обновляет ресурсы на клетке. Некоторые обновления зависят
     * от текущего хода.
     * @param turn Текущий ход.
     */
    private void updateResources(int turn){
        if (turn % 3 == 0) {
            double amount = population * 0.1;
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

    /**
     * Генерирует начальное количество ресурсов на карте.
     */
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

    /**
     * Устанвливает владелька клетки, и ставить население 1.
     * Используется для первого присваивания клетки.
     * @param id ID игрока.
     */
    public void setOwner(int id) {
        ownerID = id;
        population = 1;
    }

    public HashMap<String, String> getCellInfo(){
        resources.put("Population", String.valueOf(getPopulation()));
        for (String res: freeResources.keySet()){
            resources.put("Free " + res, String.valueOf(freeResources.get(res).getAmount()));
            resources.put("City" + res, String.valueOf(cityResources.get(res).getAmount()));
        }
        return resources;
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