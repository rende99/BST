package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST() {
		size = 0;
		root = null;
	}

	public BST_Node getRoot() {
		return root;
	}

	public boolean insert(String s) {
		/*
		 * Multiple options: 1. size is 0, create new BST. 2. string is already in the
		 * existing tree, return false, no change to the tree. 3. string isn't already
		 * in the existing tree, so a new tree cell/node is created, the string put into
		 * it as data, the new node is linked into the tree at the proper place; size is
		 * incremented by 1, and return true.
		 */
		if (size == 0) {
			root = new BST_Node(s);
			size++;
			return true;
		} else {
			if (!contains(s)) {
				size++;
				return root.insertNode(s);
			}
		}
		return false;
	}

	public boolean remove(String s) {
		/*
		 * return true if remove is successful, return false if BST is size 0 OR the
		 * element is not in the tree. To remove: unlink the node containing it and
		 * return true (success); size decreases by one.
		 */
		if (size == 0 || !contains(s))
			return false;
		if(root.getData().compareTo(s) == 0) {
			//removing the root:
			size--;
			if(root.right != null) {
				root.data = root.right.findMin().data;
				return root.right.removeNode(root.right.findMin().data);				
			}else if (root.left != null) {
				root.data = root.left.findMax().data;
				return root.left.removeNode(root.left.findMax().data);	
			}else {
				root = null;
				return true;
			}

		}
		size--;
		return root.removeNode(s);
	}

	public String findMin() {
		/*
		 * return the string in the tree that has smallest value. If BST size is 0,
		 * return null.
		 */
		if (size == 0)
			return null;
		return (root.findMin()).getData();
	}

	public String findMax() {
		/*
		 * return the string in the tree that has largest value. If BST size is 0,
		 * return null.
		 */
		if (size == 0)
			return null;
		return (root.findMax()).getData();
	}

	public boolean empty() {
		return (size == 0) ? true : false;
	}

	public boolean contains(String s) {
		/*
		 * return true if the String s is in the BST somewhere. return false if BST size
		 * = 0 OR String s is not in the BST.
		 */
		if (size == 0)
			return false;
		return root.containsNode(s);
	}

	public int size() {
		return size;
	}

	public int height() {
		/*
		 * return an integer, the length of the longest path in the tree from root to a
		 * leaf. return -1 if the BST is empty (size=0,root=null)
		 */
		if(size == 0 || root == null) return -1;
		return root.getHeight();
	}

}