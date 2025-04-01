import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Apartment {

  public static void main (String[] args) throws IOException {
	Scanner sc = new Scanner (System.in);

	//БЛОК 1 ============================================================================================
	try (PrintWriter writer = new PrintWriter (new FileWriter ("result.txt", true))) {
	  //-------------------------------------------------------------------
	  writer.println ();
	  writer.println ("===== Новый запуск программы =====");
	  writer.println (); // ещё одна пустая строка
	  //и временную метку
	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss");
	  writer.println ("Дата и время: " + LocalDateTime.now ().format (dtf));

	  System.out.println ("ВВЕДИТЕ КОЛИЧЕСТВО КОМНАТ В КВАТИРЕ: ");
	  int roomCount = sc.nextInt ();
	  writer.println ("кол-во комнат в квартире - " + roomCount);
	  System.out.println ("Количество комнат в квартире: " + roomCount);

	  //БЛОК 2 Общие переменные для квартиры================================================================
	  double totalWallArea = 0;
	  double totalFloorArea = 0;
	  double totalApartmentPerimeter = 0;
	  double totalWindowsSlopeLength = 0;
	  double totalDoorsSlopeLength = 0;
	  double totalWindowsArea = 0;
	  double totalDoorsArea = 0;
	  int totalNumberOfSockets = 0;
	  double totalCableLength = 0;
	  //-----БЛОК 2  -------------------------------------------------------------------------------------------------
	  for (int i = 1; i <= roomCount; i++) {
		System.out.println("Введите название комнаты (например: Спальня, Кухня и т.д.):");
		writer.println("Введите название комнаты (например: Спальня, Кухня и т.д.):");
		String roomName = sc.next();


		System.out.println ("КОМНАТА СТАНДАРТНАЯ угольная) или нет введите (ДА) или (НЕТ):");
		String isStandard = sc.next ();

		double wallArea;
		double floorArea;
		double roomPerimeter;
		double length = 0;
		double width = 0;
		double height;
		int roomNumberSockets;
		double cableLengthInsideRoom;
		double distanceBetweenRooms;

		if (isStandard.equalsIgnoreCase ("да")) {
		  // Прямоугольная комната
		  System.out.println ("введите длину комнаты ( м.)");
		  length = sc.nextDouble ();
		  System.out.println ("введите ширину комнаты ( м.)");
		  width = sc.nextDouble ();
		  System.out.println ("введите высоту комнаты ( м.)");
		  height = sc.nextDouble ();
		  floorArea = calcFloorArea (length, width);
		  wallArea = calcWallArea (length, width, height);
		  roomPerimeter = calcRoomPerimeter (length, width);

		} else {
		  // Нестандартная комната
		  floorArea = calcNonStandardFloorArea (sc);
		  System.out.println ("введите высоту стен ( м.)");
		  height = sc.nextDouble ();
		  System.out.println ("введите суммарный периметр нестандартного помещения.");
		  roomPerimeter = sc.nextDouble ();
		  wallArea = roomPerimeter * height;
		}

		System.out.println ("введите кол-во розеток:(шт.)");
		roomNumberSockets = sc.nextInt ();
		System.out.println ("введите расстояние от щитка до комнаты: (в метрах");
		distanceBetweenRooms = sc.nextDouble ();
		cableLengthInsideRoom = calculateCableLengthInsideRoom (roomPerimeter, roomNumberSockets, distanceBetweenRooms);
		//-------------------------------------------------------------------------------------------------
		writer.println ("===== Комната №" + i + " =====");
		writer.println (" " + roomName);
		writer.println ("Площадь пола: " + floorArea + " м².");
		writer.println ("Периметр комнаты: " + roomPerimeter + " м.");
		writer.println ("Площадь стен до вычета окон и дверей: " + wallArea + " м².");
		writer.println ("общее кол-во подрозетников: " + roomNumberSockets + "шт.");
		writer.println ("Длина кабеля в комнате от  щитка: " + cableLengthInsideRoom + " м.");

		System.out.println ("===== Комната №" + i + " =====");
		System.out.println (" " + roomName);
		System.out.println ("Площадь пола: " + floorArea + " м².");
		System.out.println ("Периметр комнаты: " + roomPerimeter + " м.");
		System.out.println ("Площадь стен до вычета окон и дверей: " + wallArea + " м².");
		System.out.println ("общее кол-во подрозетников: " + roomNumberSockets + "шт.");
		System.out.println ("Длина кабеля в комнате от щитка: " + cableLengthInsideRoom + " м.");

		//----------------------------------------------------------------------------------------------------------
		// БЛОК 5 Обработка окон
		System.out.println ("Введите количество окон:");
		int windowsCount = sc.nextInt ();

		double roomWindowsArea = 0;
		double roomWindowsSlopeLength = 0;
		double openingWidth;
		double openingHeight;

		for (int j = 1; j <= windowsCount; j++) {
		  System.out.println ("Введите ширину окна " + j + " (в метрах):");
		  openingWidth = sc.nextDouble ();
		  System.out.println ("Введите высоту окна " + j + " (в метрах):");
		  openingHeight = sc.nextDouble ();
		  roomWindowsArea += calcOpeningArea (openingWidth, openingHeight);
		  roomWindowsSlopeLength += calcOpeningPerimeter (openingWidth, openingHeight);
		}
		//----------------------------------------------------------------------------
		// БЛОК 6 Обработка дверей
		System.out.println ("Введите количество дверей:");
		int doorsCount = sc.nextInt ();

		double roomDoorsArea = 0;
		double roomDoorsSlopeLength = 0;

		for (int c = 1; c <= doorsCount; c++) {
		  System.out.println ("Введите ширину двери " + c + " (в метрах):");
		  openingWidth = sc.nextDouble ();
		  System.out.println ("Введите высоту двери " + c + " (в метрах):");
		  openingHeight = sc.nextDouble ();
		  roomDoorsArea += calcOpeningArea (openingWidth, openingHeight);
		  roomDoorsSlopeLength += calcOpeningPerimeter (openingWidth, openingHeight);
		}

		double finalWallArea = wallArea - roomWindowsArea - roomDoorsArea;

		//------------------------------------------------------------------------------------
		// БЛОК 7 итоги по одному помещению
		writer.println ();
		writer.println ("===== КОМНАТА №" + i + " =====");
		writer.println ();
		writer.println ("СТЕНЫ и ПОЛЫ");
		writer.println ("Площадь пола: " + floorArea + " м².");
		writer.println ("Площадь стен до вычета окон и дверей: " + wallArea + " м².");
		writer.println ("Площадь стен (после вычета окон и дверей): " + finalWallArea + " м².");
		writer.println ("Периметр комнаты: " + roomPerimeter + " м.");

		writer.println ();
		writer.println ("ОКНА");
		writer.println ("Площадь окон: " + roomWindowsArea + "м.кв");
		writer.println ("длинна откосов окон: " + roomWindowsSlopeLength + " м.");

		writer.println ();
		writer.println ("ДВЕРИ");
		writer.println ("Площадь дверей: " + roomDoorsArea + " м².");
		writer.println ("длинна откосов дверей: " + roomDoorsSlopeLength + " м.");

		writer.println ();
		writer.println ("ЭЛЕКТРИКА");
		writer.println ("кол-во подрозетников (шт.):" + roomNumberSockets);
		writer.println ("Длинна кабеля в комнате от щитка: " + cableLengthInsideRoom + distanceBetweenRooms + " м.");

		System.out.println ();
		System.out.println ("СТЕНЫ и ПОЛЫ");
		System.out.println ("Площадь пола: " + floorArea + " м².");
		System.out.println ("Площадь стен до вычета окон и дверей: " + wallArea + " м².");
		System.out.println ("Площадь стен (после вычета окон и дверей): " + finalWallArea + " м².");
		System.out.println ("Периметр комнаты: " + roomPerimeter + " м.");

		System.out.println ();
		System.out.println ("ОКНА");
		System.out.println ("Площадь окон: " + roomWindowsArea + " м².");
		System.out.println ("длинна откосов окон: " + roomWindowsSlopeLength + " м.");

		System.out.println ();
		System.out.println ("ДВЕРИ");
		System.out.println ("Площадь дверей: " + roomDoorsArea + " м².");
		System.out.println ("длинна откосов дверей: " + roomDoorsSlopeLength + "  м.");

		System.out.println ();
		System.out.println ("ЭЛЕКТРИКА");
		System.out.println ("кол-во подрозетников (шт.):" + roomNumberSockets);
		System.out.println ("Длинна кабеля в комнате от щитка: " + cableLengthInsideRoom + distanceBetweenRooms + " м.");


		//-----------------------------------------------------------
		totalFloorArea += floorArea;
		totalWallArea += finalWallArea;
		totalApartmentPerimeter += calcRoomPerimeter (length, width);
		totalWindowsArea += roomWindowsArea;
		totalDoorsArea += roomDoorsArea;
		totalDoorsSlopeLength += roomDoorsSlopeLength;
		totalNumberOfSockets += roomNumberSockets;
		totalCableLength += calculateCableLengthInsideRoom (roomPerimeter, roomNumberSockets, distanceBetweenRooms);
		totalWindowsSlopeLength += roomWindowsSlopeLength;
		//--------------------------------------------------------------------------------------------------
	  }
	  writer.println ();
	  writer.println ("===== ИТОГИ ПО ВСЕЙ КВАРТИРЕ=====");
	  writer.println ();
	  writer.println ("СТЕНЫ и ПОЛЫ");
	  writer.println ("Общая площадь пола: " + totalFloorArea + " м².");
	  writer.println ("Общая площадь стен: " + totalWallArea + " м².");
	  writer.println ("Общий периметр квартиры: " + totalApartmentPerimeter + " м.");

	  writer.println ();
	  writer.println ("ОКНА");
	  writer.println ("Общая площадь окон для всей квартиры: " + totalWindowsArea + " м.");
	  writer.println ("Общая длина откосов окон для всей квартиры: " + totalWindowsSlopeLength + " м.");

	  writer.println ();
	  writer.println ("ДВЕРИ");
	  writer.println ("Общая площадь дверей для всей квартиры: " + totalDoorsArea + " м.");
	  writer.println ("Общая длина откосов дверей для всей квартиры: " + totalDoorsSlopeLength + " м.");

	  writer.println ();
	  writer.println ("ЭЛЕКТРИКА");
	  writer.println ("Общее кол-во подрозетников (шт.):" + totalNumberOfSockets);
	  writer.println ("Общая длина кабеля для всей квартиры: " + totalCableLength + " м.");


	  //-------------------------------------------------------------------------------------------------
	  System.out.println ("===== Итоги для всей квартиры =====");
	  System.out.println ();
	  System.out.println ("СТЕНЫ и ПОЛЫ");
	  System.out.println ("Общая площадь пола: " + totalFloorArea + " м².");
	  System.out.println ("Общая площадь стен: " + totalWallArea + " м².");
	  System.out.println ("Общий периметр квартиры: " + totalApartmentPerimeter + " м.");

	  System.out.println ();
	  System.out.println ("ОКНА");
	  System.out.println ("общая площадь окон для всей квартиры:" + totalWindowsArea + " м.");
	  System.out.println ("Общая длина откосов окон для всей квартиры: " + totalWindowsSlopeLength + " м.");

	  System.out.println ();
	  System.out.println ("ДВЕРИ");
	  System.out.println ("общая площадь дверей для всей квартиры:" + totalDoorsArea + " м.");
	  System.out.println ("Общая длина откосов дверей для всей квартиры: " + totalDoorsSlopeLength + " м.");

	  System.out.println ();
	  System.out.println ("ЭЛЕКТРИКА");
	  System.out.println ("Общее кол-во подрозетников (шт.):" + totalNumberOfSockets);
	  System.out.println ("Общая длина кабеля для всей квартиры: " + totalCableLength + " м.");
	} catch (
		  IOException e) {
	  System.out.println ("ошибка при записи в файл" + e.getMessage ());
	}
	sc.close ();
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




