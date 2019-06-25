# S.O.L.I.D
# Изучение принципов SOLID
###### *собрано из Интернет
1) **Single Responsibility Principle** _(Принцип единой ответственности)_

Определение: _Каждый класс выполняет лишь одну задачу._
Применение: SRP стоит применять только тогда, когда это оправдано. Принцип SRP можно применить только в том случае, когда:

- объекту класса становится позволительно слишком много;
- доменная логика концентрируется только в одном классе;
- любое изменение логики поведения объекта приводит к изменениям в других местах приложения, где это не подразумевалось изначально;
- приходится тестировать, исправлять ошибки, компилировать различные места приложения, даже если за их работоспособность отвечает третья сторона;
- невозможно легко отделить и применить класс в другой сфере приложения, так как это потянет ненужные зависимости.

**Пример №1 (Active Record):**

Класс **Animal** описывающее какое-то животное.
```java
public class Animal {
    public Animal(String name) {}
    public String getName() {}
    public void save(Animal animal){}
}
```
Он решает две задачи: 1) Манипулирует свойствами объекта в конструкторе и методе **getName**. 2) Работает с хранилишем данных в методе **save**.
В соответствии с принципом единой ответственности класс должен решать лишь какую-то одну задачу, а он же решает две.
Почему это плохо? Если изменится способ (порядок) работы с хранилищем данных (изменится бд и т.п.), то придется вносить измениния во все классы работающих с хранилищем.
Такая архитектура не отличается гибкостью, измение одной подсистемы, затрагивает другие.

Для решения данной проблемы создадим еще один класс **AnimalRepository**:
```java
public class Animal {
    public Animal(String name) {}
    public String getName() {}
}

public class AnimalRepository {
    public Animal get(String animalName){}
    public void save(Animal animal){}
}
```
Задача **AnimalRepository** - работа только с хранилишем, в частности сохрание/получение объекта класса **Animal** (методы **get**, **save**).

**Пример №2 (Validation):**

В проектах часто встречается проблема валидации данных (электронная почта, сложность пароля, длина никнейма. 
Например для класса **Game** необходима реалицация валидации по стоимости. Можно реализовать саммы простым способом, с помощью метода **isValid()**.
```java
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

    public boolean isValid(){
        return price > 0;
    }
}
```
Такой подход является вполне оправданным в данном случае. Код простой, тестированию поддается, дублирования логики нет.
Но теперь наш объект **Game** начал использоваться в некотором сервисе **VipCustomerService**, для работы с VIP покупателями наших игр.
```java
public class Game {
    ...
    public boolean isValid(boolean isVipCustomerService){
        if (isVipCustomerService)
            return price >= VIP_PRICE;

        return price > 0;
    }
}
```
При предыдущей реализации нам придется изменять сам класс **Game** изменив метод **isValid()**, так как логика будет изменяться, например при добавлении новых сервисов.

```java
public class Game {
    ...
    public boolean isValid(boolean isVipCustomerService, boolean isPopularCustomerService){
        if (isVipCustomerService)
            return price >= VIP_PRICE;

        if (isPopularCustomerService)
            return price >= POPULAR_PRICE_MIN && price < POPULAR_PRICE_MAX;

        return price > 0;
    }
}
```
Решение: Стало очевидно, что при дальнейшем использовании объекта **Game** логика валидации его данных будет изменяться и усложняться. Видимо пора отдать ответственность за валидацию данных **Game** другому объекту - **GameValidator**, со своими конкретными реализациями. Причем надо сделать так, чтобы сам объект **Game** не зависел от конкретной реализации его валидатора. Например заинжектим валидатор через конструктор.

```java
public interface GameValidator {
    boolean isValid(Game game);
}
public class DefaultGameValidator implements GameValidator {
    @Override
    public boolean isValid(Game game) {
        return game.getPrice() > 0;
    }
}
public class VipGameValidator implements GameValidator {
    @Override
    public boolean isValid(Game game) {
        return game.getPrice() > VIP_PRICE;
    }
}
public class PopularGameValidator implements GameValidator {
    @Override
    public boolean isValid(Game game) {
        return game.getPrice() >= POPULAR_PRICE_MIN && game.getPrice() < POPULAR_PRICE_MAX;
    }
}

public class Game {
    private GameValidator validator;
    ...
    public Game() {
        this(new DefaultGameValidator());
    }

    public Game(GameValidator validator) {
        this.validator = validator;
    }
    ...    
    public boolean isValid(){
        return validator.isValid(this);
    }
}
```
Имеем объект **Game** отдельно, а любое количество всяческих валидаторов отдельно. Например используя для VipCustomerService
```java
Game game = new Game(new VipGameValidator());
```
