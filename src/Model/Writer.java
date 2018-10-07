package Model;

import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {

	public boolean writeData(byte[] input, String path) {
		
		try (FileOutputStream fos = new FileOutputStream(path)) {
			   fos.write(input);
			   //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
}
