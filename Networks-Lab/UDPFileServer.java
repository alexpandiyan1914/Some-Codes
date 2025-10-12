import java.io.*;
import java.net.*;

public class UDPFileServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(7000);
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            socket.receive(packet);
            String fileName = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Client requested: " + fileName);

            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();

            File file = new File(fileName);
            if (!file.exists()) {
                String msg = "File not found!";
                socket.send(new DatagramPacket(msg.getBytes(), msg.length(), clientAddress, clientPort));
                socket.close();
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                byte[] sendData = line.getBytes();
                socket.send(new DatagramPacket(sendData, sendData.length, clientAddress, clientPort));
            }

            byte[] endMsg = "EOF".getBytes();
            socket.send(new DatagramPacket(endMsg, endMsg.length, clientAddress, clientPort));

            reader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
