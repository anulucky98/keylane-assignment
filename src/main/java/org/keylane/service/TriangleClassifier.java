package org.keylane.service;

import org.keylane.exception.InvalidTriangleException;
import org.keylane.model.Triangle;
import org.keylane.model.TriangleType;
import org.keylane.util.TriangleValidator;

public class TriangleClassifier {

    private final TriangleValidator validator;

    public TriangleClassifier() {
        this.validator = new TriangleValidator();
    }

    public TriangleType classify(Triangle triangle) throws InvalidTriangleException {
        if (triangle == null) {
            throw new InvalidTriangleException("Triangle cannot be null");
        }

        double sideA = triangle.getSideA();
        double sideB = triangle.getSideB();
        double sideC = triangle. getSideC();

        validator.validate(sideA, sideB, sideC);

        boolean abEqual = (sideA == sideB);
        boolean bcEqual = (sideB == sideC);
        boolean acEqual = (sideA == sideC);

        if (abEqual && bcEqual) {
            return TriangleType.EQUILATERAL;
        }

        if (abEqual || bcEqual || acEqual) {
            return TriangleType.ISOSCELES;
        }

        return TriangleType.SCALENE;
    }
}