package AllAssistent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class ApartmentCalculator{

    public static void main(String[] args) {

        Locale.setDefault(Locale.US); //Если вся программа работает только в одной локали, можно изменить локаль для всех операций с числами:
//Однако этот способ менее предпочтителен, так как может повлиять на работу других частей кода
        Scanner sc = new Scanner(System.in);
       // sc.useLocale(Locale.US); //`Locale.US` для точки как разделителя:

        //создаем фйл для записи вывода в файл result.txt
        try (PrintWriter writer = new PrintWriter
                (new PrintWriter("result.txt"))) {


            // вывод количества комнат на консоль
            System.out.println("Введите количество комнат в квартире:");
            int roomCount = sc.nextInt();
             // вывод количества комнат в файл 
            writer.println("количество комнат в квартире: " + roomCount);

            double totalFloorArea = 0;
            double totalWallArea = 0;
            double totalWindowsArea = 0;
            double totalDoorsArea = 0;

            double totalWindowsSlopeLength = 0;
            double totalDoorsSlopeLength = 0;


            // Цикл по всем комнатам
            for (int i = 1; i <= roomCount; i++) {

                writer.println("===== Комната №" + i + " =====");
                System.out.println("===== Комната №" + i + " =====");

// Считываем размеры комнаты
                System.out.println("Введите длину комнаты (в метрах):");
                double length = sc.nextDouble();
                System.out.println("Введите ширину комнаты (в метрах):");
                double width = sc.nextDouble();
                System.out.println("Введите высоту комнаты (в метрах):");
                double height = sc.nextDouble();

// Считаем параметры комнаты
                double floorArea = calcFloorArea(length, width);
                double wallArea = calcWallArea(length, width, height);
                double roomPerimeter = calcRoomPerimeter(length, width);

                writer.println("Площадь пола: " + floorArea + " м².");
                writer.println("Периметр комнаты: " + roomPerimeter + " м.");
                writer.println("Площадь стен до вычета окон и дверей: " + wallArea + " м².");

                System.out.println("Площадь пола: " + floorArea + " м².");
                System.out.println("Периметр комнаты: " + roomPerimeter + " м.");
                System.out.println("Площадь стен до вычета окон и дверей: " + wallArea + " м².");

// вводим количество окон и их размеры
                System.out.println("Введите количество окон:");
                int windowsCount = sc.nextInt();

               writer.println("кол-во окон: " + windowsCount);

                double roomWindowsArea = 0;
                double roomWindowsSlopeLength = 0;
                for (int j = 1; j <= windowsCount; j++) {
                    System.out.println("Введите ширину окна " + j + " (в метрах):");
                    double windowWidth = sc.nextDouble();
                    System.out.println("Введите высоту окна " + j + " (в метрах):");
                    double windowHeight = sc.nextDouble();
                    roomWindowsArea += calcRectangleArea(windowWidth, windowHeight);
                    roomWindowsSlopeLength += 2 * (windowWidth + windowHeight);
                }
                totalWindowsSlopeLength += roomWindowsSlopeLength;

                writer.println("Площадь окон: " + roomWindowsArea + "м.кв");
                System.out.println("Площадь окон: " + roomWindowsArea + " м².");
                writer.println("длина откосов окон: " + totalWindowsSlopeLength + "м.");
                System.out.println("длина откосов окон: " + totalWindowsSlopeLength + " м.");

// Считываем количество дверей и их размеры
                System.out.println("Введите количество дверей:");
                int doorsCount = sc.nextInt();
                writer.println("кол-во дверей: " + doorsCount);

                double roomDoorsArea = 0;
                double roomDoorsSlopeLength = 0;
                for (int j = 1; j <= doorsCount; j++) {
                    System.out.println("Введите ширину двери " + j + " (в метрах):");
                    double doorWidth = sc.nextDouble();
                    System.out.println("Введите высоту двери " + j + " (в метрах):");
                    double doorHeight = sc.nextDouble();
                    roomDoorsArea += calcRectangleArea(doorWidth, doorHeight);
                    roomDoorsSlopeLength += 2 * (doorWidth + doorHeight);
                }
                totalDoorsSlopeLength +=roomDoorsSlopeLength;
            writer.println("Площадь дверей: " + roomDoorsArea + " м².");
                System.out.println("Площадь дверей: " + roomDoorsArea + " м².");
            writer.println("общая длина откосов дверей: " + roomDoorsSlopeLength + " м.");
            System.out.println("общая длина откосов дверей: " + roomDoorsSlopeLength + "  м.");
// Вычитаем площадь окон и дверей из площади стен
                double finalWallArea = wallArea - roomWindowsArea - roomDoorsArea;

                writer.println("Площадь стен (после вычета окон и дверей): " + finalWallArea + " м².");
                System.out.println("Площадь стен (после вычета окон и дверей): " + finalWallArea + " м².");
                // Накапливаем параметры для всей квартиры
                totalFloorArea += floorArea;
                totalWallArea += finalWallArea;
                totalWindowsArea += roomWindowsArea;
                totalDoorsArea += roomDoorsArea;

            }

// Выводим общие результаты

            writer.println("===== Итоги для всей квартиры =====");
            writer.println("Общая площадь пола: " + totalFloorArea + " м².");
            writer.println("Общая площадь стен: " + totalWallArea + " м².");
            writer.println("Общая площадь окон: " + totalWindowsArea + " м².");
            writer.println("Общая площадь дверей: " + totalDoorsArea + " м².");

// Общая длина откосов окон
            writer.println("Общая длина откосов окон: " + totalWindowsSlopeLength + " м.");
// Общая длина откосов дверей
            writer.println("Общая длина откосов дверей: " + totalDoorsSlopeLength + " м.");



            System.out.println("===== Итоги для всей квартиры =====");
            System.out.println("Общая площадь пола: " + totalFloorArea + " м².");
            System.out.println("Общая площадь стен: " + totalWallArea + " м².");
            System.out.println("Общая площадь окон: " + totalWindowsArea + " м².");
            System.out.println("Общая площадь дверей: " + totalDoorsArea + " м².");
            System.out.println("Общая длина откосов окон: " + totalWindowsSlopeLength + " м.");
            System.out.println("Общая длина откосов дверей: " + totalDoorsSlopeLength+ " м.");

        }catch (IOException e) {
            System.out.println("ошибка при записи в файл: "
            + e.getMessage()
        );
        }
        sc.close();
    }
        // Функция для расчета площади пола
        public static double calcFloorArea ( double length, double width){
            return length * width;
        }

        // Функция для расчета площади стен
        public static double calcWallArea ( double length, double width, double height){
            return 2 * (length * height) + 2 * (width * height);
        }

        // Функция для расчета площади прямоугольника
        public static double calcRectangleArea ( double width, double height){
            return width * height;
        }

        // Функция для расчета периметра комнаты
        public static double calcRoomPerimeter ( double length, double width){
            return 2 * (length + width);
        }
    }

