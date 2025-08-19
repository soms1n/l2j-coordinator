package ru.somsin.l2j.coordinator.model;

/**
 * Запись, содержащая входные параметры для построения прямоугольника.
 *
 * @param x1            координата X первой точки прямоугольника
 * @param y1            координата Y первой точки прямоугольника
 * @param x2            координата X второй точки прямоугольника
 * @param y2            координата Y второй точки прямоугольника
 * @param z             координата Z для всех точек прямоугольника
 * @param heading       направление (поворот) для всех точек прямоугольника
 * @param columnsPerRow количество столбцов в каждой строке
 * @param rows          количество строк в прямоугольнике
 */
public record RectangleInput(int x1, int y1, int x2, int y2, int z, int heading, int columnsPerRow, int rows) {
}
