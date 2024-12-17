package dishes;

import java.io.Serializable;

public class MainCourse extends Dish implements Serializable {
    private boolean containsGluten;

    public MainCourse(String name, String description, int calories, int price, boolean containsGluten) {
        super(name, description, calories, price);
        this.containsGluten = containsGluten;
    }


    @Override //переопределение метода из класса-родителя
    public void displayInfo() {
        System.out.println("Основное блюдо:");
        System.out.println("Название: " + getName());
        System.out.println("Описание: " + getDescription());
        System.out.println("Калории: " + getCalories());
        System.out.println("Цена: " + getPrice());
        System.out.println("Содержит глютен: " + (containsGluten ? "Да" : "Нет"));
    }


    @Override
    public boolean isAllergenFree() {
        return !containsGluten; //если не содержит глютен, блюдо подходит для аллергиков, вернет true
    }
}