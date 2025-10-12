import java.io.*;
import java.net.*;

public class TCPFileClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6000);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.print("Enter file name to download: ");
            String fileName = input.readLine();
            out.println(fileName);

            PrintWriter fileWriter = new PrintWriter("downloaded_" + fileName);
            String line;
            while (!(line = in.readLine()).equals("EOF")) {
                fileWriter.println(line);
            }
            fileWriter.close();

            System.out.println("File downloaded successfully as downloaded_" + fileName);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
