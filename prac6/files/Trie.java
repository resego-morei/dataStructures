public class Trie {	
	protected char[] letters;
	protected Node root = null;
	private int numPtrs;

	public Trie(char[] letters) {
		this.letters = letters;
		this.numPtrs = letters.length + 1;
	}


	//Provided Helper functions
	
	private int index(char c) {
		for (int k = 0; k < letters.length; k++) {
			if (letters[k] == (c)) {
				return k+1;
			}
		}
		return -1;
	}
	
	private char character(int i) {
		if (i == 0) {
			return '#';
		} else {
			return letters[i-1];
		}
	}
	
	private String nodeToString(Node node, boolean debug) {
		if (node.isLeaf) {
			return node.key;
		}
		else {
			String res = "";
			for (int k = 0; k < node.ptrs.length; k++) {
				if (node.ptrs[k] != null) {
					res += " (" + character(k) + ",1) ";
				} else if (debug) {
					res += " (" + character(k) + ",0) ";
				}
			}
			return res;
		}
	}

	public void print(boolean debug) {
		Queue queue = new Queue();
		Node n = root;
		if (n != null) {
			n.level = 1;
			queue.enq(n);
			while (!queue.isEmpty()){
				n = queue.deq();
				System.out.print("Level " + n.level + " [");
				System.out.print(nodeToString(n, debug));
				System.out.println("]");
				for (int k = 0; k < n.ptrs.length; k++) {
					if (n.ptrs[k] != null) {
						n.ptrs[k].level = n.level+1;
						queue.enq(n.ptrs[k]);
					}
				}
			}
		}
	}


	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	
	// Function to insert the given key into the trie at the correct position.
	public void insert(String key) {
		if(root == null){
			root = new Node(this.numPtrs);
			int pos = index(key.charAt(0));
			root.level++;
			root.ptrs[pos] = new Node(key,numPtrs);
			root.ptrs[pos].endOfWord = true;
		}
		else{
			Node tmp = root;
			int pos = index(key.charAt(0));
			if(tmp.ptrs[pos]==null){
				Node newNode = new Node(key,numPtrs);
				tmp.ptrs[pos] = newNode;
				tmp.ptrs[pos].endOfWord = true;
			}
			else{
			// 	Node old = tmp;
			// 	Node alreadyIn = old.ptrs[pos];
			// 	Node newNode = new Node(numPtrs);
			// 	old.ptrs[pos] = newNode;
			// 	old.ptrs[pos].level = old.level++;
			// 	old = old.ptrs[pos];
			// 	int oldLevel = old.level;
			// 	int i = 0;
			// 	while(true)
			// 	{
			// 		if(alreadyIn.key.charAt(oldLevel) == letters[i]){
			// 			old.ptrs[i] = new Node(alreadyIn.key, this.numPtrs);
			// 			old.ptrs[i].endOfWord = true;
			// 			break;
			// 		}
			// 		else
			// 		{
			// 			i++;
			// 		}
				// }
				
				// pos = index(key.charAt(old.level - 1));
				// Node newN = new Node(key, this.numPtrs);
				// old.ptrs[pos] = newN;
				// old.ptrs[pos].endOfWord = true;
				// return;
			}
		}
        // for (int i=0; i < key.length(); i++){
        //     pos = index(key.charAt(i));

        //     if(temp.ptrs[pos] == null){
        //         temp.ptrs[pos] = new Node(numPtrs);
		// 	}
        //     temp = temp.ptrs[pos];
        // }
        // temp.endOfWord = true;
	}
	// Function to determine if a node with the given key exists.
	public boolean contains(String key) {

		// Your code goes here
		return true;
	}

	
	// Function to print all the keys in the trie in alphabetical order.
	public void printKeyList() {

		// Your code goes here

	}

	
	//Helper functions

}