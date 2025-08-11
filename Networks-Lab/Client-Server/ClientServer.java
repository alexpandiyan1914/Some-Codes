import java.net.*;
import java.io.*;

public class ClientServer {
    public static void main(String[] args) throws Exception{
        Socket socket  = new Socket("localhost",5000);
        System.out.println("connected to port 5000...");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String servertime = in.readLine();
        System.out.println("Server Time: "+servertime);
        socket.close();
    }    
}

