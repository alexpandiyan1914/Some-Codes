import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server 5000 is running ....");
        while(true){
            Socket client = ss.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(),true);
            out.println(new Date().toString());
            client.close();
        }

    }
    
}
