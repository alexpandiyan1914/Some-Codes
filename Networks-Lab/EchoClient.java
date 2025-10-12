import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;
        int packetCount = 0;

        try (Socket socket = new Socket(host, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("🟢 Connected to Echo Server at " + host + ":" + port);

            String message;
            while (true) {
                System.out.print("Enter message: ");
                message = input.readLine();
                packetCount++;
                out.println(message);
                System.out.println("📦 Sent packet " + packetCount);

                String response = in.readLine();
                System.out.println("📥 Server response: " + response);

                if (message.equalsIgnoreCase("bye")) break;
            }

            System.out.println("📊 Total packets sent: " + packetCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    