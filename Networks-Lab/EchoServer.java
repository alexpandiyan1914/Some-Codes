import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 5000;
        int packetCount = 0;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("🔵 Echo Server started on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("✅ Client connected: " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                packetCount++;
                System.out.println("📩 Received (" + packetCount + "): " + message);
                out.println("Echo: " + message);
                System.out.println("📤 Acknowledged packet " + packetCount);
                if (message.equalsIgnoreCase("bye")) break;
            }

            socket.close();
            System.out.println("❌ Connection closed. Total packets handled: " + packetCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
