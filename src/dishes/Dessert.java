package dishes;

import java.io.Serializable;

public class Dessert extends Dish implements Serializable {
    private boolean containsLactose;

    public Dessert(String name, String description, int calories, int price, boolean containsLactose) {
        super(name, description, calories, price); //вызов конструктора родительского класса
        this.containsLactose = containsLactose;
    }

    @Override //переопределение метода из класса-родителя
    public void displayInfo() {
        System.out.println("Десерт:");
        System.out.println("Название: " + getName());
        System.out.println("Описание: " + getDescription());
        System.out.println("Калории: " + getCalories());
        System.out.println("Цена: " + getPrice());
        System.out.println("Содержит лактозу: " + (containsLactose ? "Да" : "Нет"));
    }

    @Override
    public boolean isAllergenFree() {
        return !containsLactose; //если не содержит лактозу, блюдо подходит для аллергиков, вернет true
    }
}
