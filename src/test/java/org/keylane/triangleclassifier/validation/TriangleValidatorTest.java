package org.keylane.triangleclassifier.validation;

import org.junit.jupiter.api.Test;
import org.keylane.triangleclassifier.exception.InvalidTriangleException;

import static org.junit.jupiter.api.Assertions.*;

class TriangleValidatorTest {

    @Test
    void testValidTriangle() {
        assertDoesNotThrow(() -> TriangleValidator.validate(3, 4, 5));
    }

    @Test
    void testNegativeSide() {
        InvalidTriangleException exception = assertThrows(InvalidTriangleException.class,
                () -> TriangleValidator.validate(-1, 2, 3));
        assertTrue(exception.getMessage().contains("must be greater than zero"));
    }

    @Test
    void testZeroSide() {
        InvalidTriangleException exception = assertThrows(InvalidTriangleException.class,
                () -> TriangleValidator.validate(0, 2, 3));
        assertTrue(exception.getMessage().contains("must be greater than zero"));
    }

    @Test
    void testTriangleInequalityViolation() {
        InvalidTriangleException exception = assertThrows(InvalidTriangleException.class,
                () -> TriangleValidator.validate(1, 2, 10));
        assertTrue(exception.getMessage().contains("Triangle inequality violated"));
    }

    @Test
    void testNaNSide() {
        InvalidTriangleException exception = assertThrows(InvalidTriangleException.class,
                () -> TriangleValidator.validate(Double.NaN, 2, 3));
        assertTrue(exception.getMessage().contains("not a valid number"));
    }

    @Test
    void testInfiniteSide() {
        InvalidTriangleException exception = assertThrows(InvalidTriangleException.class,
                () -> TriangleValidator.validate(Double.POSITIVE_INFINITY, 2, 3));
        assertTrue(exception.getMessage().contains("cannot be infinite"));
    }
}
