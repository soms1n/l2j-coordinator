package ru.l2j.somsin.coordinator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.somsin.l2j.coordinator.builder.RectangleBuilder;
import ru.somsin.l2j.coordinator.model.Coordinate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleBuilderTest {
    private Coordinate point1;
    private Coordinate point2;

    @BeforeEach
    void setUp() {
        point1 = new Coordinate(0, 0, 3);
        point2 = new Coordinate(100, 50, 3);
    }

    @Test
    void testBuildRectangle() {
        int rows = 2;
        int columnsPerRow = 3;

        List<Coordinate> columns = RectangleBuilder.buildRectangle(point1, point2, rows, columnsPerRow);

        assertThat(columns).hasSize(rows * columnsPerRow);

        Coordinate firstColumn = columns.getFirst();
        assertThat(firstColumn.getX()).isEqualTo(16);
        assertThat(firstColumn.getY()).isEqualTo(12);

        Coordinate lastColumn = columns.getLast();
        assertThat(lastColumn.getX()).isEqualTo(83);
        assertThat(lastColumn.getY()).isEqualTo(37);
    }

    @Test
    void testBuildRectangleUniform() {
        int rows = 2;
        int totalColumns = 6;

        List<Coordinate> columns = RectangleBuilder.buildRectangleUniform(point1, point2, rows, totalColumns);

        assertThat(columns).hasSize(totalColumns);

        for (int column = 0; column < totalColumns; column++) {
            Coordinate coordinate = columns.get(column);
            assertThat(coordinate.getX() >= 0 && coordinate.getX() <= 100).isTrue();
            assertThat(coordinate.getY() >= 0 && coordinate.getY() <= 50).isTrue();
        }
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

        int y = columns.getFirst().getY();

        for (Coordinate column : columns) {
            assertThat(column.getY()).isEqualTo(y);
        }
    }
}
