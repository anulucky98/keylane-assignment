package org.keylane.util;

import org.keylane.exception.InvalidTriangleException;

public class TriangleValidator {
    public void validate(double sideA, double sideB, double sideC)
            throws InvalidTriangleException {

        validateTriangleSide(sideA, "Side A");
        validateTriangleSide(sideB, "Side B");
        validateTriangleSide(sideC, "Side C");

        validateTriangleInequality(sideA, sideB, sideC);
    }

    private void validateTriangleSide(double side, String sideName)
            throws InvalidTriangleException {

        if (Double.isNaN(side)) {
            throw new InvalidTriangleException(sideName + " is not a valid number");
        }

        if (Double.isInfinite(side)) {
            throw new InvalidTriangleException(sideName + " cannot be infinite");
        }

        if (side <= 0) {
            throw new InvalidTriangleException(sideName + "must be greater than zero. Got: " + side);
        }
    }

    /**
     * Validates if the sum of any two sides is greater than the third side.
     */
    private void validateTriangleInequality(double sideA, double sideB, double sideC)
            throws InvalidTriangleException {

        if (sideA + sideB <= sideC) {
            throw new InvalidTriangleException(
                    String.format("Triangle inequality violated. \n SideA(%.2f) + SideB(%.2f) must be > %.2f", sideA, sideB, sideC));
        }

        if (sideA + sideC <= sideB) {
            throw new InvalidTriangleException(
                    String.format("Triangle inequality violated. \n SideA(%.2f) + Side C(%.2f) must be > %.2f", sideA, sideC, sideB));
        }

        if (sideB + sideC <= sideA) {
            throw new InvalidTriangleException(
                    String.format("Triangle inequality violated. \n SideB(%.2f) + Side C(%.2f) must be > %.2f", sideB, sideC, sideA));
        }
    }
}
