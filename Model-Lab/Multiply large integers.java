public class StringMultiplication {

    public static String multiply(String num1, String num2) {
        // Edge case
        if (num1.equals("0") || num2.equals("0")) return "0";

        int[] result = new int[num1.length() + num2.length()];

        // Reverse loop through digits
        for (int i = num1.length() - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';

                int mul = digit1 * digit2;
                int sum = mul + result[i + j + 1]; // Add to current position

                result[i + j + 1] = sum % 10; // Unit digit
                result[i + j] += sum / 10;    // Carry
            }
        }

        // Build the result string
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            // Skip leading zeros
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";

        String result = multiply(num1, num2);
        System.out.println("Product: " + result); // Output: 56088
    }
}