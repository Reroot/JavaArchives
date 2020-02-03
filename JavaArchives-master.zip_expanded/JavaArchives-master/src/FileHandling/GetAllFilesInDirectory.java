package FileHandling;

import java.io.File;

public class GetAllFilesInDirectory {
	public static void main(String[] args) {
		String filePath = "./src";
		if (args.length > 0) {
			filePath = args[0];
		}
		File curFile = new File(filePath);
		File[] files = curFile.listFiles();
		for (File file: files) {
			System.out.println(file.getPath());
			System.out.println(file.length());

		}
	}
}
