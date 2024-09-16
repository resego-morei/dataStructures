/**
 * Name: Thabo Maduna
 * Student Number:U19116498
 */

public class SplayTree<T extends Comparable<T>> {

	protected enum SplayType {
		SPLAY,
		SEMISPLAY,
		NONE
	}	

	protected Node<T> root = null;
	
	/**
	 * Prints out all the elements in the tree
	 * @param verbose
	 *			If false, the method simply prints out the element of each node in the tree
	 *			If true, then the output provides additional detail about each of the nodes.
	 */
	public void printTree(boolean verbose) {
		String result;
		result = preorder(root, verbose);
		System.out.println(result);
	}
	
	protected String preorder(Node<T> node, boolean verbose) {
		if (node != null) {
			String result = "";
			if (verbose) {
				result += node.toString()+"\n";
			} else {
				result += node.elem.toString() + " ";
			}
			result += preorder(node.left, verbose);
			result += preorder(node.right, verbose);
			return result;
		}
		return "";
	}
	
	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	/**
	* Inserts the given element into the tree, but only if it is not already in the tree.
	* @param elem 
	* 		 	The element to be inserted into the tree
	* @return 
	*			Returns true if the element was successfully inserted into the tree. 
	*			Returns false if elem is already in the tree and no insertion took place.
	*
	*/
	public boolean insert(T elem) {

		//Your code goes here
		if(!contains(elem)){
			Node<T> p = root, prev = null;
			while (p != null){ 
				prev = p;
				if (p.elem.compareTo(elem) < 0)
					p = p.right;
				else p = p.left;
			}
			if (root == null){
				root = new Node<T>(elem);
				return true;
			}
			else if (prev.elem.compareTo(elem) < 0){
				prev.right = new Node<T>(elem);
				return true;
			}
			else{
				prev.left = new Node<T>(elem);
				return true;
			}
		}
		else{
			return false;
		}
	}
	
	/**
	* Checks whether a given element is already in the tree.
	* @param elem 
	* 		 	The element being searched for in the tree
	* @return 
	*			Returns true if the element is already in the tree
	*			Returns false if elem is not in the tree
	*
	*/
	public boolean contains(T elem) {
		Node<T> temp = root;
		while (temp != null)
		if (elem.compareTo(temp.elem) == 0){
			return true;
		}
		else if (elem.compareTo(temp.elem) < 0){
			temp = temp.left;
		}
		else{
			temp = temp.right;
		}
		return false;
	}
	
	/**
	 * Accesses the node containing elem. 
	 * If no such node exists, the node should be inserted into the tree.
	 * If the element is already in the tree, the tree should either be semi-splayed so that 
	 * the accessed node moves up and the parent of that node becomes the new root or be splayed 
	 * so that the accessed node becomes the new root.
	 * @param elem
	 *			The element being accessed
	 * @param type
	 *			The adjustment type (splay or semi-splay or none)
	 */
	public void access(T elem, SplayType type) {

		//Your code goes here
		if(contains(elem)==true){
			if(type == SplayType.SEMISPLAY){
				Node<T> node =new Node<T>(elem);
				// semisplay(node);
			}
			else if(type == SplayType.SPLAY){
				Node<T> node =new Node<T>(elem);  
				splay(node);
			}
		}
		else{
			insert(elem);
		}
	}
	
	/**
	 * Semi-splays the tree using the parent-to-root strategy
	 * @param node
	 *			The node the parent of which will be the new root
	 */
	protected void semisplay(Node<T> p) {
		
		//Your code goes here
		while (p != root) {
			if(findParent(root,findParent(root, p.elem).elem) == null){ 
				
				if (findParent(root, p.elem).left == p){
					rightRotate(p);
				}
				else leftRotate(p);
			}
			else if (findParent(root, p.elem) == p){
				// if (((SplayTreeNode)p.parent).parent.left == p.parent)
				if(findParent(root,findParent(root, p.elem).elem).left.elem ==findParent(root, p.elem)){
					rightRotate(findParent(root, p.elem));
					p = findParent(root, p.elem);
				}
				else {
					rightRotate(p); // rotate p and its parent;
					leftRotate(p); // rotate p and its neparent;
				}
			}
		
			else{ // if p is a right child;
				// if (((SplayTreeNode)p.parent).parent.right == findParent(root, p.elem)) {
				if(findParent(root, findParent(root, p.elem).elem).right == findParent(root, p.elem)){
					leftRotate(findParent(root, p.elem));
					p = findParent(root, p.elem);
				}
				else {
					leftRotate(p); // rotate p and its parent;
					rightRotate(p); // rotate p and its new
				} // parent;
				if (root == null){ // update the root;
					root = p;
				}
			}
		}
	}

	/**
	 * Splays the tree using the node-to-root strategy
	 * @param node
	 *			The node which will be the new root
	 */
	protected void splay(Node<T> node) {
		root = splay(root, node.elem);
	}

	//Helper functions

	public boolean iterate(T elem, Node<T> node){
		if(node.elem.compareTo(elem)==0){
			return true;
		}
		else if(node.elem.compareTo(elem)>0){
			iterate(elem, node.right);
		}
		else{
			iterate(elem, node.left);
		}
		return false;
	}

	public Node<T> rightRotate(Node<T> node){
		Node<T> y = node.left;
		node.left = y.right;
		y.right = node;
		return y;		
	}

	public Node<T> leftRotate(Node<T> node){
		Node<T> y = node.right;
		node.right = y.left;
		y.left = node;
		return y;
	}
	
	public Node<T> splay(Node<T> node, T elem){
		if (node == null || node.elem.compareTo(elem) == 0){
			return node;
		}
		if (node.elem.compareTo(elem) > 0){
			if (node.left == null){ 
				return node;
			}
			if (node.left.elem.compareTo(elem) > 0){
				node.left.left = splay(node.left.left, elem);
				node = rightRotate(node);
			}
			else if (node.elem.compareTo(elem) < 0){
				node.left.right = splay(node.left.right, elem);
				if (node.left.right != null){
					node.left = leftRotate(node.left);
				}
			}
			if(node.left == null){
				return node;
			}
			else{
				rightRotate(node);
			}
		}
		else{
			if (node.right == null){
				return node;
			}
			if (node.right.elem.compareTo(elem) > 0){
				node.right.left = splay(node.right.left, elem);
				if (node.right.left != null){
					node.right = rightRotate(node.right);
				}
			}
			else if (node.right.elem.compareTo(elem) < 0){
				node.right.right = splay(node.right.right, elem);
				node = leftRotate(node);
			}

			if(node.right == null){
				return node;
			}
			else leftRotate(node);
		}
		return node;
	}
	
	public Node<T> findParent(Node<T> node,T elem){
		if(node == null || node == root){
			return null;
		}
		else{
			if(node.left.elem==elem || node.right.elem==elem){
				return node;
			}
			else{
				if(elem.compareTo(node.elem)>0){
					return findParent(node.right, elem);
				}
				else{
					return findParent(node.left, elem);
				}
			}
		}
	}
	
}