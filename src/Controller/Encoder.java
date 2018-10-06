package Controller;

import Model.Reader;

public class Encoder {
	
	private String readed;
	private String path;
	private Reader read;
	
	public Encoder() {
		read = new Reader();
	}
	
	public boolean readFile(String path) {		
		return read.readFile(path);
	}
	
	
}
