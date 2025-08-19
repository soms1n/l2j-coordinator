package ru.somsin.l2j.coordinator.model;

/**
 * Запись, представляющая координаты точки в трехмерном пространстве.
 *
 * @param x       координата по оси X
 * @param y       координата по оси Y
 * @param z       координата по оси Z
 * @param heading направление (поворот)
 */
public record Coordinate(int x, int y, int z, int heading) {
}
