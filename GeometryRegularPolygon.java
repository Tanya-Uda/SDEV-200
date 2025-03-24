public class GeometryRegularPolygon {
    
    private int n;        // Number of sides
    private double side;  // Length of each side
    private double x;     // x-coordinate of the center
    private double y;     // y-coordinate of the center

    // No-arg constructor (default values)
    public GeometryRegularPolygon() {
        n = 3;      // Default number of sides
        side = 1;   // Default side length
        x = 0;      // Default x-coordinate
        y = 0;      // Default y-coordinate
    }

    // Constructor with specified number of sides and side length
    public GeometryRegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
        x = 0;      // Default x-coordinate
        y = 0;      // Default y-coordinate
    }

    // Constructor with specified number of sides, side length, and center coordinates
    public GeometryRegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    // Accessor and mutator methods
    //return an int, which is a default value of n
    public int getN() {
        return n;
    }

    //accept one argument of type int, which will be used to update the value of n
    public void setN(int n) {
        this.n = n;
    }

    //return the default value of side
    public double getSide() {
        return side;
    }

    //update the value of side
    public void setSide(double side) {
        this.side = side;
    }

    //return the default x-coordinate of the polygon's center
    public double getX() {
        return x;
    }

    //updates the x-coordinate
    public void setX(double x) {
        this.x = x;
    }

    //return the default y-coordinate of the polugon's center
    public double getY() {
        return y;
    }

    //update the y-coordinate
    public void setY(double y) {
        this.y = y;
    }

    // Method to calculate the perimeter
    public double getPerimeter() {
        return n * side;
    }

    // Method to calculate the area
    public double getArea() {
        return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }
}
