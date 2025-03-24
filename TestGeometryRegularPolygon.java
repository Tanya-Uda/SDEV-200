public class TestGeometryRegularPolygon {

    public static void main(String[] args) {

        // Create first polygon using no-arg constructor
        GeometryRegularPolygon polygon1 = new GeometryRegularPolygon(); //no-arg constructor

        // Create second polygon with n=6 and side=4
        GeometryRegularPolygon polygon2 = new GeometryRegularPolygon(6, 4); //6 sides, length 4

        // Create third polygon with n=10, side=4, x=5.6, y=7.8
        GeometryRegularPolygon polygon3 = new GeometryRegularPolygon(10, 4, 5.6, 7.8); //10 sides, length 4, x = 5.6, y = 7.8

        // Display perimeter and area of polygon1
        System.out.println("Polygon 1(default):");
        System.out.println("Perimeter = " + polygon1.getPerimeter());
        System.out.println("Area = " + polygon1.getArea());

        // Display perimeter and area of polygon2
        System.out.println("\nPolygon 2 (6 sides, length 4):");
        System.out.println("Perimeter = " + polygon2.getPerimeter());
        System.out.println("Area = " + polygon2.getArea());

        // Display perimeter and area of polygon3
        System.out.println("\nPolygon 3 (10 sides, length 4, center at (5.6, 7.8)):");
        System.out.println("Perimeter = " + polygon3.getPerimeter());
        System.out.println("Area = " + polygon3.getArea());
    }
}