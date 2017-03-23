package dequesAndRandomizedQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * A double-ended queue or deque (pronounced "deck") is a generalization 
 * of a stack and a queue that supports adding and removing items from either 
 * the front or the back of the data structure.
 */
public class Deque<Item> implements Iterable<Item> {

	private int size; // number of elements on queue
	private Node first; // beginning of queue
	private Node last; // end of queue

	// helper linked list class
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
	}

	// construct an empty deque
	public Deque() {
		size = 0;
		first = null;
		last = null;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		first.prev = null;
		if (size > 0)
			oldFirst.prev = first;
		else
			last = first;
		size++;
	}

	// add the item to the end
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.prev = oldLast;
		last.next = null;
		if (size > 0)
			oldLast.next = last;
		else
			first = last;
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("Deque underflow");
		Item item = first.item;
		if (size > 1) {
			first = first.next;
			first.prev = null;
		} else {
			first = null;
			last = null;
		}
		size--;
		return item;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException("Deque underflow");
		Item item = last.item;
		if (size > 1) {
			last = last.prev;
			last.next = null;
		} else {
			first = null;
			last = null;
		}
		size--;
		return item;
	}

	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}

	}

	public static void main(String[] args) {
	}
}