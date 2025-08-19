package ru.somsin.l2j.coordinator.service;

import ru.somsin.l2j.coordinator.model.Coordinate;

import java.util.List;

import static ru.somsin.l2j.coordinator.util.DebugUtil.debug;
import static ru.somsin.l2j.coordinator.util.DebugUtil.info;

/**
 * Сервис для вывода результатов в различных форматах.
 * Преобразует координаты в JSON и выводит их пользователю.
 */
public class OutputService {

    /**
     * Выводит список координат в формате JSON.
     * Создает JSON-массив с объектами координат и выводит результат в консоль.
     *
     * @param coordinates список координат для вывода
     */
    public void outputJson(List<Coordinate> coordinates) {
        debug("Starting to output %d coordinates in JSON format", coordinates.size());

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[").append(System.lineSeparator());

        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate coordinate = coordinates.get(i);
            jsonBuilder.append("    {\"x\": ")
                    .append(coordinate.x())
                    .append(", \"y\": ")
                    .append(coordinate.y())
                    .append(", \"z\": ")
                    .append(coordinate.z())
                    .append(", \"heading\": ")
                    .append(coordinate.heading())
                    .append("}");

            if (i < coordinates.size() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append(System.lineSeparator());
        }

        jsonBuilder.append("]");

        info(jsonBuilder.toString());

        debug("JSON successfully generated and output");
    }
}
