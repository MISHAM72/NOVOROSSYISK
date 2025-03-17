package AllAssistent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class ApartmentCalculator {

  public static void main (String[] args) {

	Locale.setDefault (Locale.US); //Если вся программа работает только в одной локали, можно изменить локаль для всех операций с числами:
//Однако этот способ менее предпочтителен, так как может повлиять на работу других частей кода
	Scanner sc = new Scanner (System.in);
	// sc.useLocale(Locale.US); //`Locale.US` для точки как разделителя:

	//создаем фйл для записи вывода в файл result.txt
	try (PrintWriter writer = new PrintWriter (new PrintWriter ("result.txt"))) {
/** Как это работает:
 1. Код в блоке **`try`** выполняется.
 2. Если в блоке **`try`** возникает исключение типа `IOException`, выполнение переходит в блок **`catch`**.
 3. Внутри блока **`catch`** можно обработать возникшую ошибку (например, вывести сообщение, записать ошибку в лог или предпринять попытку восстановления).
 */

/**- Сначала пользователь вводит количество комнат*/
	  System.out.println ("Введите количество комнат в квартире:");
	  /** - Считывает количество комнат (`roomCount`)
	   с консоли с помощью объекта `Scanner` (`sc`).*/
	  int roomCount = sc.nextInt ();

	  /** - Записывает введенное количество комнат в файл
	   `result.txt` через объект `PrintWriter` (`writer`).*/
	  writer.println ("количество комнат в квартире: " + roomCount);

	  /**- Инициализируются переменные для накопления данных
	   для всей квартиры:*/

	  double totalFloorArea = 0; //-общая площадь пола для всех комнат.
	  double totalWallArea = 0;
	  double totalWindowsArea = 0;
	  double totalDoorsArea = 0;
	  double totalWindowsSlopeLength = 0;
	  double totalDoorsSlopeLength = 0; //-общая длина откосов дверей.

	  /** Описание фрагмента можно подытожить так:
	      - Сначала программа запрашивает количество комнат у пользователя.
	   - Определяются переменные, которые в дальнейшем будут накапливать параметры всей квартиры:
	   - Общую площадь пола.
	   - Общую площадь стен (с учетом окон и дверей).
	   - Общую площадь окон и дверей.
	   - Общую длину откосов окон и дверей.
	     Этот фрагмент закладывает основу для дальнейших расчетов.
           - Далее программа начинает итерацию по всем комнатам,
         используя цикл `for`, и собирает данные о каждой комнате:
	   - Размеры комнаты (длина, ширина, высота).
       - Количество окон и их размеры.
	   - Количество дверей и их размеры.
       - Расчет площади пола, площади стен, площади окон, площади дверей, длины откосов и т.д.
	  // Цикл по всем комнатам, строка вызова метода calaFloorArea находится в этом цикле For
       - Перед вызовом пользователь вводит длину и ширину комнаты:
       - Затем вызывается `calcFloorArea` для вычисления площади пола
        стандартной комнаты (прямоугольной):*/
	  for (int i = 1; i <= roomCount; i++) {
		// Выбор типа комнаты
    System.out.println("Комната стандартная (прямоугольная)? Введите 'да' или 'нет':");
       String isStandard = sc.next();
		double floorArea = 0.0; // Площадь пола
		double wallArea = 0.0;  // Площадь стен
		double roomPerimeter = 0.0;
		if (isStandard.equalsIgnoreCase ("да")) {
		  // Обработка стандартной комнаты
		  System.out.println ("Введите длину комнаты (в метрах):");
		  double length = sc.nextDouble ();
		  System.out.println ("Введите ширину комнаты (в метрах):");
		  double width = sc.nextDouble ();
		  System.out.println ("Введите высоту комнаты (в метрах):");
		  double height = sc.nextDouble ();

/** Расчеты для стандартной комнаты. строка
 *  floorArea = calcFloorArea (length, width); вызывается только в случае,
 *  если комната является стандартной (прямоугольной).*/
		  floorArea = calcFloorArea (length, width);
		   wallArea = calcWallArea (length, width, height);
		   roomPerimeter = calcRoomPerimeter (length, width);
		} else {
		  // Обработка нестандартной комнаты
		  System.out.println ("Комната состоит из нескольких зон. Вводим данные:");
		  /** это строка ызова метода для не стандартной комнаты*/
		   floorArea = calcNonStandardFloorArea (sc);

		  // Для нестандартных помещений придется уточнять высоту стен и периметр вручную
		  System.out.println ("Введите высоту стен комнаты (в метрах):");
		  double height = sc.nextDouble ();
		  System.out.println ("Введите суммарный периметр стен комнаты (в метрах):");
		   roomPerimeter = sc.nextDouble ();
		  // Рассчитаем общую площадь стен на основе периметра и высоты
		  wallArea = roomPerimeter * height;

// Печатаем результаты
		  writer.println ("===== Комната №" + i + " =====");
		  writer.println ("Площадь пола: " + floorArea + " м².");
		  writer.println ("Периметр комнаты: " + roomPerimeter + " м.");
		  writer.println ("Площадь стен до вычета окон и дверей: " + wallArea + " м².");

		  System.out.println ("===== Комната №" + i + " =====");
		  System.out.println ("Площадь пола: " + floorArea + " м².");
		  System.out.println ("Периметр комнаты: " + roomPerimeter + " м.");
		  System.out.println ("Площадь стен до вычета окон и дверей: " + wallArea + " м².");
		}
/**   - Для каждой комнаты результаты выводятся на экран и записываются в файл.*/

		// вводим количество окон и их размеры
		System.out.println ("Введите количество окон:");
		//     Пользователь вводит количество окон в комнате (`windowsCount`)
		// используя объект `Scanner` (`sc`).
		int windowsCount = sc.nextInt ();
		// Эта строка записывает количество окон в файл через объект `writer`.
		writer.println ("кол-во окон: " + windowsCount);
		//Переменные `roomWindowsArea` использованы для хранения общей площади
		// всех окон в комнате, а `roomWindowsSlopeLength`
		// — для хранения суммарной длины откосов окон.
		double roomWindowsArea = 0;
		double roomWindowsSlopeLength = 0;
		/** Этот цикл:
		 - По очереди запрашивает ширину и высоту каждого окна.
		 - Рассчитывает площадь каждого окна с использованием метода `calcRectangleArea` (предположительно вычисляет площадь прямоугольника как `ширина * высота`) и добавляет результат в `roomWindowsArea`.
		 - Рассчитывает периметр рамки каждого окна (или длину откосов для каждого окна), как `2 * (ширина + высота)` и добавляет его в `roomWindowsSlopeLength`.
		 */
		for (int j = 1; j <= windowsCount; j++) {
		  System.out.println ("Введите ширину окна " + j + " (в метрах):");
		  double windowWidth = sc.nextDouble ();
		  System.out.println ("Введите высоту окна " + j + " (в метрах):");
		  double windowHeight = sc.nextDouble ();
		  roomWindowsArea += calcRectangleArea (windowWidth, windowHeight);
		  roomWindowsSlopeLength += 2 * (windowWidth + windowHeight);

		  /**### Пояснение ключевых частей:
		   - **`calcRectangleArea(windowWidth, windowHeight)` **: Метод должен быть определён где-то в коде. Он, скорее всего, возвращает площадь окна как `width * height`.
		   - **`totalWindowsSlopeLength` **: Это глобальная переменная, использованная для накопления суммарной длины откосов окон с учётом предыдущих помещений или частей.
		   */}
/**  1. **Обновление общей длины откосов**:
 * Переменная `totalWindowsSlopeLength` (вероятно глобальная)
 * увеличивается на длину откосов окон в текущей комнате.*/
		totalWindowsSlopeLength += roomWindowsSlopeLength;

/**1. **Вывод результатов в файл и на консоль**:Подсчитанные
 *  значения площади суммарных окон и общей длины откосов окон выводятся:
 - В файл через объект `writer`.
 - В консоль через `System.out`.*/
		writer.println ("Площадь окон: " + roomWindowsArea + "м.кв");
		System.out.println ("Площадь окон: " + roomWindowsArea + " м².");
		writer.println ("длина откосов окон: " + totalWindowsSlopeLength + "м.");
		System.out.println ("длина откосов окон: " + totalWindowsSlopeLength + " м.");
// Считываем количество дверей и их размеры
		System.out.println ("Введите количество дверей:");
		int doorsCount = sc.nextInt ();
		writer.println ("кол-во дверей: " + doorsCount);

		double roomDoorsArea = 0;
		double roomDoorsSlopeLength = 0;
		for (int j = 1; j <= doorsCount; j++) {
		  System.out.println ("Введите ширину двери " + j + " (в метрах):");
		  double doorWidth = sc.nextDouble ();
		  System.out.println ("Введите высоту двери " + j + " (в метрах):");
		  double doorHeight = sc.nextDouble ();
		  roomDoorsArea += calcRectangleArea (doorWidth, doorHeight);
		  roomDoorsSlopeLength += 2 * (doorWidth + doorHeight);
		}
		totalDoorsSlopeLength += roomDoorsSlopeLength;
		writer.println ("Площадь дверей: " + roomDoorsArea + " м².");
		System.out.println ("Площадь дверей: " + roomDoorsArea + " м².");
		writer.println ("общая длина откосов дверей: " + roomDoorsSlopeLength + " м.");
		System.out.println ("общая длина откосов дверей: " + roomDoorsSlopeLength + "  м.");
// Вычитаем площадь окон и дверей из площади стен
		double finalWallArea = wallArea - roomWindowsArea - roomDoorsArea;

		writer.println ("Площадь стен (после вычета окон и дверей): " + finalWallArea + " м².");
		System.out.println ("Площадь стен (после вычета окон и дверей): " + finalWallArea + " м².");
		// Накапливаем параметры для всей квартиры
/** 1. Здесь к переменной `totalFloorArea`(суммарная
 * площадь пола для всей квартиры) добавляется значение `floorArea` (площадь пола
 *  текущего помещения). Символ `+=` означает
 *  "добавить к текущему значению и присвоить результат".*/
		totalFloorArea += floorArea;
		totalWallArea += finalWallArea;
		totalWindowsArea += roomWindowsArea;
		totalDoorsArea += roomDoorsArea;
	  }
/** Выводим общие результаты. 1.- Общие показатели для всей квартиры
 * (суммы рассчитанных параметров) также выводятся на экран  и записываются
 * в файл*/

	  writer.println ("===== Итоги для всей квартиры =====");
	  writer.println ("Общая площадь пола: " + totalFloorArea + " м².");
	  writer.println ("Общая площадь стен: " + totalWallArea + " м².");
	  writer.println ("Общая площадь окон: " + totalWindowsArea + " м².");
	  writer.println ("Общая площадь дверей: " + totalDoorsArea + " м².");
	  writer.println ("Общая длина откосов окон: " + totalWindowsSlopeLength + " м.");
	  writer.println ("Общая длина откосов дверей: " + totalDoorsSlopeLength + " м.");

	  System.out.println ("===== Итоги для всей квартиры =====");
	  System.out.println ("Общая площадь пола: " + totalFloorArea + " м².");
	  System.out.println ("Общая площадь стен: " + totalWallArea + " м².");
	  System.out.println ("Общая площадь окон: " + totalWindowsArea + " м².");
	  System.out.println ("Общая площадь дверей: " + totalDoorsArea + " м².");
	  System.out.println ("Общая длина откосов окон: " + totalWindowsSlopeLength + " м.");
	  System.out.println ("Общая длина откосов дверей: " + totalDoorsSlopeLength + " м.");
/** В данном коде `catch (IOException e)` — это часть конструкции **try-catch** в Java, которая используется для обработки исключений.
 ### Объяснение:
 1. **`IOException` **:
 - Это тип исключения в Java (наследуется от класса `Exception`), который возникает при ошибках ввода-вывода (Input/Output).
 - Например, это может быть ошибка чтения или записи данных в файл, ошибка работы с сетевыми соединениями или ошибки, связанные с потоками ввода-вывода.

 2. **`catch (IOException e)` **:
 - Оператор **`catch`** перехватывает исключение, возникающее внутри соответствующего **`try`** блока.
 - В данном случае он перехватывает исключения типа **`IOException` **и всех его подклассов (например, `FileNotFoundException`, `EOFException` и др.).

 3. **`e` **:
 - Это имя переменной, которая содержит экземпляр возникшего исключения.
 - Через объект `e` можно:
 - Получить сообщение об ошибке (вызвав метод `e.getMessage()`).
 - Просмотреть стек вызовов, сгенерировавших исключение (с помощью метода `e.printStackTrace()`).
 `catch (IOException e)` — это способ перехвата и обработки исключений, возникающих при ошибках ввода-вывода.
 */
	} catch (IOException e) {
	  System.out.println ("ошибка при записи в файл: " + e.getMessage ());
	}
	sc.close ();
  }

  // Функция для расчета площади пола
  /** ### **Описание функциональности**
   - **Название метода:** `calcFloorArea`
   - **Параметры метода:**
   - `double length` — длина пола.
   - `double width` — ширина пола.
   - **Возвращаемое значение:** Метод возвращает площадь пола типа `double`.
   ``` java
   return length * width;
   ```
   Эта строка вычисляет площадь, умножая длину (`length`) на ширину (`width`), и возвращает результат.
   */
  public static double calcFloorArea (double length, double width) {
	return length * width;
  }

  // Функция для расчета площади стен
  public static double calcWallArea (double length, double width, double height) {
	return 2 * (length * height) + 2 * (width * height);
  }

  // Функция для расчета площади прямоугольника
  public static double calcRectangleArea (double width, double height) {
	return width * height;
  }

  // Функция для расчета периметра комнаты
  public static double calcRoomPerimeter (double length, double width) {
	return 2 * (length + width);
  }
  // Метод для расчета площади круга
  public static double calcCircleArea(double radius) {
	return Math.PI * radius * radius;
  }
  // Метод для расчета площади треугольника
  public static double calcTriangleArea(double base, double height) {
	return (base * height) / 2;
  }
  public static double calcNonStandardFloorArea(Scanner sc) {
	System.out.println("Введите количество зон (например, прямоугольников, треугольников, кругов), из которых состоит помещение:");
	int zoneCount = sc.nextInt();
	double totalArea = 0; // Переменная для накопления общей площади

	for (int i = 1; i <= zoneCount; i++) {
	  System.out.println("Введите тип зоны " + i + " (1 - прямоугольник, 2 - треугольник, 3 - круг):");
	  int zoneType = sc.nextInt();

	  switch (zoneType) {
		case 1: // Прямоугольник
		  System.out.println("Введите длину прямоугольника:");
		  double rectLength = sc.nextDouble();
		  System.out.println("Введите ширину прямоугольника:");
		  double rectWidth = sc.nextDouble();
		  totalArea += calcRectangleArea(rectLength, rectWidth); // Используем существующий метод
		  break;

		case 2: // Треугольник
		  System.out.println("Введите основание треугольника:");
		  double triangleBase = sc.nextDouble();
		  System.out.println("Введите высоту треугольника:");
		  double triangleHeight = sc.nextDouble();
		  totalArea += calcTriangleArea(triangleBase, triangleHeight); // Новый метод для треугольника
		  break;

		case 3: // Круг
		  System.out.println("Введите радиус круга:");
		  double radius = sc.nextDouble();
		  totalArea += calcCircleArea(radius); // Новый метод для круга
		  break;

		default:
		  System.out.println("Неизвестный тип зоны. Пропускаем.");
		  break;
	  }
	}

	System.out.println("Общая площадь нестандартного помещения: " + totalArea + " м².");
	return totalArea;
  }
}
