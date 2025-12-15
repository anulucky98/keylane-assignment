package org.keylane.triangleclassifier;

import org.keylane.triangleclassifier.exception.InvalidTriangleException;
import org.keylane.triangleclassifier.model.Triangle;
import org.keylane.triangleclassifier.model.TriangleType;
import org.keylane.triangleclassifier.service.TriangleClassifier;

import java.util.Scanner;

public class TriangleClassifierApp {

    private final TriangleClassifier classifier;
    private final Scanner scanner;

    public TriangleClassifierApp() {
        this.classifier = new TriangleClassifier();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TriangleClassifierApp app = new TriangleClassifierApp();

        printWelcome();

        if (args.length == 3) {
            app.runWithArgs(args);
        } else {
            app.runInteractive();
        }
    }

    public void runWithArgs(String[] args) {
        try {
            Triangle triangle = parseTriangleArgs(args);
            classifyTriangle(triangle);
        } catch (NumberFormatException e) {
            System.out.println("All command line arguments must be numbers.");
        } catch (InvalidTriangleException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

    }

    private void classifyTriangle(Triangle triangle) throws InvalidTriangleException {
        TriangleType type = classifier.classify(triangle);
        printResult(triangle, type);
    }

    public void runInteractive() {
        boolean continueRunning = true;

        while (continueRunning) {
            try {
                Triangle triangle = readTriangle();
                classifyTriangle(triangle);

            } catch (InvalidTriangleException e) {
                System.out.println("\nError: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nUnexpected error: " + e.getMessage());
            }

            continueRunning = askToContinue();
        }

        System.out.println("Thank you for using Triangle Classifier!");
    }

    private static void printWelcome() {
        System.out.println("===============================================");
        System.out.println("TRIANGLE CLASSIFIER APPLICATION");
        System.out.println("===============================================");
        System.out.println();
        System.out.println("This application classifies triangles as");
        System.out.println("a. EQUILATERAL: All sides equal");
        System.out.println("b. ISOSCELES: Two sides equal");
        System.out.println("c. SCALENE: All sides different");
        System.out.println();
    }

    private Triangle readTriangle() {
        System.out.println("-----------------------------------------------");
        System.out.println("Enter the lengths of the triangle's three sides:");
        double sideA = readSide("Side A");
        double sideB = readSide("Side B");
        double sideC = readSide("Side C");
        return new Triangle(sideA, sideB, sideC);
    }

    public Triangle parseTriangleArgs(String[] args) throws InvalidTriangleException {
        if (args.length != 3) {
            throw new InvalidTriangleException("Exactly 3 sides are required");
        }

        double sideA = Double.parseDouble(args[0]);
        double sideB = Double.parseDouble(args[1]);
        double sideC = Double.parseDouble(args[2]);

        return new Triangle(sideA, sideB, sideC);
    }

    private double readSide(String sideName) {
        while (true) {
            try {
                System.out.print(sideName + ": ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.err.println("  Please enter a value");
                    continue;
                }

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {
                System.err.println("  Invalid number format. Please enter a valid number.");
            }
        }
    }

    private void printResult(Triangle triangle, TriangleType type) {
        System.out.println();
        System.out.println("===============================================");
        System.out.println("TRIANGLE with sides - " + triangle.getSideA() + ", " + triangle.getSideB() + " and " + triangle.getSideC());
        System.out.println("Result: " + type.getDisplayName().toUpperCase());
        System.out.println(type.getDescription());
        System.out.println("===============================================");
        System.out.println();
    }

    private boolean askToContinue() {
        System.out.print("Would you like to classify another triangle? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        System.out.println();
        return response.equals("yes") || response.equals("y");
    }
}