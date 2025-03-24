import java.util.Scanner;

public class IdenticalArrays {

    //method to check if two arraysare identical
    public static boolean equals(int[][] m1, int[][] m2){
        //check if the arrays have the same dimentions (3x3)
        if (m1.length != m2.length || m1[0].length != m2[0].length){
            return false;
        }

        //compare each element in the arrays
        for (int i = 0; i < m1.length; i++){
            for (int j = 0; j < m1[i].length; j++){
                if (m1[i][j] != m2[i][j]){
                    return false;
                }
            }
        }

        //if all elements are equal, return true
        return true;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //create two 3x3 arrays
        int[][] m1 = new int[3][3];
        int[][]m2 = new int[3][3];

        //prompt the user to enter elements for the first array
        System.out.println("Enter elements for the first 3x3 array (m1): ");
        for (int i = 0; i <3; i++){
            for (int j = 0; j <3; j++){
                System.out.print("m1[" + i +"] ["+ j + "]: ");
                m1[i][j] = scanner.nextInt();
            }
        }

        //prompt the user to enter elements for the second array
        System.out.println("Enter elements for the second 3x3 array (m2):");
        for (int i = 0; i <3; i++){
            for (int j = 0; j <3; j++){
                System.out.print("m2[" + i +"] ["+ j + "]: ");
                m2[i][j] = scanner.nextInt();
            }
        }

        //check if the arrays are identical and display the result
        if (equals(m1,m2)){
            System.out.println("\nThe two arrays are identical.");
        }
        else{
            System.out.println("\nThe two arrays are not identical.");
        }
        //close the scanner
        scanner.close();
    }
    
}
