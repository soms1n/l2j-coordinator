package ru.somsin.l2j.coordinator.service;

import ru.somsin.l2j.coordinator.builder.RectangleBuilder;
import ru.somsin.l2j.coordinator.model.Coordinate;
import ru.somsin.l2j.coordinator.model.RectangleInput;

import java.util.List;

import static ru.somsin.l2j.coordinator.util.DebugUtil.debug;

/**
 * Сервис для построения координат прямоугольника.
 * Координирует процесс создания координат на основе входных параметров.
 */
public class CoordinateService {

    /**
     * Строит координаты прямоугольника на основе входных параметров.
     *
     * @param input входные параметры для построения прямоугольника
     * @return список координат точек сетки прямоугольника
     */
    public List<Coordinate> buildRectangleCoordinates(RectangleInput input) {
        debug("Starting to build rectangle coordinates: %dx%d", input.columnsPerRow(), input.rows());

        Coordinate point1 = new Coordinate(input.x1(), input.y1(), input.z(), input.heading());
        Coordinate point2 = new Coordinate(input.x2(), input.y2(), input.z(), input.heading());

        List<Coordinate> coordinates = RectangleBuilder.buildRectangle(
                point1, point2, input.rows(), input.columnsPerRow()
        );

        debug("Successfully built %d coordinates", coordinates.size());

        return coordinates;
    }
}
