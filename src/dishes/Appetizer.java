package dishes;

public class Appetizer extends Dish {

    public Appetizer(String name, String description, int calories, int price) {
        super(name, description, calories, price);
    }

    @Override //переопределение метода из класса-родителя
    public void displayInfo() {
        System.out.println("Закуска:");
        System.out.println("Название: " + getName());
        System.out.println("Описание: " + getDescription());
        System.out.println("Калории: " + getCalories());
        System.out.println("Цена: " + getPrice());
    }
}
