package com.deewana.filecompression.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.deewana.filecompression.model.Node;
import com.deewana.filecompression.model.Pair;
import com.deewana.filecompression.util.Binary2Decimal;

public class CompressorFunctional implements Compressor {

	private static String fileContent;
	private static Map<Character, String> dictionaryCharToString = new HashMap<>();
	private static Map<String, Character> dictionaryStringToChar = new HashMap<>();

	private static Queue<Node> huffmanTree = new PriorityQueue<>();

	private static int additionalCharacters = 0;

	public static Map<String, Character> getDictionaryStringToChar() {
		return dictionaryStringToChar;
	}

	public static void setDictionaryStringToChar(Map<String, Character> dictionaryStringToChar) {
		CompressorFunctional.dictionaryStringToChar = dictionaryStringToChar;
	}

	public static String getFileContent() {
		return fileContent;
	}

	public static void setFileContent(String fileContent) {
		CompressorFunctional.fileContent = fileContent;
	}

	public static int getAdditionalCharacters() {
		return additionalCharacters;
	}

	public static void setAdditionalCharacters(int additionalCharacters) {
		CompressorFunctional.additionalCharacters = additionalCharacters;
	}

	/*
	 * Generating the compressed content from the intial file content 
	 * Steps: 
	 * 1. Convert each char to its binary code using huffman dicitonary 
	 * 2. Make the entire binary code a multiple of 7 if not already 
	 * 3. Finally generate the ASCII characters for 7 binary bits and adding it in the compressedCode
	 */
	protected String getCompressedContent() {
		String compressedContent = "";
		for (int i = 0; i < fileContent.length(); i++) {
			char ch = fileContent.charAt(i);
			String val = dictionaryCharToString.get(ch);
			compressedContent += val;
		}
		int l = compressedContent.length();

		String compressedContentInSevenMultiple = "";

		additionalCharacters = 7 - ((l) % 7);// The characters needed to add to
												// make the string a multiple of
												// seven

		// Adding the additional 0's at the start of the string
		for (int i = 0; i < additionalCharacters; i++) {
			compressedContentInSevenMultiple += '0';
		}

		// Adding the remaining of the string content
		compressedContentInSevenMultiple += compressedContent;

		String contentToWrite = "";

		/*
		 * Here all the characters are in 1 or 0 Took 7 characters at one time,
		 * since 7 bit number can maximum result in 127 so we can get the ASCII
		 * characters till 127 easily without any repition in storing
		 */
		// Generating the compressed content
		for (int i = 0; i < compressedContentInSevenMultiple.length(); i = i + 7) {
			// Taking seven characters at one time which are makes a 7bit binary
			// number
			String binaryNumber = compressedContentInSevenMultiple.substring(i, i + 7);

			// Converting binary to Decimal
			int decimalNumber = Binary2Decimal.binaryToDecimal(binaryNumber);

			// Generating the equivalent ASCII character for the decimal number
			char ch = (char) decimalNumber;

			// Adding the character to the content To Write content
			contentToWrite += ch;
		}

		return contentToWrite;
	}

	/*
	 * Generating the leaf node 
	 * Steps: 
	 * 1. Generate the leaf node 
	 * 2. Remove the leaf nodes with least frequency of the char occurence 2 at a time 
	 * 3. Merge the lead Nodes and add the merged node in the tree 
	 * 4. Repeat until we get only one node left in the tree 
	 * 5. The final node will be root node of the Huffman Tree
	 */
	protected void generateHuffmanTree(Map<Character, Integer> charMap) {
		/*
		 * Generating the leaf nodes for the Huffman tree
		 */
		for (Character ch : charMap.keySet()) {
			int freq = charMap.get(ch);
			Pair pair = new Pair(ch + "", freq);
			Node node = new Node();
			node.data = pair;
			node.left = null;
			node.right = null;
			huffmanTree.add(node);

		}

		/*
		 * Creating the nodes over the lead node by combining its values and removing the leaf nodes 
		 * Steps: 
		 * 1. Polling 2 nodes with least frequency(Priority Queue is used for achieving that) 
		 * 2. Merging their character content as well as adding their frequency 
		 * 3. Generating the merged node with the above details. 
		 * 4. Adding the merged node in PQ
		 * 5. Repeating the steps and finally got only one node left 
		 * 6. The left up Node is the Root Node of the Huffman tree
		 */
		while (huffmanTree.size() >= 2) {
			Node right = huffmanTree.poll();
			Node left = huffmanTree.poll();
			Node merged = new Node();
			String chars = left.data.getCharSet() + right.data.getCharSet();
			int tot = left.data.getFrequency() + right.data.getFrequency();
			Pair mergedPair = new Pair(chars, tot);
			merged.data = mergedPair;
			merged.left = left;
			merged.right = right;
			huffmanTree.add(merged);
		}
	}

	protected Node getRootNodeFromHuffmanTree() {
		return huffmanTree.peek();
	}

	public void traverse(Node node, String code) {

		// break condition
		if (node.left == null && node.right == null) {
			char ch = node.data.getCharSet().charAt(0);
			dictionaryStringToChar.put(code, ch);
			dictionaryCharToString.put(ch, code);
			return;
		}

		// recursion to the left and right and adding a 0 for moving left and 1
		// for moving right
		traverse(node.left, code + "0");
		traverse(node.right, code + "1");

	}

}
