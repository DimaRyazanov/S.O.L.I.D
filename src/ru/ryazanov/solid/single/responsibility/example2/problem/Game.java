package ru.ryazanov.solid.single.responsibility.example2.problem;

import static ru.ryazanov.solid.single.responsibility.example2.problem.PriceUtil.*;

public class Game {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isValid(boolean isVipCustomerService, boolean isPopularCustomerService){
        if (isVipCustomerService)
            return price >= VIP_PRICE;

        if (isPopularCustomerService)
            return price >= POPULAR_PRICE_MIN && price < POPULAR_PRICE_MAX;

        return price > 0;
    }
}
