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

		// Your code goes here
		insertN(key, root);
	}
	

	// Function to determine if a node with the given key exists.
	public boolean contains(String key) {

		// Your code goes here
		int x;
		char[] words = key.toCharArray();
		x = index(words[0]);
		int i = 0;
		if(x != -1)
		{
			boolean found = false;
			Node trav = root;
			
			while(i < words.length)
			{
				if(trav.ptrs[x] != null && trav.ptrs[x].isLeaf == false)
				{
					System.out.print("here");
					trav = trav.ptrs[x];
					x = index(words[trav.level - 1]);
					//if(x==-1){return false;}
				}
				if(trav.ptrs[x] != null && trav.ptrs[x].isLeaf == true)
				{
					if(trav.ptrs[x].key == key)
					{
						found = true;
					}
					else
					{
						found = false;
					}
				}	
				i = i + 1;			
			}
			return found;
		}
		else
		{
			return false;
		}
	}

	
	// Function to print all the keys in the trie in alphabetical order.
	public void printKeyList() {

		// Your code goes here
		int x = 0;
		String[] arr = {};
		int arrI = 0;
		recChar(root, x, arr, arrI);

		int n = 0;
		String temp;

		while(arr[n] != null)
		{
			n = n + 1;
		}

		for(int i = 0; i < n; i++)
		{
			for(int r = i + 1; r < n; r++)
			{
				if(arr[i].compareTo(arr[r]) > 0)
				{
					temp = arr[i];
					arr[i] = arr[r];
					arr[r] = temp;
				}
			}
		}

		for(int i = 0; i < n - 1; i++)
		{
			System.out.print(arr[i] + " ");
		}
		System.out.print(arr[n - 1]);
	}

	
	//Helper functions

	private void insertN(String key, Node node)
	{
		if(root != null) // IRON OUT CASE ON THE LEFT OF THE PICTURE!!!!!
		{
			char[] words = key.toCharArray();
			int x = index(words[0]);

			if(x != -1)
			{
				if(node.ptrs[x] == null) // check if it is not pointing to a leaf
				{
					Node newNode = new Node(key, this.numPtrs);
					node.ptrs[x] = newNode;
					node.ptrs[x].endOfWord = true;
					return;
				}
				else
				{
					if(node.ptrs[x].isLeaf == false)
					{
						Node trav = node;
						trav = node.ptrs[x];
						boolean done = false;
						
						while(done != true)
						{
							x = index(words[trav.level]);
							if(x != -1)
							{
								if(trav.ptrs[x] == null)
								{
									
									Node newNode = new Node(key, this.numPtrs);
									trav.ptrs[x] = newNode;
									trav.ptrs[x].endOfWord = true;
									done = true;
								}
								else if(trav.ptrs[x] != null && trav.ptrs[x].isLeaf == false)
								{
									trav = trav.ptrs[x];
								}
								else if(trav.ptrs[x] != null && trav.ptrs[x].isLeaf == true) // add the word that youve replaced with a node to the newNode
								{
									boolean did = false;
									int n = 0;
									//Node run = trav;
									Node store = trav.ptrs[x];
									char[] sKeySize = store.key.toCharArray();									
									Node newNode = new Node(this.numPtrs);
									trav.ptrs[x] = newNode;
									trav.ptrs[x].level = trav.level + 1;
									trav = trav.ptrs[x];
									while(did != true && n < letters.length)
									{
										if(trav.level > sKeySize.length || sKeySize[trav.level - 1] == letters[n]) // or trav.letters[trav.level - 1]
										{
											trav.ptrs[0] = store;
											trav.ptrs[0].endOfWord = true;
											did = true;
										}
										else if(sKeySize[trav.level - 1] == letters[n])
										{
											trav.ptrs[n] = store; //new Node(store.key, 0);
											trav.ptrs[n].endOfWord = true;
											did = true;
										}
										else
										{
											n = n + 1;
										}
									}	
			
									x = index(words[trav.level - 1]); 
									 
									Node newN = new Node(key, this.numPtrs);
									trav.ptrs[x] = newN;
									trav.ptrs[x].endOfWord = true;
									done = true;
									return;
								}
							}
							else
							{
								return;
							}
						}
					}
					else
					{
						boolean did = false;
						int i = 1;
						Node trav = node;
						Node store = trav.ptrs[x];
						char[] sKeySize = store.key.toCharArray();
						
						Node newNode = new Node(this.numPtrs);
						trav.ptrs[x] = newNode;
						trav.ptrs[x].level = trav.level + 1;
						trav = trav.ptrs[x];
						int m = trav.level;
						while(did != true)
						{
							if(sKeySize[m] == letters[i])///fix this
							{
								trav.ptrs[i] = new Node(store.key, this.numPtrs);
								trav.ptrs[i].endOfWord = true;
								did = true;
								break;
							}
							else
							{
								i = i + 1;
							}
						}
						
						x = index(words[trav.level - 1]); // if a letter is skipped it's here
						
						Node newN = new Node(key, this.numPtrs);
						trav.ptrs[x] = newN;
						trav.ptrs[x].endOfWord = true;
						return;
					}
				}
			}
			else
			{
				return;
			}
		}
		else
		{
			root = new Node(this.numPtrs);
			int x;
			char[] words = key.toCharArray();
			x = index(words[0]);
			root.ptrs[x] = new Node(key, this.numPtrs);
			root.ptrs[x].endOfWord = true;
			root.level = 1; 
		}
	}

	private String[] recChar(Node node, int x, String[] arr, int arrI)
	{
		if(node != null)
		{
			// void preOrder(BSTNode<T> head)
			// {
			// 	if(head != null)
			// 	{
			// 		System.out.print(head.element + " ");
			// 		preOrder(head.left);
			// 		preOrder(head.right);
			// 	}
			// 	else
			// 	{
					
			// 	}
			// }
			if(node.ptrs[x].isLeaf != true)
			{
				recChar(node.ptrs[x], x, arr, arrI);
			}
			else
			{
				if(node.ptrs[x].endOfWord == true)
				{
					arr[arrI] = node.ptrs[x].key;
					if(x + 1 < letters.length)
					{
						recChar(node, x + 1, arr, arrI + 1);
					}
				}
				else
				{
					if(x + 1 < letters.length)
					{
						recChar(node, x + 1, arr, arrI);
					}
				}
			}

			return arr;
		}
		else
		{
			return null;
		}
	}
}