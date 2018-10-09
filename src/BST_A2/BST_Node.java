package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	public boolean containsNode(String s) {
		if (s.compareTo(this.data) == 0)
			return true;
		else if (s.compareTo(this.data) > 0) {
			if (this.right == null)
				return false;
			else
				return this.right.containsNode(s);
		} else {
			if (this.left == null)
				return false;
			else
				return this.left.containsNode(s);
		}
	}

	public boolean insertNode(String s) {
		if (s.compareTo(this.data) < 0) {
			if (this.left == null) {
				this.left = new BST_Node(s);
				return true;
			} else
				return this.left.insertNode(s);
		} else {
			if (this.right == null) {
				this.right = new BST_Node(s);
				return true;
			} else
				return this.right.insertNode(s);
		}

	}

	public boolean removeNode(String s) {

		/*
		 * 3 scenarios: If s is on a leaf, one child, or >=2 children.
		 */

		if (this.left != null && s.compareTo(this.left.data) == 0) {
			// the one to be removed is to the left of the current node
			if (this.left.left == null && this.left.right == null) {
				// it's a leaf
				this.left = null;
				return true;
			} else if (!(this.left.right != null && this.left.left != null)) {
				// one child
				if (this.left.right != null) {
					this.left = this.left.right;
					return true;
				} else {
					this.left = this.left.left;
					return true;
				}

			} else {
				// =2 children

				BST_Node minRight = this.left.right.findMin();
				this.left.data = minRight.data;
				if (this.left.right.left == null && this.left.right.right == null) {
					// nothing else below
					this.left.right = null;
					return true;
				}
				return this.left.right.removeNode(minRight.data);
			}
		} else if (this.right != null && s.compareTo(this.right.data) == 0) {
			// the one to be removed is to the right of the current node
			if (this.right.right == null && this.right.left == null) {
				// it's a leaf
				this.right = null;
				return true;
			} else if (!(this.right.left != null && this.right.right != null)) {
				// one child
				if (this.right.left != null) {
					this.right = this.right.left;
					return true;
				} else {
					this.right = this.right.right;
					return true;
				}

			} else {
				// =2 children

				BST_Node minRight = this.right.right.findMin();
				this.right.data = minRight.data;
				if (this.right.right.left == null && this.right.right.right == null) {
					// nothing else below
					this.right.right = null;
					return true;
				}
				return this.right.right.removeNode(minRight.data);
			}
		}
		if (this.right == null && this.left == null)
			return false; // didn't find it.

		if (s.compareTo(this.data) > 0) {
			// not around here. look deeper.
			return this.right.removeNode(s);
		} else
			return this.left.removeNode(s);

	}

	public BST_Node findMin() {
		if (this.left == null)
			return this;
		this.left.findMin();
		return left;
	}

	public BST_Node findMax() {
		if (this.right == null)
			return this;
		this.right.findMax();
		return right;
	}

	public int getHeight() {
		int Lheight = 0;
		int Rheight = 0;

		// base case:
		if (this.left == null && this.right == null) {
			return -1;
		}

		if (this.left != null) {
			Lheight = this.left.getHeight();
		}
		if (this.right != null) {
			Rheight = this.right.getHeight();
		}

		return Math.max(Lheight + 1, Rheight + 1);

	}

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}
}