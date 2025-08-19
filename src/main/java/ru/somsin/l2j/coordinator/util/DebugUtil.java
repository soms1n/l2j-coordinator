package ru.somsin.l2j.coordinator.util;

/**
 * Утилитарный класс для централизованного управления режимом отладки.
 * Предоставляет статические методы для вывода отладочной информации.
 */
public class DebugUtil {

    private static boolean debugMode = false;

    /**
     * Устанавливает режим отладки для всего приложения.
     *
     * @param debugMode true для включения отладочной информации, false для отключения
     */
    public static void setDebugMode(boolean debugMode) {
        DebugUtil.debugMode = debugMode;
    }

    /**
     * Возвращает текущий режим отладки.
     *
     * @return true если отладка включена, false если отключена
     */
    public static boolean isDebugMode() {
        return debugMode;
    }

    /**
     * Выводит отладочное сообщение в консоль, если режим отладки включен.
     *
     * @param message сообщение для вывода
     */
    public static void debug(String message) {
        if (debugMode) {
            System.out.println("[DEBUG] " + message);
        }
    }

    /**
     * Выводит отладочное сообщение в консоль, если режим отладки включен.
     * Использует форматирование String.format.
     *
     * @param format формат сообщения
     * @param args   аргументы для форматирования
     */
    public static void debug(String format, Object... args) {
        if (debugMode) {
            System.out.println("[DEBUG] " + String.format(format, args));
        }
    }

    /**
     * Выводит информационное сообщение в консоль (всегда, независимо от режима отладки).
     *
     * @param message сообщение для вывода
     */
    public static void info(String message) {
        System.out.println(message);
    }

    /**
     * Выводит информационное сообщение в консоль (всегда, независимо от режима отладки).
     * Использует форматирование String.format.
     *
     * @param format формат сообщения
     * @param args   аргументы для форматирования
     */
    public static void info(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    /**
     * Выводит сообщение об ошибке в stderr.
     *
     * @param message сообщение об ошибке
     */
    public static void error(String message) {
        System.err.println("[ERROR] " + message);
    }

    /**
     * Выводит сообщение об ошибке в stderr.
     * Использует форматирование String.format.
     *
     * @param format    формат сообщения
     * @param exception исключение
     */
    public static void error(String format, Exception exception) {
        System.err.println("[ERROR] " + String.format(format, exception.getMessage()));
    }
}
