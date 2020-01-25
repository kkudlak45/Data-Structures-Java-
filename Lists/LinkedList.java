import java.util.Iterator;
import java.util.ListIterator;

public class LinkedList<E> implements SimpleList<E> {
	
	private int size;
	private Node dummyHead;
	
	LinkedList() {
		size = 0;
		dummyHead = new Node();
	}
	
	private class Node {
		Node next;
		Object value;
		
		Node() {
			next = null;
			value = null;
		}
	}
	
	private Node locate(int index) {
		Node temp = dummyHead;
		for (int i = -1; i < index; i++)
			temp = temp.next;
		return temp;
	}
	
	@Override
	public boolean add(Object elem) {
		add(size, elem);
		return false;
	}

	@Override
	public void add(int index, Object elem) {
		Node temp = locate(index-1);
		Node make = new Node();
		make.value = elem;
		make.next = temp.next;
		temp.next = make;
		size++;
	}

	@Override
	public boolean addAll(SimpleList list) {
		addAll(size, list);
		return true;
	}

	@Override
	public boolean addAll(int index, SimpleList list) {
		for (int i = 0; i < list.size(); i++)
			add(index + i, list.get(i));
		return true;
	}

	@Override
	public void clear() {
		dummyHead.next = null;
		size = 0;
		
	}

	@Override
	public boolean contains(Object elem) {
		Node temp = dummyHead;
		boolean found = false;
		while (temp.next != null) {
			if (temp.next.value == elem) {
				found = true;
				break;
			}
			temp = temp.next;
		}
		return found;
	}

	@Override
	public boolean containsAll(SimpleList list) {
		// an enhanced for loop would work best here
		// but my enhanced for loop wasn't looping, not sure why
		// I tried: "for (Object elem : list)"
		// there might be something broken with my iterator class/method but I'm not sure what
		for (int i = 0; i < list.size(); i++)
			if (!contains(list.get(i)))
				return false;
		return true;
	}

	@Override
	public E get(int index) {
		Node temp = locate(index);
		return (E)(temp.value);
	}

	@Override
	public int indexOf(Object elem) {
		Node temp = dummyHead;
		
		for (int i = -1; i < size; i++) {
			if (temp.value == elem)
				return i;
			temp = temp.next;
		}
		
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return dummyHead.next == null;
	}

	private class MyLinkedListIterator implements ListIterator<E> {
		int index = 0;
		Node temp = dummyHead.next;
		@Override
		public boolean hasNext() {
			return temp.next == null;
		}
		@Override
		public E next() {
			return (E)temp.next.value;
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		return (Iterator<E>) new MyLinkedListIterator();
	}

	@Override
	public int lastIndexOf(Object elem) {
		Node temp = dummyHead;
		int index = -1;
		
		for (int i = -1; i < size; i++) {
			if (temp.value == elem)
				index = i;
			temp = temp.next;
		}
		
		return index;
	}

	@Override
	public boolean remove(Object value) {
		remove(indexOf(value));
		return true;
	}

	@Override
	public E remove(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();
			
		Node keep = locate(index-1);
		E value = (E)(keep.next.value);
		keep.next = keep.next.next;
		size--;
		return value;
	}

	@Override
	public boolean removeAll(SimpleList list) {
		// same story here as containsALl()
		for (int i = 0; i < list.size(); i++) {
			Object elem = list.get(i);
			if (contains(elem))
				remove(elem);
		}
		return true;
	}

	@Override
	public boolean retainAll(SimpleList list) {
		SimpleList<E> newList = new LinkedList<E>();
		// same story as removeAll and containsAll
		for (int i = 0; i < list.size(); i++) {
			Object elemToAdd = list.get(i);
			for (int j = 0; j < size; j++) {
				if (elemToAdd == this.get(j)) {
					newList.add((E)elemToAdd);
				}
			}
		}
		
		clear();
		for (int i = 0; i < newList.size(); i++) {
			add(0, newList.get(i));
		}
		return true;
	}

	@Override
	public Object set(int index, Object value) {
		Node temp = locate(index);
		Object oldVal = temp.value;
		temp.value = value;
		return oldVal;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public SimpleList subList(int start, int end) {
		SimpleList<E> subList = new LinkedList<E>();
		Node temp = locate(start);
		for (int i = start; i < end; i++) {
			subList.add((E)temp.value);
			temp = temp.next;
		}
		return subList;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		Node temp = dummyHead.next;
		for (int i = 0; i < size; i++) {
			array[i] = temp.value;
			temp = temp.next;
		}
		return array;
	}
	
	@Override
	public String toString(){
		Node n = dummyHead.next;
		String s = "[";
		while(n != null)
		{
			s += n.value + ", ";
			n = n.next;
		}
		s = s.substring(0, s.length() - 2) + "]";
		return s;
	}


}
