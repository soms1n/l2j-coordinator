package ru.somsin.l2j.coordinator.service;

import org.junit.jupiter.api.Test;
import ru.somsin.l2j.coordinator.model.RectangleInput;
import ru.somsin.l2j.coordinator.service.InputService;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputServiceTest {

    @Test
    void testReadRectangleInputWithScanner() {
        // Подготавливаем тестовые данные
        String input = "10,20\n30,40\n5,90\n8\n6\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);
        
        // Выполняем тест
        InputService inputService = new InputService(scanner);
        RectangleInput result = inputService.readRectangleInput();
        
        // Проверяем результат
        assertNotNull(result);
        assertEquals(10, result.x1());
        assertEquals(20, result.y1());
        assertEquals(30, result.x2());
        assertEquals(40, result.y2());
        assertEquals(5, result.z());
        assertEquals(90, result.heading());
        assertEquals(8, result.columnsPerRow());
        assertEquals(6, result.rows());
        
        inputService.close();
    }

    @Test
    void testReadRectangleInputWithTestData() {
        // Создаем Scanner с тестовыми данными
        String testInput = "0,0\n100,100\n1000,0\n5\n5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        Scanner scanner = new Scanner(inputStream);
        
        // Выполняем тест
        InputService inputService = new InputService(scanner);
        RectangleInput result = inputService.readRectangleInput();
        
        // Проверяем, что используются тестовые данные
        assertNotNull(result);
        assertEquals(0, result.x1());
        assertEquals(0, result.y1());
        assertEquals(100, result.x2());
        assertEquals(100, result.y2());
        assertEquals(1000, result.z());
        assertEquals(0, result.heading());
        assertEquals(5, result.columnsPerRow());
        assertEquals(5, result.rows());
        
        inputService.close();
    }

    @Test
    void testParseCoordinates() {
        InputService inputService = new InputService();
        
        // Тестируем корректный формат
        int[] coords = inputService.parseCoordinates("10,20");
        assertEquals(10, coords[0]);
        assertEquals(20, coords[1]);
        
        // Тестируем некорректный формат
        assertThrows(IllegalArgumentException.class, () -> {
            inputService.parseCoordinates("10");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            inputService.parseCoordinates("10,20,30");
        });
        
        inputService.close();
    }
}
