import java.util.Scanner;

// Define a custom exception called BinaryFormatException
class BinaryFormatException extends Exception {
    public BinaryFormatException(String message) {
        super(message);
    }
}

public class BinaryToDecimalConverter2 {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a binary number: ");
        String binaryString = input.nextLine();

        try {
            int decimalValue = bin2Dec(binaryString);
            System.out.println("Decimal equivalent: " + decimalValue);
        } catch (BinaryFormatException e) {
            System.out.println(e.getMessage());
        }

        input.close();
    }

    // Write the bin2Dec(String binaryString) method to convert a binary string into a decimal number
    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        // Check if the string contains only '0' or '1'
        for (char c : binaryString.toCharArray()) {
            if (c != '0' && c != '1') {
                //Implement the bin2Dec method to throw a BinaryFormatException if the string is not a binary string
                throw new BinaryFormatException("Not a binary number");
            }
        }
        // Convert the valid binary string to decimal
        return Integer.parseInt(binaryString, 2);
    }
}