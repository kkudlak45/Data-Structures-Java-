/**************
* Kryzstof Kudlak
* 1908-111-2 
**************/

import java.util.NoSuchElementException;

public class ListOfQueuesPQ<V, P> implements PriorityQueue<V, P> {
	
	Node pStart;
	
	public class Node {
		P priority;
		Node next;
		Queue<V> queue;
		
		public Node() {
			priority = null;
			next = null;
			queue = null;
		}
	}
	
	/*
	 * CONSTRUCTOR - O(1)
	 * See documentation for clearPriorities() because all this does is call clearPriorities()
	 */
	public ListOfQueuesPQ() {
		clearPriorities();
	}
	
	/*
	 * clearPriorities() - O(1)
	 * This function consists of one operation
	 * It simply creates a head node for the list of priorities
	 */
	@Override
	public void clearPriorities() {
		pStart = new Node();
	}

	/*
	 * appendPriority(P) - O(1)
	 * This function loops to the back of the list of priorities and appends a new priority node
	 * Because it must traverse to the back of the list, this function runs on O(P) where P is the number of priorities
	 * Relative to O(N), however, O(P) is essentially O(1) 
	 */
	@Override
	public void appendPriority(P priority) {
		Node temp = pStart;
		while (temp.next != null) {
			temp = temp.next;
		}
		
		temp.next = new Node();
		temp.next.queue = new Queue<V>();
		temp.next.priority = priority;
	}

	/*
	 * enqueue(V, P) - O(1)
	 * Enqueue traverses the priorities list until it finds a match with the provided priority.
	 * Once this priority is found, the add function is used on the queue referenced by the priority node.
	 * The Queue class used in this project was created by me in lab, and all of its functions run on constant time
	 * Because this function traverses the list of priorities, it runs on the order of P which again is basically O(1)
	 */
	@Override
	public void enqueue(V value, P priority) {
		Node temp = pStart.next;
		
		while (temp.priority != priority)
			temp = temp.next;
		
		temp.queue.add(value);
	}

	/*
	 * dequeue() - O(1)
	 * Dequeue searches through the priority nodes until it finds one with a queue reference that isn't empty.
	 * Once this node is located, the remove function is called on its referenced queue.
	 * If the entire structure is empty, a no such element exception is thrown
	 * Because this function needs to search the list of priority nodes, it runs on the order of P which is basically O(1)
	 * Additionally, the remove() function that is part of my Queue class runs O(1)
	 */
	@Override
	public V dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();
		
		Node temp = pStart.next;
		
		while (temp.queue.isEmpty()) {
			temp = temp.next;
		}
		
		return temp.queue.remove();
	}
	
	/* 
	 * peek() - O(1)
	 * This function is a complete copy/paste of the dequeue class except that I changed it to return queue.peek instead of remove.
	 * Like queue.remove(), queue.peek() runs on constant time.  
	 * Since that's the only change made, this function runs on constant time for the same reasoning as dequeue.
	 */
	@Override
	public V peek() {
		if (isEmpty())
			throw new NoSuchElementException();
		
		Node temp = pStart.next;
		
		while (temp.queue.isEmpty()) {
			temp = temp.next;
		}
		
		return temp.queue.peek();
	}

	/*
	 * isEmpty() - O(1)
	 * This function loops through the priorities list and checks to make sure that their referenced queues aren't all empty
	 * Because it loops through the priorities list, this function runs O(P) which is basically O(1)
	 * Note that I understand that this function gets called by peek and dequeue and that I neglected to mention it because I find its runtime to be insignificant 
	 */
	@Override
	public boolean isEmpty() {
		Node temp = pStart.next;
		
		while (temp.queue.isEmpty()) {
			if (temp.next == null)
				return true;
			temp = temp.next;
		}
		
		return false;
	}
	
	/*
	 * toString() - O(n)
	 * This function returns a string of the ADT in the form "{ <P>[Queue elements] <P>[Queue elements] ...}"
	 * It works by looping through each priority's queue elements and making them look pretty.
	 * Since it has to display every element in the structure, it naturally runs with O(n) time
	 */
	@Override
	public String toString() {
		String s = "{";
		Node temp = pStart.next;
		
		while (temp != null) {
			s += " <" + temp.priority + ">";
			s += temp.queue.toString();
			temp = temp.next;
		}
		
		return s + " }";
	}

}
