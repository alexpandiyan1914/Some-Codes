import java.io.*;

public class RouterInfo {

    public static void main(String[] args) {
        try {
            String gatewayIP = getDefaultGateway();
            System.out.println("Default Gateway IP: " + gatewayIP);

            if (gatewayIP != null) {
                String macAddress = getMacAddressFromArp(gatewayIP);
                System.out.println("MAC Address of Next Hop Router: " + macAddress);
            } else {
                System.out.println("Could not determine the gateway IP.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private static String getDefaultGateway() throws IOException {
    ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "route print");
    Process process = pb.start();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line;
    boolean foundIPv4 = false;

    while ((line = reader.readLine()) != null) {
        line = line.trim();

        if (line.contains("IPv4 Route Table")) {
            foundIPv4 = true;
        }

        if (foundIPv4 && line.startsWith("0.0.0.0")) {
            String[] parts = line.split("\\s+");
            if (parts.length >= 3) {
                return parts[2];  
            }
        }
    }
    return null;
}

    private static String getMacAddressFromArp(String ipAddress) throws IOException {
        ProcessBuilder pingProc = new ProcessBuilder("ping", "-n", "1", ipAddress);
        pingProc.start();


        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        ProcessBuilder pb = new ProcessBuilder("arp", "-a");
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(ipAddress)) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 2) {
                    return parts[1];
                }
            }
        }
        return "MAC address not found.";
    }
}
