import java.rmi.*;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Adder stub = (Adder) Naming.lookup("rmi://localhost:1099/AdderService");
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter first number: ");
            int a = sc.nextInt();

            System.out.print("Enter second number: ");
            int b = sc.nextInt();

            System.out.println("Sum = " + stub.add(a, b));
            System.out.println("Product = " + stub.multiply(a, b));

            sc.close();
        } catch (Exception e) {
            System.out.println("Client Exception: " + e);
        }
    }
}
