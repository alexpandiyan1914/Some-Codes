import java.util.Scanner;

public class NaivePatternSearch {
    
    public static void naivePatternSearch(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        boolean found = false;

        for (int i = 0; i <= textLength - patternLength; i++) {
            int j;
            for (j = 0; j < patternLength; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == patternLength) {
                System.out.println("Pattern found at index: " + i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Pattern not found in the text.");
        }
    }

    public static void builtInPatternSearch(String text, String pattern) {
        int index = text.indexOf(pattern);
        if (index != -1) {
            System.out.println("Pattern found at index: " + index);
        } else {
            System.out.println("Pattern not found in the text.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the pattern to search: ");
        String pattern = scanner.nextLine();

        System.out.println("\nUsing Naïve Pattern Searching:");
        naivePatternSearch(text, pattern);

        System.out.println("\nUsing Built-in indexOf() method:");
        builtInPatternSearch(text, pattern);

        scanner.close();
    }
}
