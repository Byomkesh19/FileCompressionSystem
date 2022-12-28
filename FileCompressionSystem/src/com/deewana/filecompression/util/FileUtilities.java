package com.deewana.filecompression.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtilities {

	public static String readFile(File file) {
		FileReader fr = null;

		// Content of the file
		String content = "";
		try {
			fr = new FileReader(file);
			int i = -1;

			// The loops breaks out when i=-1 that is when fr.read() encounters
			// the end of the file
			while ((i = fr.read()) != -1) {
				content += (char) i;
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;

	}

	public static void writeOnFile(File file, String contentToWrite) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);

			// Finally writing the compressed content on the file
			fw.write(contentToWrite);
			fw.flush();

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clearFile(File file) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);

			// To erase the initial content of the file.
			fw.write("");
			fw.flush();

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getFile() {
		String fullFilePath = FileInfo.filePath + File.separator + FileInfo.fileName + FileInfo.fileExtension;
		File file = new File(fullFilePath);
		return file;
	}

}
