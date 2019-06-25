package ru.ryazanov.solid.single.responsibility.example2.solution;

public class DefaultGameValidator implements GameValidator {
    @Override
    public boolean isValid(Game game) {
        return game.getPrice() > 0;
    }
}
