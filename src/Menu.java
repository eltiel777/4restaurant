import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import dishes.Dish;
import dishes.Appetizer;
import dishes.MainCourse;
import dishes.Dessert;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static RestaurantMenu restaurantMenu = new RestaurantMenu();

    //главное меню
    public static void showMenu() {
        int choice = 0;
        do {
            System.out.println(" - - - Меню - - - ");
            System.out.println("1. Добавить блюдо");
            System.out.println("2. Посмотреть меню");
            System.out.println("3. Удалить блюдо");
            System.out.println("4. Поиск блюда по названию");
            System.out.println("5. Сохранить меню в файл");
            System.out.println("6. Загрузить меню из файла");
            System.out.println("7. Выйти");
            System.out.print("Ваш выбор: ");

            //проверяем, что введено корректное число
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка! Введите корректное число.");
                scanner.nextLine(); //очищаем буфер при некорректном вводе
            }
            choice = scanner.nextInt(); //cчитываем число
            scanner.nextLine(); //очищаем буфер от остаточного ввода

            switch (choice) {
                case 1:
                    addDish();
                    break;
                case 2:
                    restaurantMenu.displayMenu();
                    break;
                case 3:
                    removeDish();
                    break;
                case 4:
                    searchDish();
                    break;
                case 5:
                    saveToFile();
                    break;
                case 6:
                    loadFromFile();
                    break;
                case 7:
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Ошибка! Введите число от 1 до 7.");
                    break;
            }

        } while (choice != 7);
    }

    //добавление блюда в меню
    private static void addDish() {
        try {
            System.out.print("Введите тип блюда (1 - Основное, 2 - Десерт, 3 - Закуска): ");
            int dishType = -1;

            //ввод типа блюда
            while (true) {
                if (scanner.hasNextInt()) {
                    dishType = scanner.nextInt();
                    if (dishType == 1 || dishType == 2 || dishType == 3) {
                        break;
                    }
                }
                System.out.println("Ошибка! Введите 1, 2 или 3.");
                scanner.nextLine(); //очистка буфера при ошибке
            }

            scanner.nextLine(); //очистка буфера после ввода числового значения

            //ввод названия блюда
            String name = "";
            while (true)
            {
                System.out.print("Введите название блюда: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty())
                {
                    System.out.println("Ошибка! Название блюда не может быть пустым.");
                } else if (name.matches("[A-Za-zА-Яа-яЁё\\s]+"))
                {
                    break;
                } else
                {
                    System.out.println("Ошибка! Название блюда может содержать только буквы и пробелы.");
                }
            }

            //ввод описания блюда
            System.out.print("Введите описание блюда: ");
            String description = scanner.nextLine().trim();

            //ввод калорийности блюда
            System.out.print("Введите калорийность блюда: ");
            int calories = -1;
            while (true) {
                if (scanner.hasNextInt()) {
                    calories = scanner.nextInt();
                    if (calories >= 0) {
                        break;
                    }
                }
                System.out.println("Ошибка! Введите положительное целое число.");
                scanner.nextLine(); //очистка буфера
            }

            //ввод цены блюда
            System.out.print("Введите цену блюда: ");
            int price = -1;
            while (true) {
                if (scanner.hasNextInt()) {
                    price = scanner.nextInt();
                    if (price > 0) {
                        break;
                    }
                }
                System.out.println("Ошибка! Введите положительное число.");
                scanner.nextLine(); //очистка буфера
            }

            //создание блюда соответственно его типу
            Dish dish;
            switch (dishType) {
                case 1:
                    dish = new Appetizer(name, description, calories, price);
                    break;
                case 2:
                    dish = new MainCourse(name, description, calories, price);
                    break;
                case 3:
                    dish = new Dessert(name, description, calories, price);
                    break;
                default:
                    System.out.println("Некорректный тип блюда.");
                    return;
            }

            //добавление блюда в меню
            restaurantMenu.addDish(dish);
            System.out.println("Блюдо добавлено.");
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Пожалуйста, введите корректные данные.");
            scanner.nextLine(); //очистка буфера
        }
    }

    //поиск блюда по названию
    private static void searchDish() {
        System.out.print("Введите название блюда для поиска: ");
        String name = scanner.nextLine(); //вводим название

        Dish dish = restaurantMenu.findDish(name); //вызываем метод поиска в меню, если нашло то вывод, иначе null
        if (dish != null) {
            dish.displayInfo();
        } else {
            System.out.println("Блюдо не найдено.");
        }
    }

    //удалить блюдо
    private static void removeDish() {
        System.out.print("Введите название блюда для удаления: ");
        String name = scanner.nextLine();

        Dish dish = restaurantMenu.findDish(name);
        if (dish != null) {
            restaurantMenu.removeDish(dish);
            System.out.println("Блюдо удалено.");
        } else {
            System.out.println("Такого блюда нет.");
        }
    }

    //сохранение в файл
    private static void saveToFile() {
        System.out.print("Введите имя файла для сохранения: ");
        String fileName = scanner.nextLine();

        try {
            FileWork.saveRestaurantMenuToFile(restaurantMenu, fileName);
            System.out.println("Меню сохранено в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении меню в файл: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        System.out.print("Введите имя файла для загрузки меню (например, menu.txt): ");
        String fileName = scanner.nextLine();

        try {
            restaurantMenu = FileWork.loadRestaurantMenuFromFile(fileName); // загружаем меню в статический объект
            System.out.println("Меню загружено из файла: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке меню из файла: " + e.getMessage());
        }
    }
}
