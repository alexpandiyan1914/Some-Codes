import java.rmi.*;

public interface Adder extends Remote {
    public int add(int a, int b) throws RemoteException;
    public int multiply(int a, int b) throws RemoteException;
}
