package ru.ryazanov.solid.single.responsibility.example2.solution;

import static ru.ryazanov.solid.single.responsibility.example2.problem.PriceUtil.VIP_PRICE;

public class VipGameValidator implements GameValidator {
    @Override
    public boolean isValid(Game game) {
        return game.getPrice() > VIP_PRICE;
    }
}
