package com.base.InGameResources;

public abstract class Resource {

    protected double amount;

    protected String name;

    /**
     * Присваивает дочернему классу поле name, равное
     * назвнию этого класса.
     */
    Resource() {
        this.name = getClass().getSimpleName();
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Устанавливает новое количество, и проверяет его
     * на отрицательное значение.
     * @param amount Новое количество.
     */
    public void setAmount(double amount){
        if (amount < 0){
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
