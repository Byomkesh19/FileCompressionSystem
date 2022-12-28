package com.deewana.filecompression.functional;

import java.util.Map;

public class DecompressorFunctional implements Decompressor {

	private static String fileContent;
	private static int additionalCharacters;

	public static String getFileContent() {
		return fileContent;
	}

	public static void setFileContent(String fileContent) {
		DecompressorFunctional.fileContent = fileContent;
	}

	public static int getAdditionalCharacters() {
		return additionalCharacters;
	}

	public static void setAdditionalCharacters(int additionalCharacters) {
		DecompressorFunctional.additionalCharacters = additionalCharacters;
	}

	protected char getCharacterValue(int i) {
		return fileContent.charAt(i);
	}

	/*
	 * Generating the binary string 
	 * Steps: 
	 * 1. Get each character one by one 
	 * 2. Generate the decimal code of every character 
	 * 3. Convert the decimal code in binary 
	 * 4. Make sure the binary string comprises of 7 digits, if not add leading zeroes 
	 * 5. Add the final binary String into the final
	 * generated CompleteBinary String
	 */
	protected String generateBinaryString(int mapSize) {
		String completeBinaryString = "";

		for (int i = mapSize+5; i < fileContent.length(); i++) {
			// Getting each character from the compressedCode String
			char ch = fileContent.charAt(i);

			// Generating the decimal equivalent of the character
			int decimalCode = ch;

			// Generating the binary equivalent of the decimal code
			String binaryString = Integer.toBinaryString(decimalCode);

			// Ensuring the binary string is having 7 character
			binaryString = String.format("%07d", Integer.parseInt(binaryString));

			completeBinaryString += binaryString;
		}

		// Taking the sub string of the binary string in order to remove the
		// additional characters from it which is added while compressing
		completeBinaryString = completeBinaryString.substring(additionalCharacters);

		return completeBinaryString;

	}

	/*
	 * Generating back the decompressed code using the dictionary 
	 * Steps: 
	 * 1. Fetching character one by one from the binary string 
	 * 2. check if there is any such charSet in dictionary 
	 * 3. if yes get the equivalent char and add in the decompressed code 
	 * 4. if no keep on adding the chars in the charSet
	 * 5. finally we will get the decompressed code
	 */
	protected String getDecompressedCode(String completeBinaryString, Map<String, Character> dictionary) {

		String charSet = "";
		String decompressedCode = "";
		for (int i = 0; i < completeBinaryString.length(); i++) {
			charSet += completeBinaryString.charAt(i);
			if (dictionary.containsKey(charSet)) {
				decompressedCode += dictionary.get(charSet);
				charSet = "";
			}
		}
		return decompressedCode;
	}

}
