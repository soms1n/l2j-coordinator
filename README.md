# L2J Coordinator - Построение прямоугольника

## Описание

Этот проект предоставляет решение для построения прямоугольника на основе двух точек с указанием количества строк и столбцов, а также получения координат для каждого столбца.

## Структура проекта

- `Coordinate.java` - класс для представления точки с координатами x и y
- `RectangleBuilder.java` - класс для построения прямоугольника и вычисления координат столбцов
- `CoordinateUtils.java` - утилитарный класс для работы с координатами
- `Application.java` - главный класс с примерами использования

## Основные возможности

### 1. Построение прямоугольника с фиксированным количеством столбцов в строке

```java
Coordinate point1 = new Coordinate(0, 0);    // Левый верхний угол
Coordinate point2 = new Coordinate(100, 50);  // Правый нижний угол
int rows = 3;                                 // Количество строк
int columnsPerRow = 4;                        // Столбцов в каждой строке

List<Coordinate> columns = RectangleBuilder.buildRectangle(point1, point2, rows, columnsPerRow);
```

### 2. Построение прямоугольника с равномерным распределением столбцов

```java
int totalColumns = 12;                        // Общее количество столбцов
List<Coordinate> uniformColumns = RectangleBuilder.buildRectangleUniform(point1, point2, rows, totalColumns);
```

### 3. Утилиты для работы с координатами

```java
// Вычисление расстояния между точками
double distance = CoordinateUtils.distance(point1, point2);

// Поиск ближайшей точки
Coordinate nearest = CoordinateUtils.findNearest(target, coordinates);

// Фильтрация по радиусу
List<Coordinate> nearby = CoordinateUtils.filterByRadius(center, radius, coordinates);

// Сортировка по расстоянию
List<Coordinate> sorted = CoordinateUtils.sortByDistance(center, coordinates);

// Проверка, находится ли точка внутри прямоугольника
boolean inside = CoordinateUtils.isInsideRectangle(point, topLeft, bottomRight);

// Вычисление центра прямоугольника
Coordinate center = CoordinateUtils.getRectangleCenter(topLeft, bottomRight);
```

## Алгоритм работы

1. **Вычисление размеров ячеек**: Размеры вычисляются на основе разности координат двух точек, деленной на количество строк и столбцов
2. **Позиционирование столбцов**: Каждый столбец позиционируется в центре своей ячейки
3. **Возврат координат**: Метод возвращает список координат для всех столбцов

## Пример вывода

```
=== Построение прямоугольника ===
Точка 1: (0.0, 0.0)
Точка 2: (100.0, 50.0)

Строим прямоугольник:
Строк: 3
Столбцов в строке: 4

Координаты столбцов:
Строка 1, Столбец 1: (12.5, 8.333333333333334)
Строка 1, Столбец 2: (37.5, 8.333333333333334)
Строка 1, Столбец 3: (62.5, 8.333333333333334)
Строка 1, Столбец 4: (87.5, 8.333333333333334)
Строка 2, Столбец 1: (12.5, 25.0)
...
```

## Тестирование

Проект включает набор тестов для проверки функциональности:

```bash
.\gradlew.bat test
```

## Запуск

Для запуска проекта используйте Gradle:

```bash
.\gradlew.bat run
```

## Требования

- Java 8 или выше
- Gradle

## Лицензия

Этот проект распространяется под лицензией MIT. См. файл [LICENSE](LICENSE) для получения дополнительной информации.
