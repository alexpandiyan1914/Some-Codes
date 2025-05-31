public class RabinKarpSpurious13 {

    static final int d = 10; // base for digits
    static final int q = 1000000007; // modulo value

    public static void countSpuriousHits(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        int p = 0, t = 0, h = 1;
        int spuriousCount = 0;

        // h = pow(d, m-1) % q
        for (int i = 0; i < m - 1; i++)
            h = (h * d) % q;

        // Initial hash
        for (int i = 0; i < m; i++) {
            p = (d * p + (pattern.charAt(i) - '0')) % q;
            t = (d * t + (text.charAt(i) - '0')) % q;
        }

        // Matching process
        for (int i = 0; i <= n - m; i++) {
            if (p == t) {
                // Check actual match
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    System.out.println("Pattern found at index " + i);
                } else {
                    System.out.println("Spurious hit at index " + i);
                    spuriousCount++;
                }
            }

            if (i < n - m) {
                t = (d * (t - (text.charAt(i) - '0') * h) + (text.charAt(i + m) - '0')) % q;
                if (t < 0)
                    t += q;
            }
        }

        System.out.println("Total spurious hits: " + spuriousCount);
    }

    public static void main(String[] args) {
        String text = "2359023141526739921";
        String pattern = "31415";

        countSpuriousHits(text, pattern);
    }
}
