import java.io.*;

public class DNSLookup {
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("nslookup www.stackoverflow.com");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
