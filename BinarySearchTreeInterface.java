
public interface BinarySearchTreeInterface<E extends Comparable<E>> {

	public void add(E item);
	
	public void remove(E item);
	
	public boolean search(E item);
	
	public String toString();

}
