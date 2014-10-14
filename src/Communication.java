import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;


public class Communication implements Runnable {
	DatagramSocket socket = null;
	String in = null;
	
	public Communication(DatagramSocket socket, String in){
		this.socket = socket;
		this.in = in;
	}
	
	@Override
	public void run() {
		try {
			byte[] buf = new byte[256];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            String dString = null;
            if (in == null)
                dString = new Date().toString();
            else
                dString = in;

            buf = dString.getBytes("ISO-8859-1");

            // send the response to the client at "address" and "port"
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
            Thread.sleep(1000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    socket.close();
	}
	
}
