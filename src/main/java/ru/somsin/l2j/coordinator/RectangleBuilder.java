package ru.somsin.l2j.coordinator;

import java.util.ArrayList;
import java.util.List;

public class RectangleBuilder {
    
    public static List<Coordinate> buildRectangle(Coordinate point1, Coordinate point2, 
                                                 int rows, int columnsPerRow) {
        if (rows <= 0 || columnsPerRow <= 0) {
            throw new IllegalArgumentException("Количество строк и столбцов должно быть положительным");
        }
        
        List<Coordinate> allColumns = new ArrayList<>();
        
        double cellWidth = (point2.getX() - point1.getX()) / columnsPerRow;
        double cellHeight = (point2.getY() - point1.getY()) / rows;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columnsPerRow; col++) {
                double x = point1.getX() + (col + 0.5) * cellWidth;
                double y = point1.getY() + (row + 0.5) * cellHeight;
                
                allColumns.add(new Coordinate(x, y));
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
        
        double cellWidth = (point2.getX() - point1.getX()) / totalColumns;
        double cellHeight = (point2.getY() - point1.getY()) / rows;
        
        for (int col = 0; col < totalColumns; col++) {
            int row = col / (totalColumns / rows);
            
            double x = point1.getX() + (col + 0.5) * cellWidth;
            double y = point1.getY() + (row + 0.5) * cellHeight;
            
            allColumns.add(new Coordinate(x, y));
        }
        
        return allColumns;
    }
}
