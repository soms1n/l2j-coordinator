package ru.somsin.l2j.coordinator;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("=== Построение прямоугольника ===");
        
        Coordinate point1 = new Coordinate(0, 0);
        Coordinate point2 = new Coordinate(100, 50);
        
        System.out.println("Точка 1: " + point1);
        System.out.println("Точка 2: " + point2);
        
        int rows = 3;
        int columnsPerRow = 4;
        
        System.out.println("\nСтроим прямоугольник:");
        System.out.println("Строк: " + rows);
        System.out.println("Столбцов в строке: " + columnsPerRow);
        
        List<Coordinate> columns = RectangleBuilder.buildRectangle(point1, point2, rows, columnsPerRow);
        
        System.out.println("\nКоординаты столбцов:");
        for (int i = 0; i < columns.size(); i++) {
            Coordinate coord = columns.get(i);
            int row = i / columnsPerRow;
            int col = i % columnsPerRow;
            System.out.printf("Строка %d, Столбец %d: %s%n", row + 1, col + 1, coord);
        }
        
        System.out.println("\n=== Равномерное распределение ===");
        int totalColumns = 12;
        List<Coordinate> uniformColumns = RectangleBuilder.buildRectangleUniform(point1, point2, rows, totalColumns);
        
        System.out.println("Общее количество столбцов: " + totalColumns);
        System.out.println("Координаты столбцов:");
        for (int i = 0; i < uniformColumns.size(); i++) {
            Coordinate coord = uniformColumns.get(i);
            System.out.printf("Столбец %d: %s%n", i + 1, coord);
        }
        
        System.out.println("\n=== Утилиты для работы с координатами ===");
        
        Coordinate center = CoordinateUtils.getRectangleCenter(point1, point2);
        System.out.println("Центр прямоугольника: " + center);
        
        Coordinate nearest = CoordinateUtils.findNearest(center, columns);
        System.out.println("Ближайшая точка к центру: " + nearest);
        
        double distance = CoordinateUtils.distance(center, nearest);
        System.out.printf("Расстояние от центра до ближайшей точки: %.2f%n", distance);
        
        List<Coordinate> nearbyPoints = CoordinateUtils.filterByRadius(center, 30.0, columns);
        System.out.println("Точки в радиусе 30 от центра: " + nearbyPoints.size());
        
        List<Coordinate> sortedPoints = CoordinateUtils.sortByDistance(center, columns);
        System.out.println("Первые 3 ближайшие точки к центру:");
        for (int i = 0; i < Math.min(3, sortedPoints.size()); i++) {
            Coordinate coord = sortedPoints.get(i);
            double dist = CoordinateUtils.distance(center, coord);
            System.out.printf("  %s (расстояние: %.2f)%n", coord, dist);
        }
        
        boolean inside = CoordinateUtils.isInsideRectangle(center, point1, point2);
        System.out.println("Центр находится внутри прямоугольника: " + inside);
    }
}