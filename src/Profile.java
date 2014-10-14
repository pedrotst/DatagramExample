
public class Profile {
	private String address, name;
	
	public Profile(String line){
		
		this.address = line.substring(0,line.indexOf(' '));
		this.name = line.substring(line.indexOf(' ')+1, line.length());
		
	}

	public String getAddress() {
		return address;
	}
	
	public String getName() {
		return name;
	}

	
	
	
}
