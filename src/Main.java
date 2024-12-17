import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //создаем сканер для ввода с клавиатуры
        Menu.showMenu();
        scanner.close();
    }
}
