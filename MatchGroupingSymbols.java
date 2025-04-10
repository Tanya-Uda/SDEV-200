import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MatchGroupingSymbols {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MatchGroupingSymbols filename.java");
            return;
        }

        String fileName = args[0];
        Stack<Character> stack = new Stack<>();

        File file = new File(fileName);

        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);

                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty()) {
                            System.out.println("Unmatched closing symbol: " + ch);
                            return;
                        }

                        char open = stack.pop();

                        if (!isMatchingPair(open, ch)) {
                            System.out.println("Mismatched symbols: " + open + " and " + ch);
                            return;
                        }
                    }
                }
            }

            if (stack.isEmpty()) {
                System.out.println("All grouping symbols are matched correctly.");
            } else {
                System.out.println("Unmatched opening symbol(s) found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    public static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}