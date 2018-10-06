package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader {
	
	public byte[] input;
	
	
	public boolean readFile(String path) {
		try {
			input = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
