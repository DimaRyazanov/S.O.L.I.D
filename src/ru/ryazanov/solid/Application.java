package ru.ryazanov.solid;

import ru.ryazanov.solid.single.responsibility.SingleResponsibilityDescription;

public class Application {
    public static void main(String[] args) {
        Description description = new SingleResponsibilityDescription();
        System.out.println(description.principles());
    }
}
