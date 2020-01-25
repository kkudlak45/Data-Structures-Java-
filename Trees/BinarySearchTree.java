public class BinarySearchTree<E extends Comparable<E>> implements BinarySearchTreeInterface<E> {

	BinarySearchTree<E> parent;
	BinarySearchTree<E> left, right;
	E value;
	
	public BinarySearchTree() {
		parent = left = right = null;
		value = null;
	}
	
	@Override
	public void add(E item) {
		
		if (this.value == null && this.isRoot()) {
			this.value = item;
			return;
		}
		
		if (item.compareTo(this.value) < 0) {
			if (this.left != null) {
				this.left.add(item);
			} else {
				BinarySearchTree<E> addedNode = new BinarySearchTree<E>();
				addedNode.value = item;
				this.left = addedNode;
				addedNode.parent = this;
			}
		} else if (item.compareTo(this.value) > 0) {
			if (this.right != null) {
				this.right.add(item);
			} else {
				BinarySearchTree<E> addedNode = new BinarySearchTree<E>();
				addedNode.value = item;
				this.right = addedNode;
				addedNode.parent = this;
			}
		}
		
	}

	@Override
	public void remove(E item) {	
		BinarySearchTree<E> node = locate(item);
		
		if (node.isLeaf()) {
			if (node.parent.left == node) {
				node.parent.left = null;
			} else if (node.parent.right == node) {
				node.parent.right = null;
			}
		} else if (node.left != null && node.right == null) {
			node.left.parent = node.parent;
			
			if (node.parent.left == node) {
				node.parent.left = node.left;
			} else if (node.parent.right == node) {
				node.parent.right = node.left;
			}	
		} else if (node.left == null && node.right != null) {
			node.right.parent = node.parent;
			
			if (node.parent.left == node) {
				node.parent.left = node.right;
			} else if (node.parent.right == node) {
				node.parent.right = node.right;
			}	
		} else if (node.left != null && node.right != null) {
			BinarySearchTree<E> successor =  findSuccessor(node.right);
			node.value = successor.value;
			successor.parent = node;
			
			if (successor.parent.left == successor) {
				successor.parent.left = successor.right;
			} else if (successor.parent.right == successor) {
				successor.parent.right = successor.right;
			}	
		}
		
	}

	private BinarySearchTree<E> locate(E item) {
		if (item.compareTo(this.value) < 0) {
			if (this.left != null) {
				return this.left.locate(item);
			}
		} else if (item.compareTo(this.value) > 0) {
			if (this.right != null) {
				return this.right.locate(item);
			}
		} else {
			return this;
		}
		
		return null;
	}
	
	private BinarySearchTree<E> findSuccessor(BinarySearchTree<E> tree) {
		if (tree.left != null) {
			return findSuccessor(tree.left);
		}
		return tree;
	}
	
	@Override
	public boolean search(E item) {
		
		if (item.compareTo(this.value) < 0) {
			if (this.left != null) {
				return this.left.search(item);
			}
		} else if (item.compareTo(this.value) > 0) {
			if (this.right != null) {
				return this.right.search(item);
			}
		} else {
			return true;
		}
		
		return false;
	}
	
	private boolean isRoot() {
		return this.parent == null;
	}
	
	private boolean isLeaf() {
		return this.left == null && this.right == null;
	}
	
	private E treeMax() {
		if (this.right != null)
			return this.right.treeMax();
		return this.value;
	}
	
	
	static String draw;
	
	public String drawTree() {
		draw = "";
		drawTreeHelper(this, 0);
		return draw;
    }
    
    public void drawTreeHelper(BinarySearchTree<E> root, int level) {
    	if(root == null)
         	return;
    	
    	drawTreeHelper(root.right, level+1);
    	
    	if(level!=0) {
    		for(int i = 0; i < level-1; i++) {
            	draw += "|\t";           	
    		}
    		draw += "|-------"+root.value+"\n";
    	} else {
        	draw += root.value+"\n";
    	}
    	
    	drawTreeHelper(root.left, level+1);
    }	
	
    private void createString(BinarySearchTree<E> tree) {
		
    	if (tree.left != null) 
    		createString(tree.left);
    	draw += tree.value;
    	
    	if (tree.value.compareTo(this.treeMax()) < 0)
    		draw += ", ";
    	
    	if (tree.right != null)
    		createString(tree.right);
		
	}
    
	public String toString()
    {
		draw = "";
		createString(this);
		
		return "[" + draw + "]";
    }

	
}
