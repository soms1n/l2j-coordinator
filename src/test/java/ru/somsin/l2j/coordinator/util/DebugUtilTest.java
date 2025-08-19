package ru.somsin.l2j.coordinator.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class DebugUtilTest {
    private ByteArrayOutputStream outputStream;
    private ByteArrayOutputStream errorStream;
    private PrintStream originalOut;
    private PrintStream originalErr;

    @BeforeEach
    void setUp() {
        // Сбрасываем режим отладки перед каждым тестом
        DebugUtil.setDebugMode(false);

        // Сохраняем оригинальные потоки
        originalOut = System.out;
        originalErr = System.err;

        // Создаем потоки для перехвата вывода
        outputStream = new ByteArrayOutputStream();
        errorStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }

    @Test
    void testDebugModeDefaultValue() {
        assertThat(DebugUtil.isDebugMode()).isFalse();
    }

    @Test
    void testSetDebugMode() {
        DebugUtil.setDebugMode(true);
        assertThat(DebugUtil.isDebugMode()).isTrue();

        DebugUtil.setDebugMode(false);
        assertThat(DebugUtil.isDebugMode()).isFalse();
    }

    @Test
    void testDebugWhenDisabled() {
        DebugUtil.debug("Test debug message");
        assertThat(outputStream.toString()).isEmpty();
    }

    @Test
    void testDebugWhenEnabled() {
        DebugUtil.setDebugMode(true);
        DebugUtil.debug("Test debug message");

        String output = outputStream.toString();
        assertThat(output).contains("[DEBUG] Test debug message");
    }

    @Test
    void testDebugWithFormatting() {
        DebugUtil.setDebugMode(true);
        DebugUtil.debug("Test %s with %d parameters", "message", 2);

        String output = outputStream.toString();
        assertThat(output).contains("[DEBUG] Test message with 2 parameters");
    }

    @Test
    void testInfoAlwaysShows() {
        DebugUtil.info("Test info message");

        String output = outputStream.toString();
        assertThat(output).contains("Test info message");
        assertThat(output).doesNotContain("[DEBUG]");
    }

    @Test
    void testInfoWithFormatting() {
        DebugUtil.info("Test %s with %d parameters", "info", 3);

        String output = outputStream.toString();
        assertThat(output).contains("Test info with 3 parameters");
    }

    @Test
    void testErrorAlwaysShows() {
        DebugUtil.error("Test error message");

        String errorOutput = errorStream.toString();
        assertThat(errorOutput).contains("[ERROR] Test error message");
    }

    @Test
    void testMixedOutput() {
        DebugUtil.setDebugMode(true);

        DebugUtil.debug("Debug message");
        DebugUtil.info("Info message");
        DebugUtil.error("Error message");

        String output = outputStream.toString();
        String errorOutput = errorStream.toString();

        assertThat(output).contains("[DEBUG] Debug message");
        assertThat(output).contains("Info message");
        assertThat(errorOutput).contains("[ERROR] Error message");
    }
}
