import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPClientTime {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        InetAddress address = InetAddress.getByName("localhost");
        byte[] buffer = new byte[1024];
        DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(1000);
        try {
            socket.send(request);
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);
            String time = new String(response.getData(), 0, response.getLength());
            System.out.println("Time: " + time);
        } catch (SocketTimeoutException e) {
            System.out.println("TIMEOUT");
        }
    }
}