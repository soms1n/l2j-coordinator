package ru.somsin.l2j.coordinator.builder;

import ru.somsin.l2j.coordinator.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Утилитарный класс для построения координат прямоугольника.
 */
public class RectangleBuilder {
    /**
     * Строит список координат для прямоугольника на основе двух точек и параметров сетки.
     *
     * @param point1        первая точка прямоугольника
     * @param point2        вторая точка прямоугольника
     * @param rows          количество строк в сетке
     * @param columnsPerRow количество столбцов в каждой строке
     * @return список координат точек сетки
     * @throws IllegalArgumentException если количество строк или столбцов не положительное
     */
    public static List<Coordinate> buildRectangle(Coordinate point1, Coordinate point2, int rows, int columnsPerRow) {
        if (rows <= 0 || columnsPerRow <= 0) {
            throw new IllegalArgumentException("Количество строк и столбцов должно быть положительным");
        }

        List<Coordinate> allColumns = new ArrayList<>();

        int totalWidth = Math.abs(point2.x() - point1.x());
        int totalHeight = Math.abs(point2.y() - point1.y());

        if (totalHeight == 0) {
            totalHeight = rows;
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columnsPerRow; col++) {
                int x;
                if (columnsPerRow == 1) {
                    x = point1.x();
                } else {
                    x = point1.x() + (col * totalWidth) / (columnsPerRow - 1);
                }

                int y;
                if (rows == 1) {
                    y = point1.y() + (col * totalHeight) / (columnsPerRow - 1);
                } else {
                    y = point1.y() + (row * totalHeight) / (rows - 1);
                }

                allColumns.add(new Coordinate(x, y, point1.z(), point1.heading()));
            }
        }

        return allColumns;
    }
}
