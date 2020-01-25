import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface SimpleList<E> extends Iterable<E>{

	boolean add(E arg0);

	void add(int arg0, E arg1);

	boolean addAll(SimpleList<? extends E> arg0);

	boolean addAll(int arg0, SimpleList<? extends E> arg1);

	void clear();

	boolean contains(Object arg0);

	boolean containsAll(SimpleList<?> arg0);

	E get(int arg0);

	int indexOf(Object arg0);

	boolean isEmpty();

	Iterator<E> iterator();

	int lastIndexOf(Object arg0);

	boolean remove(Object arg0);

	E remove(int arg0);

	boolean removeAll(SimpleList<?> arg0);

	boolean retainAll(SimpleList<?> arg0);

	E set(int arg0, E arg1);

	int size();

	SimpleList<E> subList(int arg0, int arg1);

	Object[] toArray();


}
