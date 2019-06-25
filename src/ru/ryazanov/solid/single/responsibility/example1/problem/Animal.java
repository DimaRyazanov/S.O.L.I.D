package ru.ryazanov.solid.single.responsibility.example1.problem;

public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void save(Animal animal){
        System.out.println("Сохраняем в базу Animal + " + animal.getName());
        //логика сохранения в БД
    }

}
