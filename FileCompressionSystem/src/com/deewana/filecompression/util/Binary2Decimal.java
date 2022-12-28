package com.deewana.filecompression.util;

public class Binary2Decimal {
	// Function to convert binary to decimal
	public static int binaryToDecimal(String n) {
		String num = n;

		// Stores the decimal value
		int dec_value = 0;

		// Initializing base value to 1
		int base = 1;

		int len = num.length();
		for (int i = len - 1; i >= 0; i--) {

			// If the current bit is 1
			if (num.charAt(i) == '1')
				dec_value += base;
			base = base * 2;
		}

		// Return answer
		return dec_value;
	}
}
