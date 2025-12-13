package org.keylane.model;

public enum TriangleType {
    EQUILATERAL("Equilateral", "All sides are equal"),
    ISOSCELES("Isosceles", "Exactly two sides are equal"),
    SCALENE("Scalene", "All three sides are different");

    private final String displayName;
    private final String description;

    TriangleType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
