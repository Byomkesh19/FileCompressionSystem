package com.deewana.filecompression.model;

public class Pair {

	String charSet;
	int frequency;

	public Pair(String charSet, int frequency) {
		this.charSet = charSet;
		this.frequency = frequency;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
