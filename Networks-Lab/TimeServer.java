import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) {
        int port = 6000;
        int packetCount = 0;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("🕒 Time Server started on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("✅ Client connected: " + socket.getInetAddress());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                packetCount++;
                String currentTime = new Date().toString();
                out.println("Server Time: " + currentTime);
                System.out.println("📤 Sent time packet " + packetCount + ": " + currentTime);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
