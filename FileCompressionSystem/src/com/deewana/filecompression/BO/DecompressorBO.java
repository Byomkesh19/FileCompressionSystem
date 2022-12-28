package com.deewana.filecompression.BO;

import java.io.File;
import java.util.Map;

import com.deewana.filecompression.functional.DecompressorFunctional;
import com.deewana.filecompression.util.FileUtilities;
import com.deewana.filecompression.util.MapUtilities;

public class DecompressorBO extends DecompressorFunctional {

	public void decompress(File file) {

		// Getting the compressed content in the file
		String fileContent = FileUtilities.readFile(file);
		DecompressorFunctional.setFileContent(fileContent);

		// Getting the additional characters added
		int additionalCharacters = Character.getNumericValue(getCharacterValue(0));
		DecompressorFunctional.setAdditionalCharacters(additionalCharacters);

		// Size of hashMap written, in number of chars
		String sizeOfMapString = "";
		for(int i=1;i<=4;i++){
			sizeOfMapString+=getCharacterValue(i);
		}
		int sizeOfMap=Integer.parseInt(sizeOfMapString);
		// Getting the map written over the file
		Map<String, Character> dictionary = MapUtilities.getDictionary(sizeOfMap,
				DecompressorFunctional.getFileContent());
		
		// generating the binary String from the file from the unique ascii
		// chars stored
		String completeBinaryString = generateBinaryString(sizeOfMap);

		// Decompressing the code using the dictionary
		String decompressedCode = getDecompressedCode(completeBinaryString, dictionary);
		
		FileUtilities.clearFile(file);

		FileUtilities.writeOnFile(file, decompressedCode);

	}
}
