import java.util.Scanner;

public class MeterToFootConverter {
    public static void main(String[] args){

        try (//create a Scanner object to read input from user
        Scanner scanner = new Scanner(System.in)) {
            // Prompt the user to enter a value in meters
            System.out.print("Enter the value in meters: ");
            double meter = scanner.nextDouble();

            // Prompt the user to enter a value in feet
            System.out.print("Enter the value in feet: ");
            double foot = scanner.nextDouble();

            //call user method to 
            footToMeter(foot);
            meterToFoot(meter);
        }
        
    }
    public static double footToMeter(double foot){
        //convert foot to meter
        double meter = foot * 0.3048;

        System.out.println("Value of " + foot + " feet in meters: " + meter);
        return meter;  

    }
    public static double meterToFoot(double meter){
     //convert meter to foot
     double foot = meter * 3.28084;

     //output results
      System.out.println("Value of " + meter + " meters in feet: " + foot);
      return foot;
    }
   
}