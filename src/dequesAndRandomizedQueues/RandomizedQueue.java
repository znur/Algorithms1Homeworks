package dequesAndRandomizedQueues;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

/*A randomized queue is similar to a stack or queue, except that the item 
 * removed is chosen uniformly at random from items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] items; // items in RandomizedQueue
	private int size; // number of items

	// construct an empty randomized queue
	public RandomizedQueue() {
		items = (Item[]) new Object[1];
		size = 0;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the queue
	public int size() {
		return size;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		grow();
		items[size++] = item;
	}

	// if items array has no space to add the new object, its length doubled
	private void grow() {
		if (size == items.length) {
			Item[] temp = (Item[]) new Object[2 * items.length];
			for (int i = 0; i < items.length; i++) {
				temp[i] = items[i];
			}
			items = temp;
		}
	}

	// remove and return a random item
	public Item dequeue() {
		if (size == 0)
			throw new java.util.NoSuchElementException();
		int i = randomIndex();
		Item item = items[i];
		items[i] = items[size - 1];
		items[--size] = null;
		shrink();
		return item;
	}

	// if items array has legth 4 * size, its length halved
	private void shrink() {
		if (size < items.length / 4) {
			Item[] temp = (Item[]) new Object[items.length / 2];
			for (int i = 0; i < size; i++) {
				temp[i] = items[i];
			}
			items = temp;
		}

	}

	// return (but do not remove) a random item
	public Item sample() {
		if (size == 0)
			throw new java.util.NoSuchElementException();
		int i = randomIndex();
		return items[i];
	}

	private int randomIndex() {
		return StdRandom.uniform(0, size);
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private int cur;
		private Item[] iter;

		public RandomizedQueueIterator() {
			iter = (Item[]) new Object[size];
			for (int i = 0; i < size; i++)
				iter[i] = items[i];
			StdRandom.shuffle(iter);
		}

		@Override
		public boolean hasNext() {
			return cur < size;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			return iter[cur++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {
	}

}
