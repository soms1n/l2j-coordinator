package ru.l2j.somsin.coordinator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.somsin.l2j.coordinator.Coordinate;
import ru.somsin.l2j.coordinator.RectangleBuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class RectangleBuilderTest {
    
    private Coordinate point1;
    private Coordinate point2;
    
    @BeforeEach
    void setUp() {
        point1 = new Coordinate(0, 0);
        point2 = new Coordinate(100, 50);
    }
    
    @Test
    void testBuildRectangle() {
        int rows = 2;
        int columnsPerRow = 3;
        
        List<Coordinate> columns = RectangleBuilder.buildRectangle(point1, point2, rows, columnsPerRow);
        
        assertEquals(rows * columnsPerRow, columns.size());
        
        Coordinate firstColumn = columns.get(0);
        assertEquals(16.666666666666668, firstColumn.getX(), 0.001);
        assertEquals(12.5, firstColumn.getY(), 0.001);
        
        Coordinate lastColumn = columns.get(columns.size() - 1);
        assertEquals(83.33333333333333, lastColumn.getX(), 0.001);
        assertEquals(37.5, lastColumn.getY(), 0.001);
    }
    
    @Test
    void testBuildRectangleUniform() {
        int rows = 2;
        int totalColumns = 6;
        
        List<Coordinate> columns = RectangleBuilder.buildRectangleUniform(point1, point2, rows, totalColumns);
        
        assertEquals(totalColumns, columns.size());
        
        for (int i = 0; i < totalColumns; i++) {
            Coordinate coord = columns.get(i);
            assertTrue(coord.getX() >= 0 && coord.getX() <= 100);
            assertTrue(coord.getY() >= 0 && coord.getY() <= 50);
        }
    }
    
    @Test
    void testInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> {
            RectangleBuilder.buildRectangle(point1, point2, -1, 3);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            RectangleBuilder.buildRectangle(point1, point2, 2, 0);
        });
    }
    
    @Test
    void testSingleRow() {
        int rows = 1;
        int columnsPerRow = 4;
        
        List<Coordinate> columns = RectangleBuilder.buildRectangle(point1, point2, rows, columnsPerRow);
        
        assertEquals(4, columns.size());
        
        double yCoord = columns.get(0).getY();
        for (Coordinate coord : columns) {
            assertEquals(yCoord, coord.getY(), 0.001);
        }
    }
}
