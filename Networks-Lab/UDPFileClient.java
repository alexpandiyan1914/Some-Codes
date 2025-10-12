import java.io.*;
import java.net.*;

public class UDPFileClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter file name to download: ");
            String fileName = input.readLine();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] sendData = fileName.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, serverAddress, 7000);
            socket.send(packet);

            PrintWriter writer = new PrintWriter("udp_" + fileName);
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            while (true) {
                socket.receive(receivePacket);
                String data = new String(receivePacket.getData(), 0, receivePacket.getLength());
                if (data.equals("EOF")) break;
                writer.println(data);
            }

            writer.close();
            System.out.println("File downloaded as udp_" + fileName);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
