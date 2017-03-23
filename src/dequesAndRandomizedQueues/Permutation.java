package dequesAndRandomizedQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*takes a command-line integer k; 
 * reads in a sequence of strings from standard input using StdIn.readString(); 
 * prints exactly k of them, uniformly at random. 
 * Prints each item from the sequence at most once. 
 * Assumed that 0 ≤ k ≤ n, where n is the number of string on standard input.
 */


public class Permutation {

	public static void main(String[] args) {
		RandomizedQueue<String> str = new RandomizedQueue<>();
		System.out.println("we are in");
		while (!StdIn.isEmpty()) str.enqueue(StdIn.readString());		
		int times = Integer.parseInt(args[0]);
		System.out.println(times);
		while (times --> 0) System.out.println(str.dequeue());
			
			//StdOut.println(str.dequeue());
	}
}
