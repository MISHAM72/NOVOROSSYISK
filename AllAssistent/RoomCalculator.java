package AllAssistent;

import java.util.Scanner;

public class RoomCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // Считываем размеры комнаты
        System.out.println("введите длину комнаты (в метрах):");
        double length = sc.nextDouble();
        System.out.println("ввевите ширину комнаты (в метрах):");
        double width = sc.nextDouble();
        System.out.println("введите высоту комнаты (в метрах):");
        double height = sc.nextDouble();

        //считаем площадь пола
        double floorArea = calcFloorArea(length,width);
        System.out.println("площадь пола: " + floorArea + "м.кв.");

        // Считаем площадь стен
        double wallArea = calcWallArea(length,width,height);
        System.out.println("площадь стен до вычета окон и дверей:" + wallArea + "м.кв.");
        // Считаем периметр комнаты
        double roomPerimeter = calcRoomPerimetr(length,width);
        System.out.println("периметр комнаты:" + roomPerimeter + "м.кв.");


        // Считываем количество окон и их размеры
            System.out.println("введите кол-во окон:");
        int windowsCount = sc.nextInt();
        double totalWindowsArea = 0;
        double totalWindowSlapeLength = 0;

        for (int i = 1; i <=windowsCount; i++){
            System.out.println("введите ширину окна: " + i + "м." );
            double windowsWidth = sc.nextDouble();
            System.out.println("введите высоту окна: " + i + "м.");
            double windowsHeight = sc.nextDouble();
            // Считаем площадь текущего окна и добавляем её в общий результат
            totalWindowsArea += calcRectangleArea(windowsWidth,windowsHeight);
            // Считаем длину откосов для текущего окна и добавляем её в общий результат
            totalWindowSlapeLength += 2 * (windowsWidth + windowsHeight);
        }
        System.out.println("общая площадь окон: " + totalWindowsArea + "м.кв.");
           // выводим общую длину откосов окон
        System.out.println("общая длина откосов окон: " + totalWindowSlapeLength + " м");
//------------------------------------------------------------------------------------

        System.out.println("введите кол-во дверей:");
        int doorsCount = sc.nextInt();
        double totalDoorsArea = 0;
        double totalDoorsSlapeLength = 0;
        for (int i = 1; i <= doorsCount; i++){
            System.out.println("введите ширину двери: " + i + "м." );
            double doorsWidth = sc.nextDouble();
            System.out.println("введите высоту двери: " + i + "м.");
            double doorsHeight = sc.nextDouble();
            // Считаем площадь текущей двери и добавляем её в общий результат
            totalDoorsArea += calcRectangleArea(doorsWidth,doorsHeight);
            // Считаем длину откосов для текущей двери и добавляем её в общий результат
            totalDoorsSlapeLength += 2 * (doorsWidth + doorsHeight);
        }

        System.out.println("общая площадь дверей: " + totalDoorsArea + "  м.кв.");
        // выводим общую длину откосов окон
        System.out.println("общая длина откосов окон: " + totalDoorsSlapeLength + "  м");

//-------------------------------------------------------

        // Обновляем площадь стен, вычитая окна и двери
       double finalWallArea = wallArea - totalWindowsArea - totalDoorsArea;
        System.out.println("площадь стен после вычета окон и двери, равна:" + finalWallArea + "м.кв.");



        sc.close();
    }

    // Функция для расчета площади пола
public static double calcFloorArea(double length, double width) {
        return length*width;
}
public static double calcWallArea(double length, double width, double height){
        return 2*(length*height) + 2*(width*height);
}

    // Функция для расчета площади (прямоугольника — окна или двери)
public static double calcRectangleArea(double width, double height) {
        return  width*height;
}
    // Функция для расчета периметра комнаты
public static double calcRoomPerimetr(double length, double width) {
        return 2*(length+width);
}



}

/**  ## AI Assistant
 ### **1. Определитесь с задачей**
 Ваша программа должна:
 1. Считать площадь стен, пола, комнаты.
 2. Подсчитывать их периметр.
 3. Учитывать количество окон и дверей.
 4. Рассчитывать длину откосов окон.

 Программа должна быть интерактивной, чтобы пользователь мог вводить:
 - Длину, ширину и высоту помещения.
 - Количество окон, их размеры, а также размер дверей.

 **Выходные данные**: площадь, периметр и другие параметры, как результат, который можно распечатать.
 ### **2. Структурируйте программу**
 ### Деление программы на модули:
 Каждую задачу можно разложить на отдельные функции. Например:
 1. **Расчёт площади пола и стен**:
 - Формула для пола: длина × ширина.
 - Формула стен: 2 × (длина × высота) + 2 × (ширина × высота).
 - Вычесть площади окон и дверей из общей площади стен.

 2. **Периметр комнаты**:
 - Формула: 2 × (длина + ширина).

 3. **Окна и двери**:
 - Рассчитать общую площадь окон и дверей.
 - Длина откосов окна рассчитывается как периметр окна, умноженный на глубину (толщину стены).

 4. **Ввод данных**:
 - Создайте удобные механизмы для ввода данных пользователем:
 - Размеры комнаты.
 - Количество окон и их размеры.
 - Размер двери.

 ### **3. Как написать программу на Java**
 Вот пример, как можно начать:
 #### Основной структуры программы:
 ``` java
 import java.util.Scanner;

 public class RoomCalculator {

 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);

 // Считываем размеры комнаты
 System.out.println("Введите длину комнаты (в метрах): ");
 double length = scanner.nextDouble();

 System.out.println("Введите ширину комнаты (в метрах): ");
 double width = scanner.nextDouble();

 System.out.println("Введите высоту комнаты (в метрах): ");
 double height = scanner.nextDouble();

 // Считаем площадь пола
 double floorArea = calculateFloorArea(length, width);
 System.out.println("Площадь пола: " + floorArea + " м²");

 // Считаем площадь стен
 double wallArea = calculateWallArea(length, width, height);
 System.out.println("Площадь стен до вычета окон и дверей: " + wallArea + " м²");

 // Считаем периметр комнаты
 double roomPerimeter = calculateRoomPerimeter(length, width);
 System.out.println("Периметр комнаты: " + roomPerimeter + " м");

 // Считываем количество окон и их размеры
 System.out.println("Введите количество окон: ");
 int windowsCount = scanner.nextInt();
 double totalWindowArea = 0;
 for (int i = 1; i <= windowsCount; i++) {
 System.out.println("Введите ширину окна " + i + " (в метрах): ");
 double windowWidth = scanner.nextDouble();

 System.out.println("Введите высоту окна " + i + " (в метрах): ");
 double windowHeight = scanner.nextDouble();

 totalWindowArea += calculateRectangleArea(windowWidth, windowHeight);
 }
 System.out.println("Общая площадь окон: " + totalWindowArea + " м²");

 // Считаем площадь двери
 System.out.println("Введите ширину двери (в метрах): ");
 double doorWidth = scanner.nextDouble();

 System.out.println("Введите высоту двери (в метрах): ");
 double doorHeight = scanner.nextDouble();

 double doorArea = calculateRectangleArea(doorWidth, doorHeight);
 System.out.println("Площадь двери: " + doorArea + " м²");

 // Обновляем площадь стен, вычитая окна и дверь
 double finalWallArea = wallArea - totalWindowArea - doorArea;
 System.out.println("Площадь стен для отделки (после вычета окон и двери): " + finalWallArea + " м²");

 scanner.close();
 }

 // Функция для расчета площади пола
 public static double calculateFloorArea(double length, double width) {
 return length * width;
 }

 // Функция для расчета площади стен
 public static double calculateWallArea(double length, double width, double height) {
 return 2 * (length * height) + 2 * (width * height);
 }

 // Функция для расчета площади (прямоугольника — окна или двери)
 public static double calculateRectangleArea(double width, double height) {
 return width * height;
 }

 // Функция для расчета периметра комнаты
 public static double calculateRoomPerimeter(double length, double width) {
 return 2 * (length + width);
 }
 }
 ```
 ### **4. Результаты программы**
 Пользователь вводит данные:
 - Длину, ширину, высоту комнаты.
 - Количество окон и размеры каждого окна.
 - Размер двери.

 Программа выводит:
 1. Площадь пола.
 2. Площадь стен.
 3. Периметр комнаты.
 4. Общую площадь окон.
 5. Площадь двери.
 6. Итоговую площадь стен, готовую для отделки, после вычета окон и двери.

 ### **5. Усложнение и улучшения**
 После завершения базовой работы вы можете добавить:
 1. **Вычисление длины откосов**: Для окон:
 ``` java
 double windowSlopeLength = windowsCount * (2 * (windowWidth + windowHeight));
 System.out.println("Общая длина откосов окон: " + windowSlopeLength + " м");
 ```
 1. **Обработка нескольких комнат**:
 - Создайте цикл, чтобы пользователь мог вводить данные для нескольких комнат.

 2. **Интерфейс**:
 - Предусмотрите возможность простой навигации для пользователя:
 - Меню для выбора новых операций.
 - Выход из программы.

 3. **Сохранение данных**:
 - Добавьте возможность сохранять результаты в файл.

 ### **6. Итог**
 Вы уже знаете, с каких данных начать. Все остальное — это разбиение на логические шаги и добавление функций для удобства пользователя. Если возникнут трудности на любом этапе, обязательно спрашивайте! Удачи вам в создании вашей полезной программы! 🚀


 ## Me
 благодарю, очень хорошо вы объясняете , спасибо

 ## AI Assistant
 Спасибо вам за такие добрые слова! 😊 Мне действительно приятно помогать и видеть, что мои объяснения полезны. Если у вас появятся ещё вопросы, новые идеи или потребуется доработка вашей программы — обязательно обращайтесь. Удачи в вашем проекте, и помните, вы на правильном пути! 🚀

 Всегда рад помочь! 😉
 */