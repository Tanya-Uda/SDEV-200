import java.util.Scanner;

public class CreditCardNumberValidation {
    //Return true if the card number is valid
    public static boolean isValid(long number){

        int size = getSize(number);

        //A credit card number must have between 13 and 16 digits
        if (size < 13 || size > 16){
            return false;
        }

        // sum of double even place digits
        int sumEven = sumOfDoubleEvenPlace(number);

        // sum of odd place digits
        int sumOdd = sumOfOddPlace(number);

        //sum and check if can divide by 10
        int total = sumEven + sumOdd;
        return total % 10 == 0;
    }
    // sum of doubled even place digits
    public static int sumOfDoubleEvenPlace(long number){
        int sum = 0;
        number /= 10;

        while (number > 0){
            int digit = (int)(number % 10); // Even place digit
            sum += getDigit(digit * 2);     // Double and get single digit
            number /= 100; // Move two places to the left (next even place)
        }

        return sum;
    }
    //Return this number if it is a single digit, otherwise return the sum of the two digits
    public static int getDigit(int number){
        if (number < 10){
            return number;
        }
        else{
            return number / 10 + number % 10;
        }
    }

    //Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number){
        int sum = 0;
        boolean shouldAdd = true;

        while (number > 0){
            int digit = (int)(number % 10);
            number /= 10;

            if (shouldAdd){
                sum += digit;
            }
            shouldAdd = !shouldAdd;
        }
        return sum;

    }

    //Return true if the number d is a prefix for number
    public static boolean prefixMatched(long number, int d){
        int prefixSize = getSize(d);
         long prefix = getPrefix(number, prefixSize);

        return prefix == d;
    }

    //Return the number of digits in d
    public static int getSize(long d){
        return Long.toString(d).length();
    }
    
    //Return the first k number of digits from number. If the number of digits in number is less than k, return number.
    public static long getPrefix(long number, int k) {
        String numStr = Long.toString(number);

        if (numStr.length() < k) {
            return number;
        }

        String prefixStr = numStr.substring(0, k);
        return Long.parseLong(prefixStr);
    }
    public static void main(String[] args){
        //create scanner cobject to get input from the user
        Scanner scanner = new Scanner(System.in);

        //prompt the user to enter a credit card number
        System.out.println("Enter the credit card number: ");
        long number = scanner.nextLong();

        //validate the credit card number 
        if (isValid(number)){
            System.out.println("The credit card is a valid number. ");
        }
        else{
            System.out.println("The credit card is not valid. ");
        }
        //close the scanner
        scanner.close();

    }
    
}
