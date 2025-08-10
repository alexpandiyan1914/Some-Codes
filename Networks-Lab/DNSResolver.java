import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSResolver {
    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Local DNS Server Hostname: " + localHost.getHostName());
            System.out.println("Local DNS Server IP Address: " + localHost.getHostAddress());

            String hostToResolve = "www.google.com";
            InetAddress resolvedHost = InetAddress.getByName(hostToResolve);
            System.out.println("Resolved Hostname: " + resolvedHost.getHostName());
            System.out.println("Resolved IP Address: " + resolvedHost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
