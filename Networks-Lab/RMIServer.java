import java.rmi.*;

public class RMIServer {
    public static void main(String[] args) {
        try {
            AdderImpl obj = new AdderImpl();
            Naming.rebind("rmi://localhost:1099/AdderService", obj);
            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e);
        }
    }
}
