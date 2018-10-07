package Model;

public class Encrypt {
	
	private byte key;
	
	public byte encryptByte(byte input, byte key) {
		return (byte) ((byte)input ^ key);
	}
	
	

}
