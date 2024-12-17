package dishes;
import java.io.Serializable;

public abstract class Dish implements Serializable {
    private String name;
    private String description;
    private int calories;
    private int price;

    public Dish(String name, String description, int calories, int price) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.price = price;
    }

    //абстрактный метод для отображения информации о блюде
    public abstract void displayInfo();

    //абстрактный метод для получения информации, есть ли аллергены
    public abstract boolean isAllergenFree();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
