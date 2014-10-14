import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;


public class QuoteClientThread implements Runnable{
	private String strAddress;
	
	public QuoteClientThread(String address){
		this.strAddress = address;
	}
	
	public void run() {
		try{
			// get a datagram socket
		    DatagramSocket socket = new DatagramSocket();
		    socket.setSoTimeout(1000);
		    // send request
		    byte[] buf = new byte[256];
		    InetAddress address = InetAddress.getByName(strAddress);
		    
		    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
		    socket.send(packet);
		 
		    // get response
		    packet = new DatagramPacket(buf, buf.length);
		    socket.receive(packet);
		
		    // display response
		    String received = new String(packet.getData(), 0, packet.getLength());
		    System.out.print("Quote of the Moment: " + received + "  Received by:" + strAddress);
		    System.out.println("  Received by:" + strAddress);
		    socket.close();
		}catch(SocketTimeoutException e){
	    	System.out.println("Connection timeout on address " + strAddress + ", closing connection");
	    }
		catch (IOException e) {
            e.printStackTrace();
		}
	}
}
