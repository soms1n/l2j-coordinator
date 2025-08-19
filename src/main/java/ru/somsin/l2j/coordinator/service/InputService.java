package ru.somsin.l2j.coordinator.service;

import ru.somsin.l2j.coordinator.model.RectangleInput;

import java.util.Scanner;

import static ru.somsin.l2j.coordinator.util.DebugUtil.debug;
import static ru.somsin.l2j.coordinator.util.DebugUtil.info;

/**
 * Сервис для чтения входных данных от пользователя.
 * Обрабатывает ввод координат и параметров для построения прямоугольника.
 */
public class InputService {
    private static final int X_INDEX = 0;
    private static final int Y_INDEX = 1;
    private static final int Z_INDEX = 0;
    private static final int HEADING_INDEX = 1;

    private final Scanner scanner;

    /**
     * Конструктор по умолчанию.
     * Инициализирует сканер для чтения из System.in.
     */
    public InputService() {
        this.scanner = new Scanner(System.in);
        debug("InputService initialized with System.in");
    }

    /**
     * Конструктор для тестирования.
     * Позволяет передать Scanner для чтения из другого источника.
     *
     * @param scanner сканер для чтения входных данных
     */
    public InputService(Scanner scanner) {
        this.scanner = scanner;
        debug("InputService initialized with provided Scanner");
    }

    /**
     * Читает входные параметры для построения прямоугольника.
     * Запрашивает у пользователя координаты точек, Z-координату, направление,
     * количество столбцов и строк.
     *
     * @return объект RectangleInput с прочитанными параметрами
     * @throws IllegalArgumentException если введены некорректные данные
     */
    public RectangleInput readRectangleInput() {
        debug("Starting to read input parameters for rectangle building");

        info("Enter x,y point 1: ");
        int[] point1 = parseCoordinates(scanner.nextLine());
        debug("Point 1 read: x=%d, y=%d", point1[X_INDEX], point1[Y_INDEX]);

        info("Enter x,y point 2: ");
        int[] point2 = parseCoordinates(scanner.nextLine());
        debug("Point 2 read: x=%d, y=%d", point2[X_INDEX], point2[Y_INDEX]);

        info("Enter z,heading: ");
        int[] zHeading = parseCoordinates(scanner.nextLine());
        debug("Z and heading read: z=%d, heading=%d", zHeading[Z_INDEX], zHeading[HEADING_INDEX]);

        info("Columns: ");
        int columnsPerRow = Integer.parseInt(scanner.nextLine().trim());
        debug("Number of columns: %d", columnsPerRow);

        info("Rows: ");
        int rows = Integer.parseInt(scanner.nextLine().trim());
        debug("Number of rows: %d", rows);

        RectangleInput input = new RectangleInput(
                point1[X_INDEX], point1[Y_INDEX],
                point2[X_INDEX], point2[Y_INDEX],
                zHeading[Z_INDEX], zHeading[HEADING_INDEX],
                columnsPerRow, rows
        );

        debug("All input parameters successfully read");

        return input;
    }

    /**
     * Парсит строку координат в формате "x,y".
     *
     * @param input строка с координатами в формате "x,y"
     * @return массив из двух элементов [x, y]
     * @throws IllegalArgumentException если формат строки некорректен
     */
    int[] parseCoordinates(String input) {
        String[] parts = input.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Use x,y format");
        }

        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        return new int[]{x, y};
    }

    /**
     * Закрывает сканер и освобождает ресурсы.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
