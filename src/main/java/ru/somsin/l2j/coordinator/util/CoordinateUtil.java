package ru.somsin.l2j.coordinator.util;

import ru.somsin.l2j.coordinator.model.Coordinate;

import java.util.List;
import java.util.ArrayList;

public class CoordinateUtil {
    
    public static double distance(Coordinate p1, Coordinate p2) {
        int dx = p2.getX() - p1.getX();
        int dy = p2.getY() - p1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public static Coordinate findNearest(Coordinate target, List<Coordinate> coordinates) {
        if (coordinates == null || coordinates.isEmpty()) {
            throw new IllegalArgumentException("Список координат не может быть пустым");
        }
        
        Coordinate nearest = coordinates.get(0);
        double minDistance = distance(target, nearest);
        
        for (Coordinate coord : coordinates) {
            double dist = distance(target, coord);
            if (dist < minDistance) {
                minDistance = dist;
                nearest = coord;
            }
        }
        
        return nearest;
    }
    
    public static List<Coordinate> filterByRadius(Coordinate center, double radius, List<Coordinate> coordinates) {
        List<Coordinate> result = new ArrayList<>();
        
        for (Coordinate coord : coordinates) {
            if (distance(center, coord) <= radius) {
                result.add(coord);
            }
        }
        
        return result;
    }
    
    public static List<Coordinate> sortByDistance(Coordinate center, List<Coordinate> coordinates) {
        List<Coordinate> sorted = new ArrayList<>(coordinates);
        sorted.sort((c1, c2) -> {
            double dist1 = distance(center, c1);
            double dist2 = distance(center, c2);
            return Double.compare(dist1, dist2);
        });
        return sorted;
    }
    
    public static boolean isInsideRectangle(Coordinate point, Coordinate topLeft, Coordinate bottomRight) {
        return point.getX() >= topLeft.getX() && 
               point.getX() <= bottomRight.getX() && 
               point.getY() >= topLeft.getY() && 
               point.getY() <= bottomRight.getY();
    }
    
    public static Coordinate getRectangleCenter(Coordinate topLeft, Coordinate bottomRight) {
        int centerX = (topLeft.getX() + bottomRight.getX()) / 2;
        int centerY = (topLeft.getY() + bottomRight.getY()) / 2;
        return new Coordinate(centerX, centerY, topLeft.getZ());
    }
}
