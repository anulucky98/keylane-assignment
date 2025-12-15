package org.keylane.triangleclassifier.service;

import org.junit.jupiter.api.Test;
import org.keylane.triangleclassifier.exception.InvalidTriangleException;
import org.keylane.triangleclassifier.model.Triangle;
import org.keylane.triangleclassifier.model.TriangleType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleClassifierTest {
    private final TriangleClassifier classifier = new TriangleClassifier();

    @Test
    void testEquilateralTriangle() throws InvalidTriangleException {
        Triangle triangle = new Triangle(5, 5, 5);
        assertEquals(TriangleType.EQUILATERAL, classifier.classify(triangle));
    }

    @Test
    void testIsoscelesTriangle() throws InvalidTriangleException {
        Triangle triangle = new Triangle(5, 5, 3);
        assertEquals(TriangleType.ISOSCELES, classifier.classify(triangle));
    }

    @Test
    void testScaleneTriangle() throws InvalidTriangleException {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals(TriangleType.SCALENE, classifier.classify(triangle));
    }

    @Test
    void testNullTriangle() {
        InvalidTriangleException exception = assertThrows(InvalidTriangleException.class, () -> classifier.classify(null));
        assertEquals("Triangle cannot be null", exception.getMessage());
    }

    @Test
    void testVerySmallSides() throws InvalidTriangleException {
        Triangle triangle = new Triangle(0.00001, 0.00001, 0.00001);
        assertEquals(TriangleType.EQUILATERAL, classifier.classify(triangle));
    }

    @Test
    void testSidePermutation() throws InvalidTriangleException {
        Triangle triangle1 = new Triangle(3, 4, 5);
        Triangle triangle2 = new Triangle(5, 3, 4);
        Triangle triangle3 = new Triangle(4, 5, 3);

        assertEquals(classifier.classify(triangle1), classifier.classify(triangle2));
        assertEquals(classifier.classify(triangle1), classifier.classify(triangle3));
    }
}
