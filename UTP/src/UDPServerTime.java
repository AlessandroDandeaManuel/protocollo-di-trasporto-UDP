import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.Instant;

public class UDPServerTime {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        byte[] buffer = new byte[1024];
        DatagramSocket socket = new DatagramSocket(port);
        while (true) {
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);
            long seconds = Instant.now().getEpochSecond();
            byte[] response = Long.toBinaryString(seconds).getBytes();
            DatagramPacket reply = new DatagramPacket(response, response.length, request.getAddress(), request.getPort());
            socket.send(reply);
        }
    }
}