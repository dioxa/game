package com.base.InGameResources;

import java.util.Random;

public abstract class Resource {

    protected int amount;

    protected String name;

    protected final Random random = new Random(10);

    Resource() {
        this.name = getClass().getSimpleName();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

}
