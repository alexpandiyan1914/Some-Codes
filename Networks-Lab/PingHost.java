import java.io.*;
import java.net.*;

public class PingHost {
    public static void main(String[] args) {
        String host = "google.com";
        int pingCount = 4;
        int sent = 0, received = 0;

        try {
            System.out.println("🏓 Pinging " + host + " with 4 packets...\n");

            for (int i = 1; i <= pingCount; i++) {
                long startTime = System.currentTimeMillis();
                InetAddress inet = InetAddress.getByName(host);
                sent++;

                if (inet.isReachable(2000)) {
                    long endTime = System.currentTimeMillis();
                    long latency = endTime - startTime;
                    received++;
                    System.out.println("Reply from " + host + ": time=" + latency + "ms");
                } else {
                    System.out.println("Request timed out...");
                }

                Thread.sleep(1000);
            }

            System.out.println("\n📊 Ping statistics for " + host);
            System.out.println("Packets: Sent = " + sent + ", Received = " + received + ", Lost = " + (sent - received));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
