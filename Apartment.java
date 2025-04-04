import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Apartment {

  private static final double ELECTRICITY_FACTOR = 3.3; // Константа для расчетов кабеля
  private static final double WATER_FACTOR = 1.3; // Константа для водопровода


  public static void main (String[] args) throws IOException {
	Scanner sc = new Scanner (System.in);
	System.out.println ("Введите название вашей квартиры (например: Квартира №.....):");
	String apartmentName = sc.nextLine ();


	// Создаем файл с результатами с уникальным именем
	String fileName = "result_" + LocalDateTime.now ().format (DateTimeFormatter.ofPattern ("yyyyMMdd_HHmmss")) + ".txt";
	// Инициализация переменных для подсчета итогов
	double totalApartmentArea = 0;
	double totalWallArea = 0;
	double totalFloorArea = 0;
	double totalApartmentPerimeter = 0;
	double totalWindowsSlopeLength = 0;
	double totalDoorsSlopeLength = 0;
	double totalWindowsArea = 0;
	double totalDoorsArea = 0;
	int totalNumberOfSockets = 0;
	double totalCableLength = 0;
	double totalWaterSockets = 0;
	double totalWaterPipeLength = 0;

	//БЛОК 1 ============================================================================================
	try (PrintWriter writer = new PrintWriter (new FileWriter ("result.txt", false))) {
	  //-------------------------------------------------------------------
	  writer.println ();
	  writer.println ("===== Новый запуск программы =====");
	  writer.println (); // ещё одна пустая строка
	  //и временную метку
	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss");
	  writer.println ("Дата и время: " + LocalDateTime.now ().format (dtf));

	  System.out.println ("ВВЕДИТЕ КОЛИЧЕСТВО КОМНАТ В КВАТИРЕ: ");
	  int roomCount = safeInputInt (sc);

	  writer.println ("кол-во комнат в квартире - " + roomCount);
	  System.out.println ("Количество комнат в квартире: " + roomCount);

	  //БЛОК 2 Общие переменные для квартиры================================================================


	  //-----БЛОК 2  -------------------------------------------------------------------------------------------------
	  for (int i = 1; i <= roomCount; i++) {
		System.out.println ("КОМНАТА СТАНДАРТНАЯ (прямоугольная) или нет введите (ДА) или (НЕТ):");
		String isStandard = sc.nextLine ();

		System.out.println ("Введите название комнаты (например: Спальня, Кухня, Ванна, Туалет):");
		String roomName = sc.nextLine ();


		double wallArea, floorArea, roomPerimeter;
		double length = 0;
		double width = 0;
		double height;
		if (isStandard.equalsIgnoreCase ("да")) {
		  // Прямоугольная комната
		  System.out.println ("Введите длину комнаты (м):");
		  length = safeInputDouble (sc);
		  System.out.println ("Введите ширину комнаты (м):");
		  width = safeInputDouble (sc);
		  System.out.println ("Введите высоту комнаты (м):");
		  height = safeInputDouble (sc);
		  floorArea = calcFloorArea (length, width);
		  wallArea = calcWallArea (length, width, height);
		  roomPerimeter = calcRoomPerimeter (length, width);

		} else {
		  // Нестандартная комната
		  floorArea = calcNonStandardFloorArea (sc);
		  System.out.println ("Введите высоту стен (м):");
		  height = safeInputDouble (sc);
		  System.out.println ("Введите суммарный периметр нестандартного помещения:");
		  roomPerimeter = safeInputDouble (sc);
		  wallArea = roomPerimeter * height;
		}

		System.out.println ("Введите количество розеток (шт.):");
		int roomNumberSockets = safeInputInt (sc);
		System.out.println ("Введите расстояние от щитка до комнаты (м):");
		double distanceBetweenRooms = safeInputDouble (sc);
		double cableLengthInsideRoom = calculateCableLengthInsideRoom (roomPerimeter, roomNumberSockets, distanceBetweenRooms);


		int roomWaterSockets = 0;
		double roomWaterPipeLength = 0;
		if (roomName.equals ("Кухня") || roomName.equals ("Ванна") || roomName.equals ("Туалет")) {
		  System.out.println ("ведите кол-во водорозеток:");
		  roomWaterSockets = safeInputInt (sc);
		  roomWaterPipeLength = calcWaterPipeLength (roomPerimeter, roomWaterSockets);
		}
		//----------------------------------------------------------------------------------------------------------
		// БЛОК 5 Обработка окон
		System.out.println ("Введите количество окон:");
		int windowsCount = safeInputInt (sc);

		//int windowsCount = sc.nextInt ();
		sc.nextLine (); // Очищает буфер перед следующим `nextLine`

		double roomWindowsArea = 0;
		double roomWindowsSlopeLength = 0;
		double openingWidth;
		double openingHeight;

		for (int j = 1; j <= windowsCount; j++) {
		  System.out.println ("Введите ширину окна " + j + " (в метрах):");
		  openingWidth = safeInputDouble (sc);
		  // openingWidth = sc.nextDouble ();
		  // sc.nextLine(); // Очищает буфер перед следующим `nextLine`
		  System.out.println ("Введите высоту окна " + j + " (в метрах):");
		  openingHeight = safeInputDouble (sc);
		  // openingHeight = sc.nextDouble ();
		  //  sc.nextLine(); // Очищает буфер перед следующим `nextLine`
		  roomWindowsArea += calcOpeningArea (openingWidth, openingHeight);
		  roomWindowsSlopeLength += calcOpeningPerimeter (openingWidth, openingHeight);
		}
		//----------------------------------------------------------------------------
		// БЛОК 6 Обработка дверей
		System.out.println ("Введите количество дверей:");
		int doorsCount = safeInputInt (sc);
		//int doorsCount = sc.nextInt ();
		//sc.nextLine(); // Очищает буфер перед следующим `nextLine`

		double roomDoorsArea = 0;
		double roomDoorsSlopeLength = 0;

		for (int c = 1; c <= doorsCount; c++) {
		  System.out.println ("Введите ширину двери " + c + " (в метрах):");
		  openingWidth = safeInputDouble (sc);
		  // openingWidth = sc.nextDouble ();
		  //  sc.nextLine(); // Очищает буфер перед следующим `nextLine`
		  System.out.println ("Введите высоту двери " + c + " (в метрах):");
		  openingHeight = safeInputDouble (sc);
		  //openingHeight = sc.nextDouble ();
		  // sc.nextLine(); // Очищает буфер перед следующим `nextLine`
		  roomDoorsArea += calcOpeningArea (openingWidth, openingHeight);
		  roomDoorsSlopeLength += calcOpeningPerimeter (openingWidth, openingHeight);
		}
		double finalWallArea = wallArea - roomWindowsArea - roomDoorsArea;

		// Итоги по комнате
		printRoomDetails (writer, roomName, floorArea, wallArea, roomPerimeter,
			finalWallArea, roomWindowsArea, roomWindowsSlopeLength,
			roomDoorsArea, roomDoorsSlopeLength, roomNumberSockets, cableLengthInsideRoom, roomWaterPipeLength, roomWaterSockets);

		// Суммируем общие данные
		totalApartmentArea += floorArea;
		totalFloorArea += floorArea;
		totalWallArea += finalWallArea;
		totalApartmentPerimeter += roomPerimeter;
		totalWindowsArea += roomWindowsArea;
		totalWindowsSlopeLength += roomWindowsSlopeLength;
		totalDoorsArea += roomDoorsArea;
		totalDoorsSlopeLength += roomDoorsSlopeLength;
		totalNumberOfSockets += roomNumberSockets;
		totalCableLength += cableLengthInsideRoom;
		totalWaterSockets += roomWaterSockets;
		totalWaterPipeLength += roomWaterPipeLength;
	  }
	  //--------------------------------------------------------------------------------------------------

	// Итоговый отчет
	writeApartmentSummary (writer, apartmentName, totalApartmentArea, totalFloorArea, totalWallArea,
		totalApartmentPerimeter, totalWindowsArea, totalWindowsSlopeLength,
		totalDoorsArea, totalDoorsSlopeLength, totalNumberOfSockets, totalCableLength, totalWaterSockets, totalWaterPipeLength);


  } catch(
  IOException e)

  {
	System.out.println ("ошибка при записи в файл" + e.getMessage ());
  }

	sc.close ();
  }

// Безопасный ввод целого числа
private static int safeInputInt(Scanner sc) {
  while (true) {
	try {
	  return sc.nextInt();
	} catch (InputMismatchException e) {
	  System.out.println("Ошибка ввода. Введите целое число.");
	  sc.nextLine();
	}
  }
}
// Безопасный ввод числа с плавающей точкой
private static double safeInputDouble(Scanner sc) {
  while (true) {
	try {
	  return sc.nextDouble();
	} catch (InputMismatchException e) {
	  System.out.println("Ошибка ввода. Введите правильное число.");
	  sc.nextLine();
	}
  }
}


  public static double calcFloorArea (double length, double width) {
	return length * width;
  }

  public static double calcWallArea (double length, double width, double height) {
	return 2 * (length * height) + 2 * (width * height);
  }

  public static double calcRoomPerimeter (double length, double width) {
	return 2 * (length + width);
  }

  public static double calculateCableLengthInsideRoom (double roomPerimeter, int sockets, double distanceBetweenRooms) {
	return (sockets * (roomPerimeter / 3.3) + distanceBetweenRooms);
  }
  public static double calcWaterPipeLength (double roomPerimeter, double sockets) {
	return roomPerimeter / 1.3 * sockets ;
  }

  public static double calcOpeningPerimeter (double openingWidth, double openingHeight) {
	return 2 * (openingWidth + openingHeight);
  }

  public static double calcOpeningArea (double openingWidth, double openingHeight) {
	return openingWidth * openingHeight;
  }

  public static double calcCircleArea (double radius) {
	return Math.PI * radius * radius;
  }

  // Метод для расчета площади треугольника
  public static double calcTriangleArea (double base, double height) {
	return (base * height) / 2;
  }

  public static double calcNonStandardFloorArea (Scanner sc) {
	double totalArea = 0;
	System.out.println ("Введите количество зон:");
	int zoneCount = sc.nextInt ();
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
		  totalArea += calcFloorArea (rectLength, rectWidth); // Используем существующий метод
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
		  System.out.println ("Неизвестный тип зоны. " +
								  "Попробуйте снова.");
		  //i--;// Заглушка, чтобы не засчитывать ввод как
		  // "целую зону"
	  }
	}
	System.out.println ("Общая площадь нестандартного помещения: " + totalArea + " м².");
	return totalArea;
  }
}
private static void printRoomDetails(PrintWriter writer, String roomName, double floorArea, double wallArea,
									 double roomPerimeter, double finalWallArea, double roomWindowsArea,
									 double roomWindowsSlopeLength, double roomDoorsArea, double roomDoorsSlopeLength,
									 int roomNumberSockets, double cableLengthInsideRoom, double roomWaterPipeLength, double roomWaterSockets
) {
  writer.println("===== " + roomName + " =====");
  writer.println("Площадь пола: " + floorArea + " м²");
  writer.println("Площадь стен до вычета окон и дверей: " + wallArea + " м²");
  writer.println("Площадь стен после вычета окон и дверей: " + finalWallArea + " м²");
  writer.println("Периметр комнаты: " + roomPerimeter + " м");
  writer.println("Площадь окон: " + roomWindowsArea + " м²");
  writer.println("Длинна откосов окон: " + roomWindowsSlopeLength + " м");
  writer.println("Площадь дверей: " + roomDoorsArea + " м²");
  writer.println("Длина откосов дверей: " + roomDoorsSlopeLength + " м");
  writer.println("Кол-во розеток: " + roomNumberSockets);
  writer.println("Длина кабеля: " + cableLengthInsideRoom + " м");
	writer.println("Кол-во водорозеток: " + roomWaterSockets);
	writer.println("Длина труб: " + roomWaterPipeLength + " м");
  writer.println();
}

// Итоговый отчет по квартире
private static void writeApartmentSummary(PrintWriter writer, String apartmentName, double totalApartmentArea,
										  double totalFloorArea, double totalWallArea, double totalApartmentPerimeter,
										  double totalWindowsArea, double totalWindowsSlopeLength,
										  double totalDoorsArea, double totalDoorsSlopeLength, int totalNumberOfSockets,
										  double totalCableLength, double totalWaterSockets, double totalWaterPipeLength) {
  writer.println("===== Итог по всей квартире: \"" + apartmentName + "\" =====");
  writer.println("Общая площадь квартиры: " + totalApartmentArea + " м²");
  writer.println("Общая площадь пола: " + totalFloorArea + " м²");
  writer.println("Общая площадь стен: " + totalWallArea + " м²");
  writer.println("Общий периметр квартиры: " + totalApartmentPerimeter + " м");
  writer.println("Общая площадь окон: " + totalWindowsArea + " м²");
  writer.println("Общая длина откосов окон: " + totalWindowsSlopeLength + " м");
  writer.println("Общая площадь дверей: " + totalDoorsArea + " м²");
  writer.println("Общая длина откосов дверей: " + totalDoorsSlopeLength + " м");
  writer.println("Общее количество розеток: " + totalNumberOfSockets);
  writer.println("Общая длина кабеля для всех комнат: " + totalCableLength + " м");
  writer.println("Общее количество водорозеток: " + totalWaterSockets);
  writer.println("Общая длина труб: " + totalWaterPipeLength + " м");
  writer.println();
}




