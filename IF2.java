
import java.util.Scanner;

public class IF2 {
    public static void main(String[] args) {
        System.out.println("введите число от 1-3");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        switch (num) {
            case 1 -> System.out.println("вы ввели - 1");
            case 2 -> System.out.println("вы ввели - 2");
            case 3 -> System.out.println("вы ввели - 3");
            default -> System.out.println("вы ввели неверное число");
        }

        scanner.close(); // Закрываем Scanner после использования
    }
}
