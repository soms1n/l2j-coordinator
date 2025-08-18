package ru.somsin.l2j.coordinator.builder;

import ru.somsin.l2j.coordinator.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class RectangleBuilder {
    
    public static List<Coordinate> buildRectangle(Coordinate point1, Coordinate point2,
                                                  int rows, int columnsPerRow) {
        if (rows <= 0 || columnsPerRow <= 0) {
            throw new IllegalArgumentException("Количество строк и столбцов должно быть положительным");
        }
        
        List<Coordinate> allColumns = new ArrayList<>();
        
        // Вычисляем размеры ячеек
        int totalWidth = point2.getX() - point1.getX();
        int totalHeight = point2.getY() - point1.getY();
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columnsPerRow; col++) {
                // Вычисляем координаты центра каждой ячейки
                int x = point1.getX() + (col * totalWidth + totalWidth / 2) / columnsPerRow;
                int y = point1.getY() + (row * totalHeight + totalHeight / 2) / rows;
                
                allColumns.add(new Coordinate(x, y, point1.getZ()));
            }
        }
        
        return allColumns;
    }
    
    public static List<Coordinate> buildRectangleUniform(Coordinate point1, Coordinate point2, 
                                                        int rows, int totalColumns) {
        if (rows <= 0 || totalColumns <= 0) {
            throw new IllegalArgumentException("Количество строк и столбцов должно быть положительным");
        }
        
        List<Coordinate> allColumns = new ArrayList<>();
        
        // Вычисляем размеры ячеек
        int totalWidth = point2.getX() - point1.getX();
        int totalHeight = point2.getY() - point1.getY();
        
        for (int col = 0; col < totalColumns; col++) {
            int row = col / (totalColumns / rows);
            
            // Вычисляем координаты центра каждой ячейки
            int x = point1.getX() + (col * totalWidth + totalWidth / 2) / totalColumns;
            int y = point1.getY() + (row * totalHeight + totalHeight / 2) / rows;
            
            allColumns.add(new Coordinate(x, y, point1.getZ()));
        }
        
        return allColumns;
    }
}
