import java.util.Scanner;

public class TestTriangle {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

         // Prompt the user to enter the sides of the triangle (allow skipping with Enter)
         System.out.print("Enter side1 (default 1.0): ");
         double side1 = getDoubleOrDefault(input, 1.0);
 
         System.out.print("Enter side2 (default 1.0): ");
         double side2 = getDoubleOrDefault(input, 1.0);
 
         System.out.print("Enter side3 (default 1.0): ");
         double side3 = getDoubleOrDefault(input, 1.0);
 
         // Prompt for color and filled status (allow skipping with Enter)
         System.out.print("Enter color (default white): ");
         String color = getStringOrDefault(input, "white");
 
         System.out.print("Is the triangle filled (true/false, default false)? ");
         boolean filled = getBooleanOrDefault(input, false);
 
         // Create a Triangle object with the gathered or default values
         Triangle triangle = new Triangle(side1, side2, side3);
         triangle.setColor(color);
         triangle.setFilled(filled);
 
        // Display the properties of the triangle
        System.out.println("\nTriangle details:");
        System.out.println(triangle.toString());
        System.out.println("Area: " + triangle.getArea());
        System.out.println("Perimeter: " + triangle.getPerimeter());
        System.out.println("Color: " + triangle.getColor());
        System.out.println("Filled: " + triangle.isFilled());

        input.close();
    }
    
    // Helper method to get a double value or a default if the input is empty
    private static double getDoubleOrDefault(Scanner input, double defaultValue) {
        String line = input.nextLine().trim();
        if (line.isEmpty()) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(line);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, using default: " + defaultValue);
            return defaultValue;
        }
    }

    // Helper method to get a string or a default if the input is empty
    private static String getStringOrDefault(Scanner input, String defaultValue) {
        String line = input.nextLine().trim();
        return line.isEmpty() ? defaultValue : line;
    }

    // Helper method to get a boolean or a default if the input is empty
    private static boolean getBooleanOrDefault(Scanner input, boolean defaultValue) {
        String line = input.nextLine().trim();
        if (line.isEmpty()) {
            return defaultValue;
        }
        return Boolean.parseBoolean(line);
    }
}

    
