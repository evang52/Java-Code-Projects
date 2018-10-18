package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data){ this.data=data; }

	// --- used for testing  ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData(){ return data; }
	public BST_Node getLeft(){ return left; }
	public BST_Node getRight(){ return right; }

	// --- end used for testing -------------------------------------------


	// --- fill in these methods ------------------------------------------
	//
	// at the moment, they are stubs returning false 
	// or some appropriate "fake" value
	//
	// you make them work properly
	// add the meat of correct implementation logic to them

	// you MAY change the signatures if you wish...
	// make the take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations


	public boolean containsNode(String s){ 
		boolean ans = false;
		int det =0;
		BST_Node ite = this;
		while(det!=0) {
			if(ite == null)
				return ans;
			String comp = ite.data;
			det = s.compareTo(comp);
			if(det <0 )
				ite = ite.getRight();
			if(det>0)
				ite = ite.getLeft();
		}
		ans = true;
		return ans;
	}
	public boolean insertNode(String s, BST tree){
		if(s == null)
			return false;
		return tree.insert(s);
		/*
		if(tree.size() ==0) {
			tree.root = new BST_Node(s);
			tree.incSize();
			return true;
		}
		BST_Node curr = tree.getRoot();
		BST_Node par = null;
		boolean go = true;
		while(go) {
			par = curr;
			if(s.compareTo(curr.data) <0 ) {
				curr = curr.left;
				if(curr == null) {
					par.left = new BST_Node(s);
					go = false;
					tree.incSize();
					return true;
				}
			}else {
				curr = curr.right;
				if(curr ==null) {
					par.right = new BST_Node(s);
					go = false;
					tree.incSize();
					return true;
				}
			}
		}
		return false;
		 */
	}

	public boolean removeNode(String s, BST tree){
		BST_Node par = tree.getRoot(), curr = tree.getRoot();
		boolean isLeft = false;
		while(!curr.data.equals(s)) {
			par = curr;
			if(s.compareTo(curr.data)<0) {
				isLeft = true;
				curr = curr.left;
			}else {
				isLeft = false;
				curr = curr.right;
			}
			if(curr == null)
				return false;

		}
		//Case 1, node to be removed is a leaf
		if(curr.left == null && curr.right == null) {
			if(curr==tree.getRoot()) {
				tree.setRoot(null);
			}
			if(isLeft) {
				par.left = null;
			}else {
				par.right = null;
			}


		}
		//case 2: node TBR has one child
		else if(curr.right == null) {
			if(curr == tree.getRoot()) {
				tree.setRoot(curr.left);
			}else if(isLeft){
				par.left = curr.left;

			}else {
				par.right = curr.left;
			}
		}
		else if(curr.left==null) {
			if(curr == tree.getRoot()) {
				tree.setRoot(curr.right);
			}else if(isLeft) {
				par.left = curr.right;
			}else {
				par.right = curr.right;
			}
		}else if(curr.left != null && curr.right != null) {
			BST_Node prev = tree.prev(curr);
			if(curr==tree.getRoot()) {
				tree.setRoot(prev);
			}else if(isLeft) {
				par.left=prev;
			}else {
				par.right = prev;
			}
			prev.left = curr.left;
		}
		tree.size--;
		return true;
	}



	public String findMin(){ 
		if(left == null) 
			return data;
		else
			return left.findMin();
	}
	public String findMax(){ 
		if(right ==null)
			return data;
		else
			return right.findMax();

	}
	public int getHeight(){ 
		int size =0,temp = 0;
		BST_Node next = right;
		BST_Node next1 = left;
		if(next != null) {

			while(next.getRight() != null ) {
				next = next.getRight();
				size++;

			}
		}
		if(next1 != null) {
			while(next1.getLeft() != null) {
				next1 = next1.getLeft();
				temp++;
			}
		}
		if(size > temp)
			return size; 

		return temp+1;
	}


	// --- end fill in these methods --------------------------------------


	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------

	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
				+",Right: "+((this.right!=null)?right.data:"null");
	}
}