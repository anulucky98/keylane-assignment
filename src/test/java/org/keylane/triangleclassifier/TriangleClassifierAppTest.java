package org.keylane.triangleclassifier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.keylane.triangleclassifier.exception.InvalidTriangleException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TriangleClassifierAppTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void testInteractiveAppEquilateral() {
        String input = "5\n5\n5\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        TriangleClassifierApp app = new TriangleClassifierApp();
        app.runInteractive();

        String result = output.toString();
        assertTrue(result.contains("EQUILATERAL"), "Expected EQUILATERAL triangle output");
    }

    @ParameterizedTest
    @CsvSource({
            "5,5,5,EQUILATERAL",
            "5,5,3,ISOSCELES",
            "3,4,5,SCALENE"
    })
    void testRunWithArgs(double sideA, double sideB, double sideC, String expectedType) throws InvalidTriangleException {
        TriangleClassifierApp app = new TriangleClassifierApp();
        String[] args = {String.valueOf(sideA), String.valueOf(sideB), String.valueOf(sideC)};
        app.runWithArgs(args);

        String result = output.toString();
        assertTrue(result.contains(expectedType), "Expected output to contain: " + expectedType);
    }

    @ParameterizedTest
    @CsvSource({
            "0,5,5",
            "-1,5,5",
            "1,2,3",
            "2,2,5",
            "0.0,0.0,0.0"
    })
    void testInvalidTriangles(double sideA, double sideB, double sideC) {
        TriangleClassifierApp app = new TriangleClassifierApp();
        String[] args = {String.valueOf(sideA), String.valueOf(sideB), String.valueOf(sideC)};
        app.runWithArgs(args);

        String result = output.toString();
        assertTrue(result.contains("Error"), "Expected output to contain exception");
    }
}
