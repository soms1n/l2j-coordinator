package ru.somsin.l2j.coordinator.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.somsin.l2j.coordinator.model.Coordinate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleBuilderTest {
    private Coordinate point1;
    private Coordinate point2;

    @BeforeEach
    void setUp() {
        point1 = new Coordinate(0, 0, 3, 8000);
        point2 = new Coordinate(100, 50, 3, 8000);
    }

    @Test
    void testBuildRectangle() {
        int rows = 2;
        int columnsPerRow = 3;

        List<Coordinate> columns = RectangleBuilder.buildRectangle(point1, point2, rows, columnsPerRow);

        assertThat(columns).hasSize(rows * columnsPerRow);

        Coordinate firstColumn = columns.getFirst();
        assertThat(firstColumn.x()).isEqualTo(0);
        assertThat(firstColumn.y()).isEqualTo(0);
        assertThat(firstColumn.z()).isEqualTo(3);
        assertThat(firstColumn.heading()).isEqualTo(8000);

        Coordinate lastColumn = columns.getLast();
        assertThat(lastColumn.x()).isEqualTo(100);
        assertThat(lastColumn.y()).isEqualTo(50);
        assertThat(lastColumn.z()).isEqualTo(3);
        assertThat(lastColumn.heading()).isEqualTo(8000);
    }

    @Test
    void testInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> RectangleBuilder.buildRectangle(point1, point2, -1, 3));

        assertThrows(IllegalArgumentException.class, () -> RectangleBuilder.buildRectangle(point1, point2, 2, 0));
    }

    @Test
    void testSingleRow() {
        int rows = 1;
        int columnsPerRow = 4;

        List<Coordinate> columns = RectangleBuilder.buildRectangle(point1, point2, rows, columnsPerRow);

        assertThat(columns).hasSize(4);

        // Для одной строки Y-координата должна изменяться между столбцами
        Coordinate firstColumn = columns.getFirst();
        Coordinate lastColumn = columns.getLast();
        
        assertThat(firstColumn.y()).isEqualTo(0); // point1.y()
        assertThat(lastColumn.y()).isEqualTo(50); // point2.y()
        
        // Проверяем, что Y-координата изменяется между столбцами
        for (int i = 0; i < columns.size(); i++) {
            Coordinate column = columns.get(i);
            assertThat(column.z()).isEqualTo(3);
            assertThat(column.heading()).isEqualTo(8000);
            
            // Y-координата должна изменяться от point1.y() до point2.y()
            if (i == 0) {
                assertThat(column.y()).isEqualTo(0);
            } else if (i == columns.size() - 1) {
                assertThat(column.y()).isEqualTo(50);
            }
        }
    }

    @Test
    void testCoordinateProgression() {
        int rows = 2;
        int columnsPerRow = 3;

        List<Coordinate> coordinates = RectangleBuilder.buildRectangle(point1, point2, rows, columnsPerRow);

        assertThat(coordinates).hasSize(6);

        // Проверяем прогрессию X-координат в первой строке
        assertThat(coordinates.get(0).x()).isEqualTo(0);   // col 0, row 0
        assertThat(coordinates.get(1).x()).isEqualTo(50);  // col 1, row 0
        assertThat(coordinates.get(2).x()).isEqualTo(100); // col 2, row 0

        // Проверяем прогрессию X-координат во второй строке
        assertThat(coordinates.get(3).x()).isEqualTo(0);   // col 0, row 1
        assertThat(coordinates.get(4).x()).isEqualTo(50);  // col 1, row 1
        assertThat(coordinates.get(5).x()).isEqualTo(100); // col 2, row 1

        // Проверяем прогрессию Y-координат
        assertThat(coordinates.get(0).y()).isEqualTo(0);   // row 0
        assertThat(coordinates.get(3).y()).isEqualTo(50);  // row 1
    }
}
