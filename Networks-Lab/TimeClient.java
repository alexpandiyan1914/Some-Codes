import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 6000;
        int packetCount = 0;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("🟢 Connected to Time Server at " + host + ":" + port);

            String response;
            while ((response = in.readLine()) != null) {
                packetCount++;
                System.out.println("⏰ Packet " + packetCount + ": " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
