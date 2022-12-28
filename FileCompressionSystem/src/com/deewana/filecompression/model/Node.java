package com.deewana.filecompression.model;

public class Node implements Comparable<Node> {
	public Node left;
	public Node right;
	public Pair data;

	@Override
	public int compareTo(Node o) {
		return this.data.frequency - o.data.frequency;
	}

}
