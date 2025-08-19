package ru.somsin.l2j.coordinator.service;

import org.junit.jupiter.api.Test;
import ru.somsin.l2j.coordinator.model.Coordinate;
import ru.somsin.l2j.coordinator.service.OutputService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutputServiceTest {

    @Test
    void testOutputJson() {
        // Подготавливаем тестовые данные
        List<Coordinate> coordinates = Arrays.asList(
            new Coordinate(0, 0, 10, 90),
            new Coordinate(1, 0, 10, 90),
            new Coordinate(0, 1, 10, 90)
        );
        
        // Перехватываем вывод
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            OutputService outputService = new OutputService();
            outputService.outputJson(coordinates);
            
            String output = outputStream.toString();
            
            // Проверяем, что вывод содержит JSON
            assertTrue(output.contains("["));
            assertTrue(output.contains("]"));
            assertTrue(output.contains("\"x\": 0"));
            assertTrue(output.contains("\"y\": 0"));
            assertTrue(output.contains("\"z\": 10"));
            assertTrue(output.contains("\"heading\": 90"));
            
            // Проверяем количество координат (ищем только строки с координатами)
            long coordinateCount = output.lines()
                .filter(line -> line.trim().startsWith("{\"x\":"))
                .count();
            assertEquals(3, coordinateCount);
            
        } finally {
            System.setOut(originalOut);
        }
    }
}
