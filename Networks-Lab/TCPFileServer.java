import java.io.*;
import java.net.*;

public class TCPFileServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(6000);
            System.out.println("Server started. Waiting for client...");

            Socket socket = server.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fileName = in.readLine();
            System.out.println("Client requested: " + fileName);

            File file = new File(fileName);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            if (file.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = fileReader.readLine()) != null) {
                    out.println(line);
                }
                fileReader.close();
                out.println("EOF");
            } else {
                out.println("File not found!");
            }

            socket.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
