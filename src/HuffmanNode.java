public class HuffmanNode implements Comparable<HuffmanNode> {
	// ASCII value
	public int value;
	// character frequency
	public int frequency;
	// left node
	public HuffmanNode left;
	// right node
	public HuffmanNode right;
	
	// leaf node constructor
	public HuffmanNode(int value, int frequency) {
		this.value = value;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}
	
	// node constructor
	public HuffmanNode(int value, int frequency, HuffmanNode left, HuffmanNode right) {
		this.value = value;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}
	
	// compare's frequency
	// positive if greater than, negative if less than
	public int compareTo(HuffmanNode node) {
		return this.frequency - node.frequency;
	}
	
}