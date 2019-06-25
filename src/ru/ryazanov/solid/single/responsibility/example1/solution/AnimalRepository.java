package ru.ryazanov.solid.single.responsibility.example1.solution;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnimalRepository {
    private static final Map<String, Animal> database = new ConcurrentHashMap<>();

    public Animal get(String animalName){
        System.out.println("Загружаем из базы Animal по имени " + animalName);
        //логика загрузки из БД
        return database.get(animalName);
    }

    public void save(Animal animal){
        System.out.println("Сохраняем в базу Animal " + animal.getName());
        //логика сохранения в БД
        database.put(animal.getName(), animal);
    }
}
