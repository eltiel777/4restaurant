import java.io.*;
import java.util.*;
import dishes.Dish;

public class RestaurantMenu implements Serializable {
    private List<Dish> dishes;
    //поле - коллекция объектов типа блюд
    //список блюд принадлежит конкретному объекту

    //конструктор создания пустого списка
    public RestaurantMenu() {
        this.dishes = new ArrayList<>(); //ArrayList динамически изменяет размер
    }

    //добавление блюда в меню
    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    //удаление блюда из меню
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    //поиск блюда по названию
    public Dish findDish(String name) {
        for (int i = 0; i < dishes.size(); i++) {  //перебираем
            Dish dish = dishes.get(i);            //получаем элемент по индексу
            if (dish.getName().equalsIgnoreCase(name)) { //проверяем соответствует ли имя
                return dish; //возвращаем найденное блюдо
            }
        }
        return null; //возвращаем null, если блюдо не найдено
    }

    //отображение самого меню
    public void displayMenu() {
        if (dishes.isEmpty()) {
            System.out.println("Меню пусто.");
        } else {
            for (Dish dish : dishes) { //перебираем все эоементы
                dish.displayInfo();
                System.out.println("-------------------------");
            }
        }
    }

    //получить список блюд
    public List<Dish> getDishes() {
        return dishes;
    }
}


