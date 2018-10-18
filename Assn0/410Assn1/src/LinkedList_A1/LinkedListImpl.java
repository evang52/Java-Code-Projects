/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
 */
/*
	  Interface: The LIST will provide this collection of operations:

	  clear:
	    in: nothing
	    return: void
	    effect: list is left with size 0, no elements in it,
	            consists of just the original root Node
	  size:
	    in: nothing
	    return: number of elements stored in the list
	    effect: no change to list state
	  isEmpty:
	    in: nothing
	    return: boolean, true if the list has no elements in it, true is size is 0
	    effect: no change to list state
	  insert
	    in: a double (the data element), and an int (position index)
	    return: boolean, return true if insert is successful, false otherwise
	    effect: the list state will be altered so that the element is located at the
	            specified index; the list has size bigger by one; all elements that were
	            at the specified index or after have been moved down one slot
	    error: if index is beyond list size range return false
	           valid inserts take place either at a location where a list element
	           already exists, or at the location that is one beyond the last element
	  remove
	    in: an int (the index of the element to take out of the list)
	    return: boolean.. return true if the remove is successful, false otherwise
	    effect: list state is altered in that the Node at the specified index is decoupled
	            list size decreases by one
	    errors: what if specified index is not in the list? return false
	  get
	    in: an int, the index of the element item to return
	    return: double, the element stored at index, or Double.NaN
	    effect: no change in state of the list
	    error: what if index is not in the list? return Double.NaN
 */
package LinkedList_A1;


public class LinkedListImpl implements LIST_Interface {
	Node sentinel;//this will be the entry point to your linked list (the head)
	int size;
	public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
		sentinel=new Node(0); //Note that the root's data is not a true part of your data set!
		size =0;
	}
	public void clear() {
		sentinel.next=null;
		sentinel.prev = null;
		size =0;
	}
	//implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!

	public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
		return sentinel;
	}

	public int size() {
		return size;
	}
	public boolean isEmpty() {
		if(size ==0)
			return true;
		else 
			return false;
	}
	public boolean insert(double elt, int index) {	
		if(index < 0 || index > size)
			return false;
		
		Node newb = new Node(elt);
		if(size ==0) {
			sentinel.prev = newb;
			sentinel.next = newb;	
			newb.prev = sentinel;
			newb.next = sentinel;
			
		}else {
			Node nodeNext = nodeLoc(index);
			Node nodePrev = nodeLoc(index -1);
			newb.next = nodeNext;
			newb.prev = nodePrev;
			nodePrev.next = newb;
			nodeNext.prev = newb;
		}
		size++;
		return true;
	}
	
	public Node nodeLoc(int ind) {
		Node inc = sentinel;
		for(int i =0; i< ind +1; i++) {
			inc = inc.next;
		}
		return inc;
	}
	
	public boolean remove(int index) {
		if(index < 0 || index > size || size == 0)
			return false;
		Node curr = sentinel;
		for (int j=1; j <= index; j++) {
			if(curr.getNext() == null)
				return false;
			
			curr = curr.next;
		}
		curr.next = curr.getNext().getNext();
		curr.next.prev = curr.prev;
		size--;
		return true;
		
		
	}
	public double get(int index) {
		if(index < 0 || index > size || size == 0)
			return Double.NaN;
		
		Node curr = sentinel.next;
		for(int l =0; l< index; l++) {
			if(curr.next == null)
				return Double.NaN;
			
			curr = curr.next;
		}
		return curr.getData();
	}
}