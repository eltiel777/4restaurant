import java.io.*;
import java.util.Scanner;
import dishes.Dish;
import dishes.Appetizer;
import dishes.MainCourse;
import dishes.Dessert;

public class FileWork {

    //сохраняем меню в файл
    public static void saveRestaurantMenuToFile(RestaurantMenu menu, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Dish dish : menu.getDishes()) { //перебираем все блюда в меню, каждому блюду переменная dish, ссылается на текущее блюдо в списке
                String dishText = dishToText(dish);  //преобразуем каждое блюдо в строку
                writer.write(dishText);
                writer.newLine();  //добавляем новую строку для каждого блюда
            }
            System.out.println("Меню успешно сохранено в файл " + fileName);
        } catch (IOException e) {
            throw new IOException("Ошибка при сохранении файла: " + e.getMessage(), e);
        }
    }

    //загрузка меню из текстового файла
    public static RestaurantMenu loadRestaurantMenuFromFile(String fileName) throws IOException {
        RestaurantMenu menu = new RestaurantMenu();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; //каждая строка считанная будет тут
            while ((line = reader.readLine()) != null) { //пока не пусто считываем
                Dish dish = textToDish(line);  //преобразуем строку в объект блюда
                if (dish != null) {
                    menu.addDish(dish); //добавляем в меню
                }
            }
        } catch (IOException e) {
            throw new IOException("Ошибка при загрузке файла: " + e.getMessage(), e);
        }
        return menu;
    }

    //преобразование блюда в строку
    private static String dishToText(Dish dish) {
        return String.join(";",
                dish.getClass().getSimpleName(), //тип блюда (название класса)
                dish.getName(),                  //название
                dish.getDescription(),           //описание
                String.valueOf(dish.getCalories()), //калорийность, преобразование в строку
                String.valueOf(dish.getPrice())  //цена
        );
    }

    //преобразование строки в блюдо
    private static Dish textToDish(String text) {
        String[] parts = text.split(";"); //разделяем на части по знаку
        if (parts.length != 5) {
            return null; //если строка не соответствует формату 5 частей
        }

        String type = parts[0];
        String name = parts[1];
        String description = parts[2];
        int calories = Integer.parseInt(parts[3]);
        int price = Integer.parseInt(parts[4]);

        switch (type) {
            case "Appetizer":
                return new Appetizer(name, description, calories, price);
            case "MainCourse":
                return new MainCourse(name, description, calories, price);
            case "Dessert":
                return new Dessert(name, description, calories, price);
            default:
                return null;
        }
    }
}
