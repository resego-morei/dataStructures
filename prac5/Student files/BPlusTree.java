/*
	You may not change the signatures of any of the given methods.  You may 
	however add any additional methods and/or field(s) which you may require to aid 
	you in the completion of this practical.
 */

public class BPlusTree {
	int order;
	int minKeys;
	int maxKeys;
	BPlusNode root; // do not modify

	public BPlusTree(int m) {
		/*62894133405
		The constructor.  Creates a BPlusTree object of order m,
		where m is passed as a parameter to the constructor. 
		You may assume that m >= 3.
		*/
		order = m;
		minKeys = (int) Math.ceil(m / 2.0) - 1;
		maxKeys = order - 1;
		root = new BPlusNode(m, true); /* root starts as leaf node and root's parent is null */
	}

	/* insert an element into the BPlusTree, you may assume duplicates will not be inserted. */
	public void insertElement(int element) {
		// your code goes here
		if (root.keyTally == 0){	// root = new BPlusNode(order, true);  // Create new root
        	root.keys[0] = element;  // Insert key
        	root.keyTally = 1;  // Update number of keys in root
    	}
		
    	else {		// If the tree is not empty
        	// root = root.insert(key);
			BPlusNode temp = root;
			BPlusNode parent= null;
			while(temp.leaf ==false){
				parent = temp;
				for(int i=0; i<temp.keyTally; i++){
					if(element < temp.keys[i]){
						temp = temp.branches[i];
						break;
					}
					if(i == temp.keyTally-1){
						temp = temp.branches[i+1];
						break;
					}
				}
			}
			if(temp.keyTally < maxKeys){
				int count =0;
				while(element > temp.keys[count] && count < temp.keyTally){
					count++;
				}
				for(int i = temp.keyTally; i> count; i--){
					temp.keys[i] = temp.keys[i-1];
				}
				temp.keys[count] = element;
				temp.keyTally = temp.keyTally + 1;
				temp.branches[temp.keyTally] = temp.branches[temp.keyTally-1];
				temp.branches[temp.keyTally -1] = null;
			}
			else{
				BPlusNode newNode = new BPlusNode(order, true);
				int[] tempNode =new int[maxKeys+1];
				for(int i=0; i<maxKeys; i++){
					tempNode[i] = temp.keys[i];
				}
				int count1=0;
				while(element > tempNode[count1] && count1 < maxKeys){
					count1++;
				}
				for(int i=maxKeys+1; i>element;i--){
					tempNode[i] = tempNode[i-1];
				}
				tempNode[count1] = element;
				temp.keyTally =(maxKeys+1)/2;
				newNode.keyTally = maxKeys + 1 -temp.keyTally;
				temp.branches[temp.keyTally] = newNode;
				newNode.branches[newNode.keyTally] = temp.branches[maxKeys];
				temp.branches[maxKeys] = null;
				for (count1 = 0; count1 < temp.keyTally; count1++) {
                	temp.keys[count1]= tempNode[count1];
            	}
				if (temp == root) {
  
					// Create a new Node
					BPlusNode newRoot = new BPlusNode(order,false);
	  
					// Update rest field of
					// B+ Tree Node
					newRoot.keys[0] = newNode.keys[0];
					newRoot.branches[0] = temp;
					newRoot.branches[1] = newNode;
					newRoot.keyTally = 1;
					root = newRoot;
				}
				else{
					insideInsert(parent, newNode, newNode.keys[0]);
				}
			}
    	}
	}

	public void insideInsert(BPlusNode temp,BPlusNode tempNode, int element){
		if(temp.keyTally < maxKeys){
			int count1 =0;
			while(tempNode.keys[0] > temp.keys[count1] && tempNode.keys[0]< temp.keyTally){
				count1++;
			}
			for(int j = temp.keyTally; j > count1; j--) { 
				temp.keys[j]= temp.keys[j - 1];
			}
			for (int j = temp.keyTally + 1; j > count1 + 1; j--){
            	temp.branches[j]= temp.branches[j - 1];
        	}
			temp.keys[count1] = element;
			temp.keyTally++;
			temp.branches[count1 + 1] = tempNode;
		}
		else{
			BPlusNode newInternal = new BPlusNode(order,false);
			int[] newKeys= new int[maxKeys + 1];
			BPlusNode[] newBranches = new BPlusNode[maxKeys + 2];
			for(int i = 0; i < maxKeys; i++) {
				newKeys[i] = temp.keys[i];
			}
			for(int i = 0; i < maxKeys + 1; i++) {
				newBranches[i] = temp.branches[i];
			}
			int count1 =0;
			while (element > newKeys[count1] && count1 < maxKeys) {
            	count1++;
        	}
			for (int j = maxKeys + 1;j > count1; j--) {
            newKeys[j]= newKeys[j - 1];
        	}
  			newKeys[count1] = element;
			for (int j = maxKeys + 2; j > count1 + 1; j--) {
				newBranches[j]= newBranches[j - 1];
			}
			newBranches[count1 + 1] = tempNode;
			temp.keyTally= (maxKeys + 1) / 2;
  
        	newInternal.keyTally= maxKeys-(maxKeys + 1)/2;
			for (int i = 0, j = temp.keyTally + 1;i < newInternal.keyTally; i++, j++){
  				newInternal.keys[i] = newKeys[j];
				newInternal.branches[i] = newBranches[j];
       		}
			if(temp == root){
  
				// Create a new root node
				BPlusNode newRoot = new BPlusNode(order,false);
	  
				// Update key value
				newRoot.keys[0] = temp.keys[temp.keyTally];
	  
				// Update rest field of
				// B+ Tree Node
				newRoot.branches[0] = temp;
				newRoot.branches[1] = newInternal;
				newRoot.keyTally = 1;
				root = newRoot;
			}
			else {
				insideInsert(findParent(root,temp),newInternal,temp.keys[temp.keyTally]);
			}
		}
	}
	/*  
	    This method should return the left-most leaf node in the tree.
		If the tree is empty, return null.
	 */
	public BPlusNode getFirstLeaf() {
		// your code goes here
		return null;
	}

	public BPlusNode findParent(BPlusNode cursor, BPlusNode child){
		BPlusNode  parent=null;
	
		// If cursor reaches the end of Tree
		if (cursor.leaf || (cursor.branches[0]).leaf) {
			return null;
		}
	
		// Traverse the current node with
		// all its child
		for (int i = 0; i < cursor.keyTally + 1; i++) {
	
			// Update the parent for the
			// child Node
			if (cursor.branches[i] == child) {
				parent = cursor;
				return parent;
			}
	
			// Else recursively traverse to
			// find child node
			else {
				parent = findParent(cursor.branches[i], child);
	
				// If parent is found, then
				// return that parent node
				if (parent != null)
					return parent;
			}
		}
	
		// Return parent node
		return parent;
	}
}
