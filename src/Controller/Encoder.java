package Controller;

import Model.Encrypt;
import Model.Reader;
import Model.Writer;

public class Encoder implements Runnable{
	
	private String path;
	private Reader read;
	private Writer write;
	private Encrypt enc;
	private String key;
	private byte[] encrypted;
	private boolean success;
	public int actual;
	
	public Encoder() {
		read = new Reader();
		write = new Writer();
		enc = new Encrypt();
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public boolean readFile(String path) {		
		this.path = path;
		return read.readFile(path);
	}
	
	public void run() {
		int keyIndex = 0;
		encrypted = new byte[read.input.length];
		int index = 0;
		for(byte b : read.input) {			
			encrypted[index++] = enc.encryptByte(b, (byte)key.charAt(keyIndex));
			actual = index;
			if(keyIndex < key.length()-1) {
				keyIndex++;
			} else {
				keyIndex = 0;
			}
		}
	}
	
	public boolean save(boolean isEncoded) {
		if(isEncoded) {
			return write.writeData(encrypted, path.substring(0, path.indexOf(".bin")));
		} else {
			return write.writeData(encrypted, path + ".bin");
		}
		
	}
	
	public int getSize() {
		return read.input.length;
	}
	
	public boolean hasInput() {
		return this.path != null;
	}
}
