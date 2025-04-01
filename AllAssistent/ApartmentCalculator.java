package AllAssistent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class ApartmentCalculator {

  public static void main (String[] args) throws IOException {

	Locale.setDefault (Locale.US); //Если вся программа работает только в одной локали, можно изменить локаль для всех операций с числами:
//Однако этот способ менее предпочтителен, так как может повлиять на работу других частей кода
	Scanner sc = new Scanner (System.in);

	//создаем фйл для записи вывода в файл result.txt
	try (PrintWriter writer = new PrintWriter (new FileWriter ("result.txt"))) {

	  //----------------------------------------------------------------------------------------------------
	  System.out.println ("Введите количество комнат в квартире:");
	  int roomCount = sc.nextInt ();
	  writer.println ("количество комнат в квартире: " + roomCount);

	  double totalFloorArea = 0;
	  double totalWallArea = 0;
	  double totalWindowsArea = 0;
	  double totalDoorsArea = 0;
	  double totalWindowsSlopeLength = 0;
	  double totalDoorsSlopeLength = 0;
	  double totalApartmentPerimeter = 0;
	  int totalNumberOfSockets = 0;
	  double totalCableLength = 0;

	  for (int i = 1; i <= roomCount; i++) {
		System.out.println ("Комната стандартная (прямоугольная)? Введите 'да' или 'нет':");
		String isStandard = sc.next ();
		/* double floorArea;Объявляет переменную с именем floorArea типа double
		для хранения площади пола. Комментарий указывает, что это площадь пола.
		 Важно: он объявлен,
		но не инициализирован. Это означает, что у него пока нет значения по умолчанию.
		Вы должны присвоить значение floorArea перед его использованием. */
		double wallArea;
		double floorArea;
		double roomPerimeter;
		double length = 0;
		double width = 0;
		double height = 0;
		int roomNumberSockets = 0;
		double cableLengthInsideRoom;
		double distanceBetweenRooms;

		//----------------------------------------------------------------------------------------------------
		if (isStandard.equalsIgnoreCase ("да")) {

		  // Обработка стандартной комнаты
		  System.out.println ("Введите длину комнаты (в метрах):");
		  length = sc.nextDouble ();
		  System.out.println ("Введите ширину комнаты (в метрах):");
		  width = sc.nextDouble ();
		  System.out.println ("Введите высоту комнаты (в метрах):");
		  height = sc.nextDouble ();
		  floorArea = calcFloorArea (length, width);
		  wallArea = calcWallArea (length, width, height);
		  roomPerimeter = calcRoomPerimeter (length, width);

		} else {
		  // Обработка нестандартной комнаты
		  System.out.println ("Комната состоит из нескольких зон. Вводим данные:");
		  floorArea = calcNonStandardFloorArea (sc);
		  // Для нестандартных помещений придется уточнять высоту стен и периметр вручную
		  System.out.println ("Введите высоту стен комнаты (в метрах):");
		  height = sc.nextDouble ();
		  System.out.println ("Введите суммарный периметр стен комнаты (в метрах):");
		  roomPerimeter = sc.nextDouble ();
		  wallArea = roomPerimeter * height;
		}
		System.out.println ("введите кол-во розеток:(шт.)");
		roomNumberSockets = sc.nextInt ();
		System.out.println ("введите расстояние от щитка до комнаты: (в метрах");
		distanceBetweenRooms = sc.nextDouble ();
		cableLengthInsideRoom = calculateCableLengthInsideRoom (roomPerimeter, roomNumberSockets,distanceBetweenRooms);

// Печатаем результаты
		writer.println ("===== Комната №" + i + " =====");
		writer.println ("Площадь пола: " + floorArea + " м².");
		writer.println ("Периметр комнаты: " + roomPerimeter + " м.");
		writer.println ("Площадь стен до вычета окон и дверей: " + wallArea + " м².");
		writer.println ("общее кол-во подрозетников: " + totalNumberOfSockets + "шт.");
		writer.println ("Длина кабеля в комнате от  щитка: " + cableLengthInsideRoom + distanceBetweenRooms + " м.");

		System.out.println ("===== Комната №" + i + " =====");
		System.out.println ("Площадь пола: " + floorArea + " м².");
		System.out.println ("Периметр комнаты: " + roomPerimeter + " м.");
		System.out.println ("Площадь стен до вычета окон и дверей: " + wallArea + " м².");
		System.out.println ("общее кол-во подрозетников: " + totalNumberOfSockets + "шт.");
		System.out.println ("Длина кабеля в комнате от щитка: " + cableLengthInsideRoom + distanceBetweenRooms + " м.");
		//------------------------------------------------------------------------------------------------------



		// вводим количество окон и их размеры
		System.out.println ("Введите количество окон:");
		int windowsCount = sc.nextInt ();
		writer.println ("кол-во окон: " + windowsCount);
		double roomWindowsArea = 0;
		double roomWindowsSlopeLength = 0;
		/* Этот цикл:
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

		}
		totalWindowsSlopeLength += roomWindowsSlopeLength;
		writer.println ("Площадь окон: " + roomWindowsArea + "м.кв");
		System.out.println ("Площадь окон: " + roomWindowsArea + " м².");
		writer.println ("длина откосов окон: " + totalWindowsSlopeLength + "м.");
		System.out.println ("длина откосов окон: " + totalWindowsSlopeLength + " м.");
		//----------------------------------------------------------------------------------------------------

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

		writer.println ("Площадь дверей: " + roomDoorsArea + " м².");
		writer.println ("общая длина откосов дверей: " + roomDoorsSlopeLength + " м.");
		System.out.println ("Площадь дверей: " + roomDoorsArea + " м².");
		System.out.println ("общая длина откосов дверей: " + roomDoorsSlopeLength + "  м.");
		//----------------------------------------------------------------------------------------------------

		double finalWallArea = wallArea - roomWindowsArea - roomDoorsArea;
		writer.println ("Площадь стен (после вычета окон и дверей): " + finalWallArea + " м².");
		System.out.println ("Площадь стен (после вычета окон и дверей): " + finalWallArea + " м².");

		totalFloorArea += floorArea;
		totalWallArea += finalWallArea;
		totalApartmentPerimeter += calcRoomPerimeter (length, width);
		totalWindowsArea += roomWindowsArea;
		totalDoorsArea += roomDoorsArea;

		totalDoorsSlopeLength += roomDoorsSlopeLength;
		totalNumberOfSockets += calcNumberSockets (roomNumberSockets);
		totalCableLength += calculateCableLengthInsideRoom (roomPerimeter, roomNumberSockets, distanceBetweenRooms);



		writer.println ("===== Итоги для всей квартиры =====");
		writer.println ("Общая площадь пола: " + totalFloorArea + " м².");
		writer.println ("Общая площадь стен: " + totalWallArea + " м².");
		writer.println ("Общая площадь окон: " + totalWindowsArea + " м².");
		writer.println ("Общая площадь дверей: " + totalDoorsArea + " м².");
		writer.println ("Общая длина откосов окон: " + totalWindowsSlopeLength + " м.");
		writer.println ("Общая длина откосов дверей: " + totalDoorsSlopeLength + " м.");
		writer.println ("Общий периметр квартиры: " + totalApartmentPerimeter + " м."); // Добавлено

		System.out.println ("===== Итоги для всей квартиры =====");
		System.out.println ("Общая площадь пола: " + totalFloorArea + " м².");
		System.out.println ("Общая площадь стен: " + totalWallArea + " м².");
		System.out.println ("Общая площадь окон: " + totalWindowsArea + " м².");
		System.out.println ("Общая площадь дверей: " + totalDoorsArea + " м².");
		System.out.println ("Общая длина откосов окон: " + totalWindowsSlopeLength + " м.");
		System.out.println ("Общая длина откосов дверей: " + totalDoorsSlopeLength + " м.");
		System.out.println ("Общий периметр квартиры: " + totalApartmentPerimeter + " м."); // Добавлено
		//----------------------------------------------------------------------------------------------------
	  }
	} catch (IOException E) {
	  System.out.println ("ошибка при записи в файл: " + E.getMessage ());
	}
	sc.close ();
  }
	//----------------------------------------------------------------------------------------------------
	public static double calcFloorArea ( double length, double width){
	  return length * width;
	}

	// Функция для расчета площади стен
	public static double calcWallArea ( double length, double width, double height){
	  return 2 * (length * height) + 2 * (width * height);
	}
  public static double calculateCableLengthInsideRoom (double roomPerimeter, int sockets, double distanceBetweenRooms) {
	return (sockets * (roomPerimeter / 3.3) + distanceBetweenRooms);
  }

	// Функция для расчета площади прямоугольника
	public static double calcRectangleArea ( double width, double length){
	  return width * length;
	}

	// Функция для расчета периметра комнаты
	public static double calcRoomPerimeter ( double length, double width){
	  return 2 * (length + width);
	}

	// Метод для расчета площади круга
	public static double calcCircleArea ( double radius){
	  return Math.PI * radius * radius;
	}

	// Метод для расчета площади треугольника
	public static double calcTriangleArea ( double base, double height){
	  return (base * height) / 2;
	}

	public static int calcNumberSockets ( int roomNumberSockets){
	  return (roomNumberSockets + roomNumberSockets);
	}

	public static double calcNonStandardFloorArea (Scanner sc){
	  System.out.println ("Введите количество зон (например, прямоугольников, треугольников, кругов), из которых состоит помещение:");
	  int zoneCount = sc.nextInt ();
	  double totalArea = 0; // Переменная для накопления общей площади

	  for (int i = 1; i <= zoneCount; i++) {
		System.out.println ("Введите тип зоны " + i + " (1 - прямоугольник, 2 - треугольник, 3 - круг):");
		int zoneType = sc.nextInt ();

		switch (zoneType) {
		  case 1: // Прямоугольник
			System.out.println ("Введите длину прямоугольника:");
			double rectLength = sc.nextDouble ();
			System.out.println ("Введите ширину прямоугольника:");
			double rectWidth = sc.nextDouble ();
			//Площадь прямоугольника totalArea += calcRectangleArea(rectLength, rectWidth)
			totalArea += calcRectangleArea (rectLength, rectWidth); // Используем существующий метод
			break;

		  case 2: // Треугольник
			System.out.println ("Введите основание треугольника:");
			double triangleBase = sc.nextDouble ();
			System.out.println ("Введите высоту треугольника:");
			double triangleHeight = sc.nextDouble ();
			totalArea += calcTriangleArea (triangleBase, triangleHeight); // Новый метод для треугольника
			break;

		  case 3: // Круг
			System.out.println ("Введите радиус круга:");
			double radius = sc.nextDouble ();
			totalArea += calcCircleArea (radius); // Новый метод для круга
			break;

		  default:
			System.out.println ("Неизвестный тип зоны. Пропускаем.");
			break;
		}
	  }

	  System.out.println ("Общая площадь нестандартного помещения: " + totalArea + " м².");
	  return totalArea;
	}
  }

