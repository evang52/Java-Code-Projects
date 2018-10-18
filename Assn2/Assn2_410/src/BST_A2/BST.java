package BST_A2;
/*
Interface: The BST will provide this collection of operations:

insert:
  in: a string (the element to be stored into the tree)
  return: boolean, return true if insert is successful, false otherwise
  effect: if the string is already in the tree, then there is no change to
            the tree state, and return false
          if the string is not already in the tree, then a new tree cell/node
            is created, the string put into it as data, the new node is linked
            into the tree at the proper place; size is incremented by 1,
            and return a true
remove:
  in: a string (the element to be taken out of the tree)
  return: boolean, return true if the remove is successful, false otherwise
          this means return false if the tree size is 0
  effect: if the element being looked for is in the tree, unlink the node containing
            it and return true (success); size decreases by one
          if the element being looked for is not in the tree, return false and
            make no change to the tree state
contains:
  in: a string (the element to be seaarched for)
  return: boolean, return true if the string being looked for is in the tree;
          return false otherwise
          this means return false if tree size is 0
  effect: no change to the state of the tree

findMin:
  in: none
  return: string, the element value from the tree that is smallest
  effect: no tree state change
  error: is tree size is 0, return null


findMax:
  in: none
  return: string, the element value from the tree that is largest
  effect: no tree state change
  error: is tree size is 0, return null

size:
  in: nothing
  return: number of elements stored in the tree
  effect: no change to tree state

empty:
  in: nothing
  return: boolean, true if the tree has no elements in it, true if size is 0
          return false otherwise
  effect: no change to tree state

height:
  in: none
  return: integer, the length of the longest path in the tree from root to a leaf
  effect: no change in tree state
  error: return -1 is tree is empty (size is 0, root is null)

getRoot:
  in: none
  return: a tree cell/node, the one that is the root of the entire tree
          means return a null if the tree is empty
  effect: no change to tree state

 */
public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST(){ 
		size=0; 
		root=null; 
	}
	public void setRoot( BST_Node val) {
		root = val;
	}
	public void incSize() {
		size++;
	}
	

	@Override
	//used for testing, please leave as is
	public BST_Node getRoot(){ return root; }

	@Override
	public boolean insert(String s) {
		if(s == null)
			return false;
			//return root.insertNode(s, this);
			
		if(size() ==0) {
			root = new BST_Node(s);
			incSize();
			return true;
		}
		BST_Node curr = getRoot();
		BST_Node par = null;
		boolean go = true;
		while(go) {
			par = curr;
			if(s.compareTo(curr.data) <0 ) {
				curr = curr.left;
				if(curr == null) {
					par.left = new BST_Node(s);
					go = false;
					incSize();
					return true;
				}
			}else {
				curr = curr.right;
				if(curr ==null) {
					par.right = new BST_Node(s);
					go = false;
					incSize();
					return true;
				}
			}
		}
		return false;
		
	}

	@Override
	public boolean remove(String s) {
		return root.removeNode(s, this);
		/*
		BST_Node par = root, curr = root;
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
			if(curr==root) {
				root = null;
			}
			if(isLeft) {
				par.left = null;
			}else {
				par.right = null;
			}


		}
		//case 2: node TBR has one child
		else if(curr.right == null) {
			if(curr == root) {
				root=curr.left;
			}else if(isLeft){
				par.left = curr.left;
				
			}else {
				par.right = curr.left;
			}
		}
		else if(curr.left==null) {
			if(curr == root) {
				root = curr.right;
			}else if(isLeft) {
				par.left = curr.right;
			}else {
				par.right = curr.right;
			}
		}else if(curr.left != null && curr.right != null) {
			BST_Node prev = prev(curr);
			if(curr==root) {
				root = prev;
			}else if(isLeft) {
				par.left=prev;
			}else {
				par.right = prev;
			}
			prev.left = curr.left;
		}
		size--;
		return true;
		*/
	}

	public BST_Node prev(BST_Node n) {
		BST_Node p = root;
		int det =0;
		BST_Node ite = root;
		while(det!=0) {
			if(ite == null)
				break;
			String comp = ite.data;
			det = n.data.compareTo(comp);
			if(det <0 )
				p = ite;
			ite = ite.getRight();
			if(det>0)
				p = ite;
			ite = ite.getLeft();
		}
		return p;

	}
	@Override

	public String findMin() {
		if(size ==0) 
			return null;
		else
			return root.findMin();
	}


	@Override
	public String findMax() {
		if(size ==0) 
			return null;
		else
			return root.findMax();
	}

	@Override
	public boolean empty() {
		if(size ==0)
			return true;

		return false;
	}

	@Override
	//remodeled to fit new understanding of BST, USE THIS AS PARADIGM
	public boolean contains(String s) {
		boolean ans = false;
		if(size ==0 || root == null)
			return ans;
		int det =0;
		BST_Node ite = root;
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


	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		return root.getHeight();
	}

}
