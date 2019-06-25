package ru.ryazanov.solid.single.responsibility.example2.solution;

public class Game {
    private String name;
    private int price;

    private GameValidator validator;

    public Game() {
        this(new DefaultGameValidator());
    }

    public Game(GameValidator validator) {
        this.validator = validator;
    }

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

    public boolean isValid(){
        return validator.isValid(this);
    }
}
