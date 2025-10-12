import java.rmi.*;
import java.rmi.server.*;

public class AdderImpl extends UnicastRemoteObject implements Adder {
    AdderImpl() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }
}
