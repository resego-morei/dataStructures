public class Main {

	private static void printRecursive(BPlusNode nodeToPrint, int depth) {
		if (nodeToPrint != null) {
			System.out.println("Level " + depth + " " + nodeToPrint);
			for (int i = 0; i < nodeToPrint.keyTally + 1; i++) {
				printRecursive(nodeToPrint.branches[i], depth + 1);
			}
		}
	}

	private static void printTree(BPlusTree tree) {
		printRecursive(tree.root, 1);
	}

	private static void insertElementIntoTree(BPlusTree tree, int elementToInsert) {
		System.out.println("------INSERT " + elementToInsert + "---------");
		tree.insertElement(elementToInsert);
		printTree(tree);
		System.out.println();
	}

	private static void printOrderFromLeaves(BPlusTree tree) {
		if (tree != null) {
			System.out.println("Printing in ascending order using leaf nodes: ");
			BPlusNode leaf = tree.getFirstLeaf();
			while (leaf != null) {
				System.out.println(leaf);
				leaf = leaf.next;
			}
		}
	}

	public static void main(String args[]) {

		 System.out.println("TREE with ODD order");
		 BPlusTree tree = new BPlusTree(5);
		 insertElementIntoTree(tree, 1);
		 insertElementIntoTree(tree, 2);
		 insertElementIntoTree(tree, 4);
		 insertElementIntoTree(tree, 3);
		 insertElementIntoTree(tree, 7);
		 insertElementIntoTree(tree, 10);
		 insertElementIntoTree(tree, 5);
		 insertElementIntoTree(tree, 6);
		 insertElementIntoTree(tree, 50);
		 insertElementIntoTree(tree, 60);
		 insertElementIntoTree(tree, 12);
		 insertElementIntoTree(tree, 15);
		 insertElementIntoTree(tree, 22);
		 insertElementIntoTree(tree, 23);
		 insertElementIntoTree(tree, 17);
		 insertElementIntoTree(tree, 88);
		 insertElementIntoTree(tree, 56);
		 insertElementIntoTree(tree, 73);
		 insertElementIntoTree(tree, 25);
		 insertElementIntoTree(tree, 99);
		 insertElementIntoTree(tree, 105);
		 insertElementIntoTree(tree, 24);
		 insertElementIntoTree(tree, 14);
		 insertElementIntoTree(tree, 106);
		 insertElementIntoTree(tree, 200);
		 insertElementIntoTree(tree, 300);
		
		 printOrderFromLeaves(tree);

		/* Expected output
		TREE with ODD order
		------INSERT 1---------
		Level 1 [1]
		
		------INSERT 2---------
		Level 1 [1 | 2]
		
		------INSERT 4---------
		Level 1 [1 | 2 | 4]
		
		------INSERT 3---------
		Level 1 [1 | 2 | 3 | 4]
		
		------INSERT 7---------
		Level 1 [3]
		Level 2 [1 | 2]
		Level 2 [3 | 4 | 7]
		
		------INSERT 10---------
		Level 1 [3]
		Level 2 [1 | 2]
		Level 2 [3 | 4 | 7 | 10]
		
		------INSERT 5---------
		Level 1 [3 | 5]
		Level 2 [1 | 2]
		Level 2 [3 | 4]
		Level 2 [5 | 7 | 10]
		
		------INSERT 6---------
		Level 1 [3 | 5]
		Level 2 [1 | 2]
		Level 2 [3 | 4]
		Level 2 [5 | 6 | 7 | 10]
		
		------INSERT 50---------
		Level 1 [3 | 5 | 7]
		Level 2 [1 | 2]
		Level 2 [3 | 4]
		Level 2 [5 | 6]
		Level 2 [7 | 10 | 50]
		
		------INSERT 60---------
		Level 1 [3 | 5 | 7]
		Level 2 [1 | 2]
		Level 2 [3 | 4]
		Level 2 [5 | 6]
		Level 2 [7 | 10 | 50 | 60]
		
		------INSERT 12---------
		Level 1 [3 | 5 | 7 | 12]
		Level 2 [1 | 2]
		Level 2 [3 | 4]
		Level 2 [5 | 6]
		Level 2 [7 | 10]
		Level 2 [12 | 50 | 60]
		
		------INSERT 15---------
		Level 1 [3 | 5 | 7 | 12]
		Level 2 [1 | 2]
		Level 2 [3 | 4]
		Level 2 [5 | 6]
		Level 2 [7 | 10]
		Level 2 [12 | 15 | 50 | 60]
		
		------INSERT 22---------
		Level 1 [7]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22]
		Level 3 [7 | 10]
		Level 3 [12 | 15]
		Level 3 [22 | 50 | 60]
		
		------INSERT 23---------
		Level 1 [7]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22]
		Level 3 [7 | 10]
		Level 3 [12 | 15]
		Level 3 [22 | 23 | 50 | 60]
		
		------INSERT 17---------
		Level 1 [7]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22]
		Level 3 [7 | 10]
		Level 3 [12 | 15 | 17]
		Level 3 [22 | 23 | 50 | 60]
		
		------INSERT 88---------
		Level 1 [7]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22 | 50]
		Level 3 [7 | 10]
		Level 3 [12 | 15 | 17]
		Level 3 [22 | 23]
		Level 3 [50 | 60 | 88]
		
		------INSERT 56---------
		Level 1 [7]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22 | 50]
		Level 3 [7 | 10]
		Level 3 [12 | 15 | 17]
		Level 3 [22 | 23]
		Level 3 [50 | 56 | 60 | 88]
		
		------INSERT 73---------
		Level 1 [7]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22 | 50 | 60]
		Level 3 [7 | 10]
		Level 3 [12 | 15 | 17]
		Level 3 [22 | 23]
		Level 3 [50 | 56]
		Level 3 [60 | 73 | 88]
		
		------INSERT 25---------
		Level 1 [7]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22 | 50 | 60]
		Level 3 [7 | 10]
		Level 3 [12 | 15 | 17]
		Level 3 [22 | 23 | 25]
		Level 3 [50 | 56]
		Level 3 [60 | 73 | 88]
		
		------INSERT 99---------
		Level 1 [7]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22 | 50 | 60]
		Level 3 [7 | 10]
		Level 3 [12 | 15 | 17]
		Level 3 [22 | 23 | 25]
		Level 3 [50 | 56]
		Level 3 [60 | 73 | 88 | 99]
		
		------INSERT 105---------
		Level 1 [7 | 50]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22]
		Level 3 [7 | 10]
		Level 3 [12 | 15 | 17]
		Level 3 [22 | 23 | 25]
		Level 2 [60 | 88]
		Level 3 [50 | 56]
		Level 3 [60 | 73]
		Level 3 [88 | 99 | 105]
		
		------INSERT 24---------
		Level 1 [7 | 50]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22]
		Level 3 [7 | 10]
		Level 3 [12 | 15 | 17]
		Level 3 [22 | 23 | 24 | 25]
		Level 2 [60 | 88]
		Level 3 [50 | 56]
		Level 3 [60 | 73]
		Level 3 [88 | 99 | 105]
		
		------INSERT 14---------
		Level 1 [7 | 50]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22]
		Level 3 [7 | 10]
		Level 3 [12 | 14 | 15 | 17]
		Level 3 [22 | 23 | 24 | 25]
		Level 2 [60 | 88]
		Level 3 [50 | 56]
		Level 3 [60 | 73]
		Level 3 [88 | 99 | 105]
		
		------INSERT 106---------
		Level 1 [7 | 50]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22]
		Level 3 [7 | 10]
		Level 3 [12 | 14 | 15 | 17]
		Level 3 [22 | 23 | 24 | 25]
		Level 2 [60 | 88]
		Level 3 [50 | 56]
		Level 3 [60 | 73]
		Level 3 [88 | 99 | 105 | 106]
		
		------INSERT 200---------
		Level 1 [7 | 50]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22]
		Level 3 [7 | 10]
		Level 3 [12 | 14 | 15 | 17]
		Level 3 [22 | 23 | 24 | 25]
		Level 2 [60 | 88 | 105]
		Level 3 [50 | 56]
		Level 3 [60 | 73]
		Level 3 [88 | 99]
		Level 3 [105 | 106 | 200]
		
		------INSERT 300---------
		Level 1 [7 | 50]
		Level 2 [3 | 5]
		Level 3 [1 | 2]
		Level 3 [3 | 4]
		Level 3 [5 | 6]
		Level 2 [12 | 22]
		Level 3 [7 | 10]
		Level 3 [12 | 14 | 15 | 17]
		Level 3 [22 | 23 | 24 | 25]
		Level 2 [60 | 88 | 105]
		Level 3 [50 | 56]
		Level 3 [60 | 73]
		Level 3 [88 | 99]
		Level 3 [105 | 106 | 200 | 300]
		
		Printing in ascending order using leaf nodes: 
		[1 | 2]
		[3 | 4]
		[5 | 6]
		[7 | 10]
		[12 | 14 | 15 | 17]
		[22 | 23 | 24 | 25]
		[50 | 56]
		[60 | 73]
		[88 | 99]
		[105 | 106 | 200 | 300]

		*/
	}
}
