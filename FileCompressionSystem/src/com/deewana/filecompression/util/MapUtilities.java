package com.deewana.filecompression.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtilities {

	public static Map<Character, Integer> generateCharacterFrequencyHashMap(String fileContent) {
		// Generating the unique characters with its frequency in charMap
		Map<Character, Integer> charMap = new HashMap<>();
		for (int i = 0; i < fileContent.length(); i++) {
			char ch = fileContent.charAt(i);
			if (charMap.containsKey(ch)) {
				charMap.replace(ch, charMap.get(ch) + 1);
			} else {
				charMap.put(ch, 1);
			}
		}
		return charMap;
	}

	public static String generateStringContentFromMap(Map<String, Character> map) {
		String mapContent = "";
		int sizeOfMap = 0;

		for (String str : map.keySet()) {
			String key = str;
			Character value = map.get(str);


			String stringValue = String.valueOf(value);
			if (value == '1') {
				stringValue = "ab";
			} else if (value == '0') {
				stringValue = "aa";
			}
			sizeOfMap += key.length() + stringValue.length();

			mapContent += key + stringValue;
		}
		String sizeOfMapString = String.valueOf(sizeOfMap);
		sizeOfMapString = String.format("%04d", Integer.parseInt(sizeOfMapString));
		String contentToWrite = sizeOfMapString + mapContent;

		return contentToWrite;
	}

	public static Map<String, Character> getDictionary(int mapSize, String content) {
		String binaryKey = "";
		Map<String, Character> dictionary = new HashMap<>();
		for (int i = 5; i < mapSize + 5; i++) {
			char chValue = content.charAt(i);
			if (chValue == '1' || chValue == '0') {
				binaryKey += chValue;
			} else {
				if (chValue == 'a' && i < (mapSize + 4)) {
					if (content.charAt(i + 1) == 'a') {
						dictionary.put(binaryKey, '0');
						binaryKey = "";
						continue;
					} else if (content.charAt(i + 1) == 'b') {
						dictionary.put(binaryKey, '1');
						binaryKey = "";
						continue;
					}
				}
				dictionary.put(binaryKey, chValue);
				binaryKey = "";
			}
		}
		return dictionary;
	}

}
