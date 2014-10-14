
import java.io.*;
import java.net.*;
import java.util.ArrayList;
 
public class QuoteClient {
	
	
    public static void main(String[] args) throws IOException {
    	BufferedReader in = null;
    	ArrayList<Profile> profiles = new ArrayList<Profile>();
    	String profile;
    	
    	try {
            in = new BufferedReader(new FileReader("addresses.txt"));
        
    	
	    	while((profile = in.readLine()) != null){
	    		profiles.add(new Profile(profile));
	    	}
	    	for (int j = 0; j < profiles.size(); j++) {
	        	System.out.print("Address: "+ profiles.get(j).getAddress());
	        	System.out.println(" Name: " + profiles.get(j).getName());
			}
	    	in.close();
	    	while(true){
	    		for(int i=0; i < profiles.size(); i++){
		    		Thread quoteClient = new Thread(new QuoteClientThread(profiles.get(i).getAddress()));
		    		quoteClient.start();
	    		}
	    		try {
					Thread.sleep(5*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	}
    	catch (FileNotFoundException e) {
            System.err.println("Could not open address file. Program Exiting");
        }
    }
    
   
}