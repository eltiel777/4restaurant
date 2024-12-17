package dishes;

import java.io.Serializable;

public class Appetizer extends Dish implements Serializable {
    private boolean containsNuts;

    public Appetizer(String name, String description, int calories, int price, boolean containsNuts) {
        super(name, description, calories, price);
        this.containsNuts = containsNuts;
    }

    @Override //переопределение метода из класса-родителя
    public void displayInfo() {
        System.out.println("Закуска:");
        System.out.println("Название: " + getName());
        System.out.println("Описание: " + getDescription());
        System.out.println("Калории: " + getCalories());
        System.out.println("Цена: " + getPrice());
        System.out.println("Содержание орехов: " + (containsNuts ? "Да" : "Нет"));
    }


    @Override
    public boolean isAllergenFree() {
        return !containsNuts; //если не содержит орехов, блюдо подходит для аллергиков, вернет true
    }

}
