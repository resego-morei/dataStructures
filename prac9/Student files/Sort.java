import java.util.Arrays;
@SuppressWarnings("unchecked")
public class Sort {

	////// Implement the functions below this line //////

	/********** HEAP **********/
	public static <T extends Comparable<? super T>> void heapsort(T[] data, boolean debug) {
		// Your code here
		// floydAlgorithm(data);
		for (int i = data.length/2 - 1; i >= 0; --i){
			movedown(data,i,data.length-1,debug);
		}
		for (int i = data.length-1; i >= 1; --i) {
			swap(data,0,i);
			movedown(data,0,i-1,debug);
		}
	}

	public static <T extends Comparable<? super T>> void swap(T[] data,int i, int j){
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	public static <T extends Comparable<? super T>> void floydAlgorithm(T[] data){
		int i = (data.length-2)/2;
		while(i>=0){
			int p =i;
			int L = 2*p+1;
			int R = 2*p+2;
			int Largest = R;
			Boolean isLeaf = false;
			if(L<data.length-1 && R<data.length-1){
				isLeaf = false;
			}
			else isLeaf = true;
			if(data[L].compareTo(data[R]) >0 || R>data.length-1){
				Largest = L;
			}
			else Largest = R;
			while(!isLeaf && (data[p].compareTo(data[L])< 0 || data[p].compareTo(data[R])< 0)){
				T temp = data[p];
				data[p] = data[Largest];
				data[Largest] = temp;
			}
			i = i-1;
		}

	}

	private static <T extends Comparable<? super T>> void movedown(T[] data, int first, int last, boolean debug) {
		// Your code here
		int largest = 2*first + 1;
		while (largest <= last) {
		if (largest < last && data[largest].compareTo(data[largest+1]) < 0){
			largest++;
		}
		if ((data[first]).compareTo(data[largest]) < 0) {
			swap(data,first,largest);
			first = largest;
			largest = 2*first + 1;
		}
		else largest = last + 1;
		}
		// DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug) {
		// Your code here
		int mid;
		if(first<last){
			mid = (first+last)/2;
			mergesort(data, first, mid, debug);
			mergesort(data, mid+1, last, debug);
			merge(data, first, last, debug);
		}
	}

	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug) {
		// Your code here
		int mid = (first+last)/2;
		int it1 = 0;
		int it2 = first;
		int it3 = mid + 1;
		T[] temp = (T[])new Comparable [last-first+1];
		while(it2<=mid && it3<=last ){
			if(data[it2].compareTo(data[it3])< 0 ){
				temp[it1++] = data[it2++];
			}
			else{
				temp[it1++] = data[it3++];
			}
		}

		while(it2 <= mid) {
			temp[it1++] = data[it2++];
		}
		while(it3 <= last) {
			temp[it1++] = data[it3++];
		}
	
		// copy temp to original interval
		for(int i = first; i <= last; i ++) {
			data[i] = temp[i - first];
		}

		// DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}
}