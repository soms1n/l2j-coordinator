package ru.somsin.l2j.coordinator.util;

import ru.somsin.l2j.coordinator.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Утилитарный класс для работы с координатами.
 * Предоставляет методы для вычисления расстояний, поиска ближайших точек,
 * фильтрации и сортировки координат.
 */
public class CoordinateUtil {

    /**
     * Вычисляет расстояние между двумя точками.
     *
     * @param p1 первая точка
     * @param p2 вторая точка
     * @return расстояние между точками
     */
    public static double distance(Coordinate p1, Coordinate p2) {
        int dx = p2.x() - p1.x();
        int dy = p2.y() - p1.y();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Находит ближайшую точку к заданной среди списка координат.
     *
     * @param target      целевая точка, к которой ищем ближайшую
     * @param coordinates список координат для поиска
     * @return ближайшая координата к целевой точке
     * @throws IllegalArgumentException если список координат пуст или равен null
     */
    public static Coordinate findNearest(Coordinate target, List<Coordinate> coordinates) {
        if (coordinates == null || coordinates.isEmpty()) {
            throw new IllegalArgumentException("Список координат не может быть пустым");
        }

        Coordinate nearest = coordinates.get(0);
        double minDistance = distance(target, nearest);

        for (Coordinate coord : coordinates) {
            double dist = distance(target, coord);
            if (dist < minDistance) {
                minDistance = dist;
                nearest = coord;
            }
        }

        return nearest;
    }

    /**
     * Фильтрует координаты по радиусу от заданного центра.
     *
     * @param center      центральная точка для фильтрации
     * @param radius      радиус в единицах измерения координат
     * @param coordinates список координат для фильтрации
     * @return список координат, находящихся в пределах заданного радиуса
     */
    public static List<Coordinate> filterByRadius(Coordinate center, double radius, List<Coordinate> coordinates) {
        List<Coordinate> result = new ArrayList<>();

        for (Coordinate coord : coordinates) {
            if (distance(center, coord) <= radius) {
                result.add(coord);
            }
        }

        return result;
    }

    /**
     * Сортирует координаты по расстоянию от заданного центра.
     *
     * @param center      центральная точка для сортировки
     * @param coordinates список координат для сортировки
     * @return отсортированный список координат (от ближайших к дальним)
     */
    public static List<Coordinate> sortByDistance(Coordinate center, List<Coordinate> coordinates) {
        List<Coordinate> sorted = new ArrayList<>(coordinates);
        sorted.sort((c1, c2) -> {
            double dist1 = distance(center, c1);
            double dist2 = distance(center, c2);
            return Double.compare(dist1, dist2);
        });
        return sorted;
    }

    /**
     * Проверяет, находится ли точка внутри прямоугольника.
     *
     * @param point       проверяемая точка
     * @param topLeft     верхняя левая точка прямоугольника
     * @param bottomRight нижняя правая точка прямоугольника
     * @return true, если точка находится внутри прямоугольника, false в противном случае
     */
    public static boolean isPointInRectangle(Coordinate point, Coordinate topLeft, Coordinate bottomRight) {
        return point.x() >= topLeft.x() &&
                point.x() <= bottomRight.x() &&
                point.y() >= topLeft.y() &&
                point.y() <= bottomRight.y();
    }

    /**
     * Вычисляет центр прямоугольника.
     *
     * @param topLeft     верхняя левая точка прямоугольника
     * @param bottomRight нижняя правая точка прямоугольника
     * @return координата центра прямоугольника
     */
    public static Coordinate getRectangleCenter(Coordinate topLeft, Coordinate bottomRight) {
        int centerX = (topLeft.x() + bottomRight.x()) / 2;
        int centerY = (topLeft.y() + bottomRight.y()) / 2;
        return new Coordinate(centerX, centerY, topLeft.z(), topLeft.heading());
    }
}
