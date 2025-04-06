import java.util.Scanner;

public class TestCircle {
    public static void main(String[] args) {
        // Prompt user for radius
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt user for radius
            System.out.print("Enter the radius for Circle 1: ");
            double radius1 = scanner.nextDouble();
            
            System.out.print("Enter the radius for Circle 2: ");
            double radius2 = scanner.nextDouble();
            
            // Create circles with user-provided radii
            Circle c1 = new Circle(radius1);
            Circle c2 = new Circle(radius2);
            
            // Display circle 1 details
            System.out.println("Circle 1 Area: " + c1.getArea());
            System.out.println("Circle 1 Perimeter: " + c1.getPerimeter());
            
            // Display circle 2 details
            System.out.println("Circle 2 Area: " + c2.getArea());
            System.out.println("Circle 2 Perimeter: " + c2.getPerimeter());
            
            // Check for equality
            System.out.println("c1 equals c2? " + c1.equals(c2));
        }
    }
}