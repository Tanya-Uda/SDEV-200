public class Triangle extends GeometricObject {
    private double side1;
    private double side2;
    private double side3;

    // sides with default values 1.0 to denote three sides of triangle
    public Triangle() {
        this.side1 = 1.0;
        this.side2 = 1.0;
        this.side3 = 1.0;
    }

    // Constructor with specified side lengths
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // Getter methods for the sides
    public double getSide1() {
        return side1;
    }
    
    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    // Method to calculate the area of triangle
    @Override
    public double getArea() {
        double s = (side1 + side2 + side3) / 2; // Semi-perimeter
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3)); // Heron's formula
    }

    // Method to calculate the perimeter of friangle
    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    // Method to return a string description of the triangle
    @Override
    public String toString() {
        return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }
}
