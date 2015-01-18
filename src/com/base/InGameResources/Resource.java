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
        if (  amount < 0){
            this.amount = 0;
        } else {
            this.amount = amount;
        }
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

}
