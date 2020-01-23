public class SkipListSet implements Set {

Node StartingPoint;
	
	public class Node {
		Node left, right, up, down;
		String value;
		
		public Node() {
			left = right = up = down = null;
			value = null;
		}
		
		private boolean isBottom() { return down == null; }
		private boolean hasAbove() { return up   != null; }
		private boolean isDummy()  { return left == null; }
	}
	
	public SkipListSet() {
		StartingPoint = new Node();
		Node make = new Node();
		StartingPoint.down = make;
		make.up = StartingPoint;
	}
	
	public void add(String element) {
		Node curr = StartingPoint;
		
		while (!curr.isBottom()) {
			curr = curr.down;
			while (curr.right != null && element.compareTo(curr.right.value) > 0)
				curr = curr.right;
			if (curr.right != null && element.compareTo(curr.right.value) == 0)
				return;
		}
		
		Node make = new Node();
		make.value = element;
		join(make, curr);
		
		while (coinFlip()) {
			Node above = new Node();
			above.value = element;
			curr.right.up = above;
			above.down = curr.right;
			
			while (!curr.hasAbove() && !curr.isDummy())
				curr = curr.left;
			
			if (curr.isDummy() && curr.up.right == null) {
				Node newDummy = new Node();
				newDummy.up = curr.up;
				newDummy.down = curr;
				curr.up.down = newDummy;
				curr.up = newDummy;
			}
			
			curr = curr.up;
			join(above, curr);		
		
		}
		
	}
	
	public boolean contains(String element) {
		Node curr = StartingPoint;
		
		while (!curr.isBottom()) {
			curr = curr.down;
			while (curr.right != null && element.compareTo(curr.right.value) >= 0)
				curr = curr.right;
			if (curr.value != null && element.compareTo(curr.value) == 0)
				return true;
		}
		
		return false;
	}
	
	private boolean coinFlip() {
		return Math.random() > 0.5;
	}
	
	private void join(Node n1, Node n2) {
		n1.left = n2;
		n1.right = n2.right;
		if (n2.right != null)
			n2.right.left = n1;
		n2.right = n1;
	}
	
	public String toString() {
		String s = "";
		
		Node currHead = StartingPoint;
		
		while (!currHead.isBottom()) {
			currHead = currHead.down;
			Node curr = currHead;
			s += "[";
			while (curr.right != null) {
				curr = curr.right;
				s = s + curr.value;
				if (curr.right != null)
					s += ", ";
			}
			s += "]\n";
		}
		
		return s;
	}
	
}
