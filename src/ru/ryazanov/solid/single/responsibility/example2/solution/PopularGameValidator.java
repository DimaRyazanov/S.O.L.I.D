package ru.ryazanov.solid.single.responsibility.example2.solution;

import static ru.ryazanov.solid.single.responsibility.example2.problem.PriceUtil.POPULAR_PRICE_MAX;
import static ru.ryazanov.solid.single.responsibility.example2.problem.PriceUtil.POPULAR_PRICE_MIN;

public class PopularGameValidator implements GameValidator {
    @Override
    public boolean isValid(Game game) {
        return game.getPrice() >= POPULAR_PRICE_MIN && game.getPrice() < POPULAR_PRICE_MAX;
    }
}
