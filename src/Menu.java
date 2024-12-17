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
            System.out.println("7. Вывести блюда, подходящие для аллергиков");
            System.out.println("8. Выйти");
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
                    System.out.println("Вывод блюд, подходящих для аллергиков:");
                    for (Dish dish : restaurantMenu.getDishes()) { //перебираем каждый элемент коллекции, который возвращается с помощью get.dishes
                        if (dish.isAllergenFree()) { //если вернуло true, то
                            dish.displayInfo(); //показываем только те блюда, которые подходят для аллергиков
                        }
                    }
                    break;
                case 8:
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Ошибка! Введите число от 1 до 8.");
                    break;
            }

        } while (choice != 8);
    }

    //добавление блюда в меню
    private static void addDish() {
        try {
            System.out.print("Введите тип блюда (1 - Закуска, 2 - Основное блюдо, 3 - Десерт): ");
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
                } else if (name.matches("[A-Za-zА-Яа-яЁё\\s]+")) //если содержит подходящие символы
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
            Dish dish = null;
            try {
                switch (dishType) {
                    case 1:
                        //закуска: содержит ли она орехи
                        int nutsInput = -1;
                        while (nutsInput != 0 && nutsInput != 1) {
                            System.out.print("Содержит ли закуска орехи? (1 - да, 0 - нет): ");
                            nutsInput = scanner.nextInt();
                            if (nutsInput != 0 && nutsInput != 1) {
                                System.out.println("Ошибка: введите 1 или 0.");
                            }
                        }
                        boolean containsNuts = (nutsInput == 1); //если введено 1, то true, иначе false
                        dish = new Appetizer(name, description, calories, price, containsNuts);
                        break;
                    case 2:
                        //основное блюдо: содержит ли оно глютен
                        int glutenInput = -1;
                        while (glutenInput != 0 && glutenInput != 1) {
                            System.out.print("Содержит ли основное блюдо глютен? (1 - да, 0 - нет): ");
                            glutenInput = scanner.nextInt();
                            if (glutenInput != 0 && glutenInput != 1) {
                                System.out.println("Ошибка: введите 1 или 0.");
                            }
                        }
                        boolean containsGluten = (glutenInput == 1); //если введено 1, то true, иначе false
                        dish = new MainCourse(name, description, calories, price, containsGluten);
                        break;
                    case 3:
                        //десерт: содержит ли он лактозу
                        int lactoseInput = -1;
                        while (lactoseInput != 0 && lactoseInput != 1) {
                            System.out.print("Содержит ли десерт лактозу? (1 - да, 0 - нет): ");
                            lactoseInput = scanner.nextInt();
                            if (lactoseInput != 0 && lactoseInput != 1) {
                                System.out.println("Ошибка: введите 1 или 0.");
                            }
                        }
                        boolean containsLactose = (lactoseInput == 1); //если введено 1, то true, иначе false
                        dish = new Dessert(name, description, calories, price, containsLactose);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());  //обработка других ошибок
            }

            //добавление блюда в меню
            restaurantMenu.addDish(dish);
            System.out.println("Блюдо добавлено.");
        } catch (InputMismatchException e) { //если ввел не 1 или 0
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

    //загрузка из файла
    private static void loadFromFile() {
        System.out.print("Введите имя файла для загрузки меню (например, menu.dat): ");
        String fileName = scanner.nextLine();

        try {
            restaurantMenu = FileWork.loadRestaurantMenuFromFile(fileName); // Загружаем меню
            System.out.println("Меню загружено из файла: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке меню из файла: " + e.getMessage());
        }
    }
}
