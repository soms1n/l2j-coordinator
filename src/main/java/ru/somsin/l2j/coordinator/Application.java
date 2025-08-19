package ru.somsin.l2j.coordinator;

import ru.somsin.l2j.coordinator.model.Coordinate;
import ru.somsin.l2j.coordinator.model.RectangleInput;
import ru.somsin.l2j.coordinator.service.CoordinateService;
import ru.somsin.l2j.coordinator.service.InputService;
import ru.somsin.l2j.coordinator.service.OutputService;
import ru.somsin.l2j.coordinator.util.DebugUtil;

import java.util.Arrays;
import java.util.List;

import static ru.somsin.l2j.coordinator.util.DebugUtil.debug;
import static ru.somsin.l2j.coordinator.util.DebugUtil.error;
import static ru.somsin.l2j.coordinator.util.DebugUtil.info;

/**
 * Главный класс приложения L2J Coordinator.
 * Координирует работу сервисов для построения координат прямоугольника.
 */
public class Application {

    private final InputService inputService;
    private final CoordinateService coordinateService;
    private final OutputService outputService;

    /**
     * Конструктор приложения.
     *
     * @param inputService      сервис для чтения входных данных
     * @param coordinateService сервис для построения координат
     * @param outputService     сервис для вывода результатов
     */
    public Application(InputService inputService, CoordinateService coordinateService, OutputService outputService) {
        this.inputService = inputService;
        this.coordinateService = coordinateService;
        this.outputService = outputService;
        debug("Application initialized with services");
    }

    /**
     * Запускает основную логику приложения.
     * Читает входные данные, строит координаты прямоугольника и выводит результат.
     */
    public void run() {
        info("Starting L2J Coordinator application");

        try {
            RectangleInput input = inputService.readRectangleInput();
            List<Coordinate> coordinates = coordinateService.buildRectangleCoordinates(input);
            outputService.outputJson(coordinates);
            debug("Application completed successfully");
        } catch (Exception exception) {
            error("Error occurred during application execution: %s", exception);
        } finally {
            inputService.close();
            debug("InputService closed");
        }
    }

    /**
     * Точка входа в приложение.
     * Инициализирует сервисы и запускает приложение.
     *
     * @param args аргументы командной строки (--debug для включения режима отладки)
     */
    public static void main(String[] args) {
        debug("Initializing L2J Coordinator");

        boolean debugMode = Arrays.asList(args).contains("--debug");
        if (debugMode) {
            DebugUtil.setDebugMode(true);
            debug("Debug mode enabled");
        }

        InputService inputService = new InputService();
        CoordinateService coordinateService = new CoordinateService();
        OutputService outputService = new OutputService();

        Application application = new Application(inputService, coordinateService, outputService);
        application.run();
    }
}