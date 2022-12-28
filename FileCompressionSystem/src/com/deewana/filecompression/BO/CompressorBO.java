package com.deewana.filecompression.BO;

import java.io.File;
import java.util.Map;

import com.deewana.filecompression.functional.CompressorFunctional;
import com.deewana.filecompression.model.Node;
import com.deewana.filecompression.util.FileUtilities;
import com.deewana.filecompression.util.MapUtilities;

public class CompressorBO extends CompressorFunctional {

	public void compress(File file) {

		// Getting the content of the file
		String fileContent = FileUtilities.readFile(file);
		CompressorFunctional.setFileContent(fileContent);
		
		Map<Character, Integer> charMap = MapUtilities.generateCharacterFrequencyHashMap(fileContent);

		// compressing logic using Huffman algorithm

		generateHuffmanTree(charMap);

		Node rootNode = getRootNodeFromHuffmanTree();

		// Traversing the tree starting from the root node and assigning the
		// chars a binary string
		traverse(rootNode, "");// After the method the dictionary used for
								// compressing is filled with all the values

		// Generate the compressed content to be written in file
		String contentToWrite = getCompressedContent();

		FileUtilities.clearFile(file);

		// Writing the extra characters added in the compressedCode first
		int additionalCharacters = getAdditionalCharacters();
		String additionalCharactersString = String.valueOf(additionalCharacters);
		FileUtilities.writeOnFile(file, additionalCharactersString);

		// Generating the map content to write in a file and writing on the file
		Map<String, Character> dictionaryStringToChar = getDictionaryStringToChar();
		String mapContentToWrite = MapUtilities.generateStringContentFromMap(dictionaryStringToChar);
		FileUtilities.writeOnFile(file, mapContentToWrite);

		// Writing the compressed content on the file.
		FileUtilities.writeOnFile(file, contentToWrite);

	}

}
