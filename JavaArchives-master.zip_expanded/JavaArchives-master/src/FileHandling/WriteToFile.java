package FileHandling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
	
	public static void main(final String[] args) throws IOException {
		final BufferedWriter writer = new BufferedWriter(new FileWriter("./src/One/append.txt", true));
		writer.append("another one");
		writer.close();
	}
}