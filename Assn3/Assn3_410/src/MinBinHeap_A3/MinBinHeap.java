package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
	private static final int d = 2;
	//be null. This is ok! Just build out 
	//from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
		//of child/parent computations...
		//the book/animation page both do this.
	}

	//Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		/*	int i=0;
		while(array[i]!= null) {
			i++;
		}
		array[i]= entry;
			size++;
		 */
		//TODO: find out what is wrong with the insert method, check HeapUp against txt
		if(!( size ==array.length-1)) {
			size++;
			array[size] = entry;
			heapUp(size);
		}
		//			EntryPair n = entry;
		//			int pr =parent(size-1);
		//			int no = n.priority;
		//			EntryPair temp;
		//			while(pr!= 0 && entry.priority < array[pr].priority ) {
		//				temp = array[no];
		//				array[no] = array[pr];
		//				array[pr] = temp;
		//				pr = parent(no);

		//		}

		//}
	}
	//percolation
	private void heapUp(int childIdx) {
		int parind;
		EntryPair temp; 
		if(childIdx !=0) {
			parind = parent(childIdx);
			if(array[parind].priority > array[childIdx].priority) {
				temp = array[parind];
				array[parind]= array[childIdx];
				array[childIdx] = temp;
				if( parind != 1)
					heapUp(parind);
			}
		}

	}
	private void heapDown(int idx) {
		int LCIdx, RCIdx, mIdx;
		EntryPair temp;
		LCIdx = yThChild(idx, 0);
		RCIdx = yThChild(idx, 1);
		mIdx =LCIdx;
		if(RCIdx >size) {
			if(LCIdx > size)
				return;
			else
				mIdx = LCIdx;
		}else {
			if(array[LCIdx].priority< array[RCIdx].priority)
				mIdx = LCIdx;
			else
				mIdx = RCIdx;
		}
		if(array[idx].priority > array[mIdx].priority) {
			temp = array[mIdx];
			array[mIdx] = array[idx];
			array[idx] = temp;
			heapDown(mIdx);
		}
		//		int child;
		//		EntryPair temp = array[idx];
		//		while(yThChild(idx, 1)< size() ) {
		//			child = getMinChild(idx);
		//			if(array[child].priority < temp.priority)
		//				array[idx] = array[child];
		//			else
		//				break;
		//			idx = child;
		//		}
		//		array[idx] = temp;
	}
	//methods to return the indices of parents and children
	public int parent(int i) {
		
		return (int)i/d;
	}
	public int yThChild(int i, int y) {
		return d* i +y;
	}
//	private int getMinChild(int idx) {
//		int minC = yThChild(idx, 1);
//		int dub = 2;
//		int pos = yThChild(idx,2);
//		while(dub <=d && pos < size) {
//			if(array[pos].priority < array[minC].priority) 
//				minC = pos;
//			pos = yThChild(idx, dub++);
//		}
//		return minC;
//	}

	@Override
	public void delMin() {
		if(!(size()==0)) {
			array[1] = array[size];
			size--;
			if(size >0)
				heapDown(1);
		}

	}


	@Override
	public EntryPair getMin() {
		if(size()==0)
			return null;
		else 
			return array[1];
		//		
		//		int temp = array[1].getPriority();
		//		
		//		EntryPair ret =null;
		//		for(int i=0;i<size;i++) {
		//			if(array[i].getPriority()<temp) {
		//				ret= array[i];
		//				temp = ret.getPriority();
		//			}
		//		}
		//		return ret;

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		if(!(entries.length== 0)) {
			for(int i =1;i<entries.length;i++) {
				array[i] = entries[i-1];
				size++;
			}
		}
		//i=size /2 because this will bubble down on every node that isn't a leaf
		for(int i=size /2;i>0; i--) {
			heapDown(i);
		}
	}
}