import java.util.Random;

@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<? super T>> {

	public int maxLevel;
	public SkipListNode<T>[] root;
	private int[] powers;
	private Random rd = new Random();

	SkipList(int i) {
		maxLevel = i;
		root = new SkipListNode[maxLevel];
		powers = new int[maxLevel];
		for (int j = 0; j < i; j++)
			root[j] = null;
		choosePowers();
		rd.setSeed(202003); // do not modify this line
	}

	SkipList() {
		this(4);
	}

	public void choosePowers() {
		powers[maxLevel - 1] = (2 << (maxLevel - 1)) - 1;
		for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
			powers[i] = powers[i + 1] - (2 << j);
	}

	public int chooseLevel() {
		int i, r = Math.abs(rd.nextInt()) % powers[maxLevel - 1] + 1;
		for (i = 1; i < maxLevel; i++) {
			if (r < powers[i])
				return i - 1;
		}
		return i - 1;
	}

	public boolean isEmpty() {
		// your code goes here
		if(root[0] != null){
			return false;
		}
		return true;
	}

	public void insert(T key) {
		
		SkipListNode<T>[] curr = new SkipListNode[maxLevel];
		SkipListNode<T>[] prev = new SkipListNode[maxLevel];
		SkipListNode<T> newNode;
		int lvl, i;
		curr[maxLevel-1] = root[maxLevel-1];
		prev[maxLevel-1] = null;
		for (lvl = maxLevel - 1; lvl >= 0; lvl--) {
			while (curr[lvl] != null && curr[lvl].key.compareTo(key) < 0){
				prev[lvl] = curr[lvl];
				curr[lvl] = curr[lvl].next[lvl];
			}
			if (curr[lvl] != null && curr[lvl].key.compareTo(key) == 0) // don't include
				return;
			// duplicates;
			if (lvl > 0)
			// go one level down
				if (prev[lvl] == null) {
			// if not the lowest
				curr[lvl-1] = root[lvl-1]; // level, using a link
				prev[lvl-1] = null;
			// either from the root
			}
			else {
			// or from the predecessor;
				curr[lvl-1] = prev[lvl].next[lvl-1];
				prev[lvl-1] = prev[lvl];
			}
		}
		lvl = chooseLevel();
		newNode = new SkipListNode<T>(key,lvl+1);
		for (i = 0; i <= lvl; i++) {
			newNode.next[i] = curr[i];
			if (prev[i] == null)
				root[i] = newNode;
			else prev[i].next[i] = newNode;
		}	
	}

	public boolean delete(T key) {
		// your code goes here

		if(isEmpty()){
			return false;
		}
		else{
			int lvl;
			SkipListNode<T> curr;
			for (lvl = 0; lvl <=maxLevel-1 && root[lvl] == null; lvl++){
				curr = root[lvl];
				Boolean found = false;
				while (found != true) {
					if (key.compareTo(curr.next[lvl].key) == 0){
						curr.next[lvl] = curr.next[lvl].next[lvl];
						found = true;
					}	
				}
			}
			return true;
		}
		
	}

	public T first() {
		// your code goes here
		if(root[0]==null){
			return null;
		}
		else{
			return root[0].key;
		}
	}

	public T search(T key) {
		
		if(isEmpty()){
			return null;
		}
		else{
			int lvl;
			SkipListNode<T> prev, curr;
			for (lvl = maxLevel-1; lvl >= 0 && root[lvl] == null; lvl--);
			prev = curr = root[lvl];
			while (true) {
				if (key.compareTo(curr.key) == 0)
					return curr.key;
				else if (key.compareTo(curr.key) < 0) {
					if (lvl == 0)
						return null;
					else if (curr == root[lvl]){
						curr = root[--lvl];
					}
					else curr = prev.next[--lvl]; 
				}
				else {
					prev = curr;
					if (curr.next[lvl] != null){
						curr = curr.next[lvl];
					}
					else {
						for (lvl--; lvl >= 0 && curr.next[lvl] == null; lvl--);
						if (lvl >= 0)
							curr = curr.next[lvl];
						else return null;
					}
				}
			}
		}
	}
	
	public String getPathToLastNode() {
		// your code goes here
		SkipListNode<T>[] temp =  new SkipListNode[maxLevel];
		SkipListNode<T>[] pre = new SkipListNode[maxLevel];
		SkipListNode<T>[] last = new SkipListNode[1];
		last[0] = root[0];
		// int numOfNode = 0;
		while(last[0].next[0] != null){
			last[0] = last[0].next[0];
			// numOfNode++;
		}
		temp[maxLevel-1] = root[maxLevel-1];
		pre[maxLevel-1] = root[maxLevel-1];
		String t="";
		if(root[0].key != null){
			t = t + "[" +root[maxLevel-1].key.toString() + "]";
		}
		else{
			return "";
		}
		int lvl;
		SkipListNode<T> prev, curr;
		for (lvl = maxLevel-1; lvl >= 0 && root[lvl] == null; lvl--);
		prev = curr = root[lvl];
		while (true) {
			// System.out.println("t is: " + pre[0].key.toString());
			if (last[0].key == curr.key){
				// t = t + "[" + last[0].key.toString() + "]";
				// System.out.println("t is: " + last[0].key.toString());
				return t;
			}
			else if (last[0].key.compareTo(curr.key) < 0) {
				if (lvl == 0){
					t = t + "[" +curr.key.toString() + "]";
					return t;
				}
				else if (curr == root[lvl]){
					curr = root[--lvl];
				}
				else {
					curr = prev.next[--lvl];
					t = t + "[" +curr.key.toString() + "]"; 
				} 
			}
			else {
				prev = curr;
				if (curr.next[lvl] != null){
					curr = curr.next[lvl];
					t = t + "[" +curr.key.toString() + "]";
				}
				else {
					for (lvl--; lvl >= 0 && curr.next[lvl] == null; lvl--);
					if (lvl >= 0){
						curr = curr.next[lvl];
						t = t + "[" +curr.key.toString() + "]";
					}
					else return "";
				}
			}
		}
	}
}