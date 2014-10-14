import java.io.*;
import java.net.*;
import java.util.*;
 
public class QuoteServerThread extends Thread {
 
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
 
    public QuoteServerThread() throws IOException {
    	this("QuoteServerThread");
    }
 
    public QuoteServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
 
        try {
            in = new BufferedReader(new FileReader("one-liners.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file. Serving time instead.");
        }
    }
 
    public void run() {
 
        while (moreQuotes) {
        	byte[] buf = new byte[256];

            // receive request
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
				socket.receive(packet);
				socket.setSoTimeout(1000);
				String dString = null;
	            if (in == null)
	                dString = new Date().toString();
	            else
	                dString = getNextQuote();

	            buf = dString.getBytes("ISO-8859-1");

	            // send the response to the client at "address" and "port"
	            InetAddress address = packet.getAddress();
	            int port = packet.getPort();
	            packet = new DatagramPacket(buf, buf.length, address, port);
	            socket.send(packet);
				
            }catch(SocketTimeoutException e){
			   try {
				Thread.sleep((new Random()).nextInt(2001)+1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
 
    protected String getNextQuote() {
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
                returnValue = "No more quotes. Goodbye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }
}