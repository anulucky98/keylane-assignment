package org.keylane;

import org.keylane.exception.InvalidTriangleException;
import org.keylane.model.Triangle;
import org.keylane.model.TriangleType;
import org.keylane.service.TriangleClassifier;

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
        app.run();
    }
    
    public void run() {
        printWelcome();

        boolean continueRunning = true;

        while (continueRunning) {
            try {
                Triangle triangle = readTriangle();
                TriangleType type = classifier.classify(triangle);
                printResult(type);

            } catch (InvalidTriangleException e) {
                System.out.println("\nError: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nUnexpected error: " + e. getMessage());
            }

            continueRunning = askToContinue();
        }

        System.out.println("Thank you for using Triangle Classifier!");
        scanner.close();
    }
    
    private void printWelcome() {
        System.out.println("===============================================");
        System.out. println("TRIANGLE CLASSIFIER APPLICATION");
        System.out. println("===============================================");
        System.out.println();
        System.out.println("This application classifies triangles as - ");
        System.out.println("a. EQUILATERAL: All sides equal");
        System.out. println("b. ISOSCELES: Two sides equal");
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
                System. err.println("  Invalid number format. Please enter a valid number.");
            }
        }
    }
    
    private void printResult(TriangleType type) {
        System.out.println();
        System.out.println("===============================================");
        System.out. println("Result: " + type.getDisplayName().toUpperCase());
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