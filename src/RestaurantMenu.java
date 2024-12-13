import java.io.*;
import java.util.*;
import dishes.Dish;
import dishes.Appetizer;
import dishes.MainCourse;
import dishes.Dessert;


public class RestaurantMenu implements Serializable {
    private static List<Dish> dishes; //dishes - список объектов типа Dish лист это тип данных для хранения

    //конструктор создания пустого списка
    public RestaurantMenu() {
        dishes = new ArrayList<>(); //ArrayList динамически изменяет размер
    }

    //добавление блюда в меню
    public static void addDish(Dish dish) {
        dishes.add(dish);
    }

    //удаление блюда из меню
    public static void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    //поиск блюда по названию
    public static Dish findDish(String name) {
        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);  //получаем блюдо этого индекса
            if (dish.getName().equalsIgnoreCase(name)) { //проверяем соответствие названий
                return dish;
            }
        }
        return null;
    }

    //отображение самого меню
    public static void displayMenu() {
        if (dishes.isEmpty()) {
            System.out.println("Меню пусто.");
        } else {
            for (int i = 0; i < dishes.size(); i++) {
                Dish dish = dishes.get(i);
                dish.displayInfo();
                System.out.println("-------------------------");
            }
        }
    }

    //получить список блюд
    public static List<Dish> getDishes() {
        return dishes;
    }
}
