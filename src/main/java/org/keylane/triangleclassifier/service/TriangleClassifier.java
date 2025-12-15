package org.keylane.triangleclassifier.service;

import org.keylane.triangleclassifier.exception.InvalidTriangleException;
import org.keylane.triangleclassifier.model.Triangle;
import org.keylane.triangleclassifier.model.TriangleType;
import org.keylane.triangleclassifier.validation.TriangleValidator;

public class TriangleClassifier {

    public TriangleType classify(Triangle triangle) throws InvalidTriangleException {
        if (triangle == null) {
            throw new InvalidTriangleException("Triangle cannot be null");
        }

        double sideA = triangle.getSideA();
        double sideB = triangle.getSideB();
        double sideC = triangle.getSideC();

        TriangleValidator.validate(sideA, sideB, sideC);

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