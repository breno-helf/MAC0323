/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;


public class Subset {

    public static void main(String[] args) {
	RandomizedQueue<String> R = new RandomizedQueue<String>();
	
	int k = Integer.parseInt(args[0]);
	while (StdIn.hasNextChar()) {
	    R.enqueue(StdIn.readString());
	}
	while ((k--) != 0) {
	    StdOut.println(R.dequeue());
	}
    }
}
