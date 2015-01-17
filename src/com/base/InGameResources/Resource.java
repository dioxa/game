package com.base.InGameResources;

public abstract class Resource {

    protected double amount;

    protected String name;

    Resource() {
        this.name = getClass().getSimpleName();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

}
