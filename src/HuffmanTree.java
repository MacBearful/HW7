// temp code
import java.util.*;
import java.io.*;
public class HuffmanTree {
	// saves the root
	HuffmanNode overallRoot;

	// the character's ASCII value is the index and its frequency is the array value
	public HuffmanTree(int[] count) {
		Queue<HuffmanNode> nodes = new PriorityQueue<HuffmanNode>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0) {
				nodes.add(new HuffmanNode(i, count[i]));
			}
		}
		nodes.add(new HuffmanNode(count.length, 1));
		build(nodes);
		// overall root is now the final node of the node queue
		overallRoot = nodes.remove();
	}

	public HuffmanTree(Scanner input) {
		overallRoot = new HuffmanNode(-1, 0);
		while (input.hasNextLine()) {
			int value = Integer.parseInt(input.nextLine());
			String code = input.nextLine();
			overallRoot = build(overallRoot, value, code);
		}
	}

	private HuffmanNode build(HuffmanNode root, int value, String code) {
		if (!code.substring(1).isEmpty()) {
			if (code.charAt(0) == '0') {
				if (root.left == null) {
					root.left = new HuffmanNode(-1, 0);
				}
				build(root.left, value, code.substring(1));
			} else {
				if (root.right == null) {
					root.right = new HuffmanNode(-1, 0);
				}
				build(root.right, value, code.substring(1));
			}
		} else {
			if (code.charAt(0) == '0') {
				root.left = new HuffmanNode(value, 0);
			} else {
				root.right = new HuffmanNode(value, 0);
			}
		}
		return root;
	}

	// uses the queue to assemble the tree
	private void build(Queue<HuffmanNode> nodeList) {
		// goes until the final node is produced
		if (nodeList.size() > 1) {
			// take the nodes out in order of frequency (the smallest frequencies come out first)
			// firstNode will be on the left and secondNode will be on the right
			HuffmanNode firstNode = nodeList.remove();
			HuffmanNode secondNode = nodeList.remove();
			// links them together with a temporary root, which will be readded to the list
			HuffmanNode tempRoot = new HuffmanNode(-1, firstNode.frequency + secondNode.frequency, firstNode, secondNode);
			// this node has the combined frequencies of these two values
			nodeList.add(tempRoot);
			// iterates through the node list once this change has been made
			build(nodeList);
		}
	}
	
	// Writes the current tree to a code file in Standard Format
	// s is the code (when initializing make it "")
	// root is the starting point (when initializing make it overallRoot)
	public void write(HuffmanNode root, String s, PrintStream output) {
		if (root != null) {
			// at roots, where the value is -1, it keeps going until there is a value
			if (root.value != -1) {
				// this prints the final ASCII value and then its code
				output.println(root.value);
				output.println(s);
			} else {
				// the code is written so that left is 0 and right is 1
				write(root.left, s + "0", output);
				write(root.right, s + "1", output);
			}
		}
	}

	public void decode(BitInputStream input, PrintStream output, int eof) {
		HuffmanNode root = overallRoot;
		while (root.value != eof) {
			if (root.left == null && root.right == null) {
				output.write(root.value);
				root = overallRoot;
			} else {
				int n = input.readBit();
				if (n == 0) {
					root = root.left;
				} else {
					root = root.right;
				}
			}
		}
	}
}
