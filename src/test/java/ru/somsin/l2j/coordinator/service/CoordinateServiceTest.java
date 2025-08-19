package ru.somsin.l2j.coordinator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.somsin.l2j.coordinator.model.Coordinate;
import ru.somsin.l2j.coordinator.model.RectangleInput;
import ru.somsin.l2j.coordinator.service.CoordinateService;
import ru.somsin.l2j.coordinator.util.DebugUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateServiceTest {

    @BeforeEach
    void setUp() {
        // Сбрасываем режим отладки перед каждым тестом
        DebugUtil.setDebugMode(false);
    }

    @Test
    void testBuildRectangleCoordinates() {
        // Подготавливаем тестовые данные
        RectangleInput input = new RectangleInput(0, 0, 2, 2, 10, 90, 3, 3);
        
        // Выполняем тест
        CoordinateService coordinateService = new CoordinateService();
        List<Coordinate> coordinates = coordinateService.buildRectangleCoordinates(input);
        
        // Проверяем результат
        assertThat(coordinates).isNotNull();
        assertThat(coordinates).hasSize(9); // 3x3 = 9 координат
        
        // Проверяем первую координату
        Coordinate first = coordinates.getFirst();
        assertThat(first.x()).isEqualTo(0);
        assertThat(first.y()).isEqualTo(0);
        assertThat(first.z()).isEqualTo(10);
        assertThat(first.heading()).isEqualTo(90);
        
        // Проверяем последнюю координату
        Coordinate last = coordinates.getLast();
        // С новой логикой: x = 0 + (2 * 2) / 2 = 2
        assertThat(last.x()).isEqualTo(2);
        // y = 0 + (2 * 2) / 2 = 2
        assertThat(last.y()).isEqualTo(2);
        assertThat(last.z()).isEqualTo(10);
        assertThat(last.heading()).isEqualTo(90);
    }
    
    @Test
    void testDebugModeDefaultValue() {
        // Проверяем, что по умолчанию debugMode выключен
        assertThat(DebugUtil.isDebugMode()).isFalse();
    }
    
    @Test
    void testSetDebugMode() {
        // Проверяем установку debugMode в true
        DebugUtil.setDebugMode(true);
        assertThat(DebugUtil.isDebugMode()).isTrue();
        
        // Проверяем установку debugMode в false
        DebugUtil.setDebugMode(false);
        assertThat(DebugUtil.isDebugMode()).isFalse();
    }
    
    @Test
    void testDebugModeAffectsConsoleOutput() {
        // Сохраняем оригинальный System.out
        PrintStream originalOut = System.out;
        
        try {
            // Создаем ByteArrayOutputStream для перехвата вывода
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            
            RectangleInput input = new RectangleInput(0, 0, 2, 2, 10, 90, 3, 3);
            CoordinateService coordinateService = new CoordinateService();
            
            // По умолчанию debugMode выключен - вывод не должен появляться
            coordinateService.buildRectangleCoordinates(input);
            String outputWhenDisabled = outputStream.toString();
            assertThat(outputWhenDisabled).isEmpty();
            
            // Очищаем поток
            outputStream.reset();
            
            // Включаем debugMode
            DebugUtil.setDebugMode(true);
            coordinateService.buildRectangleCoordinates(input);
            String outputWhenEnabled = outputStream.toString();
            
            // Проверяем, что вывод появился
            assertThat(outputWhenEnabled).isNotEmpty();
            assertThat(outputWhenEnabled).contains("[DEBUG] Starting to build rectangle coordinates: 3x3");
            assertThat(outputWhenEnabled).contains("[DEBUG] Successfully built 9 coordinates");
            
        } finally {
            // Восстанавливаем оригинальный System.out
            System.setOut(originalOut);
        }
    }
}
