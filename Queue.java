/**************
* Kryzstof Kudlak
* 1908-111-2 
***************/

import java.util.NoSuchElementException;

public class Queue<E> {

	Node head, tail;
	int size;
	
	/*
	 * CONSTRUCTOR - O(1)
	 * Calls the clear() function, so see its documentation
	 */
	public Queue() {
		clear();
	}
	
	public class Node {
		E value;
		Node next;
		
		public Node() {
			value = null;
			next = null;
		}
	}
	
	/*
	 * add(E) - O(1)
	 * This pretty much just jumps to the tail end of the structure and adds a new node to the queue.
	 * No loops, just operations, meaning this runs O(1)
	 */
	public boolean add(E item) {
		Node temp = new Node();
		temp.value = item;
		if (isEmpty()) {
			tail = head = temp;
		} else {
			tail.next = temp;
			tail = temp;
		}
		size++;
		return true;
	}

	/*
	 * peek() - O(1)
	 * All this does is return the value of whatever is first in the queue if the queue isn't empty
	 * It's one operation (excluding the isEmpty() check), so it runs O(1)
	 */
	public E peek() {
		if (isEmpty())
			return null;
		return head.value;
	}

	/*
	 * remove() - O(1)
	 * Minus the isEmpty() check, this just changes the head reference to next and returns the previous head's value
	 * This function consists of two operations with no loops, so it runs O(1)
	 */
	public E remove() {
		if (isEmpty())
			throw new NoSuchElementException();
		E temp = head.value;
		head = head.next;
		size--;
		return temp;
	}

	/*
	 * clear() - O(1)
	 * This runs O(1) because it consists of 2 or 3 operations.  It just sets stuff to null or 0.
	 */
	public void clear() {
		head = tail = null;
		size = 0;
	}

	/*
	 * isEmpty() - O(1)
	 * All this does is check whether the queue is empty using size
	 * This is only one operation, so it runs O(1)
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * size() -O(1)
	 * All this does is return the size, and I don't think I used it for this project
	 * This is just an accessor method, so it runs O(1)
	 */
	public int size() {
		return size;
	}
	
	/*
	 * toString() - O(n)
	 * This is a toString method written by our lovely TA to test the Queue class with his QueueTester file
	 * It runs on the order of n because it has to traverse the entire queue to display all the values contained within
	 */
	public String toString()
    {
   	 String s = "[";
   	 Node n = head;
   	 while(n != null)
   	 {
   		 s += n.value + ", ";
   		 n = n.next;
   	 }
   	 if(s.length() > 1)
   		 s = s.substring(0, s.length() - 2);
   	 s += "]";
   	 return s;
    }


}
