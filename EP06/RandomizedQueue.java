/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;
import java.util.*;
import java.lang.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] v;
    private int tot, n;
    
    
    // construct an empty randomized queue
    public RandomizedQueue() {
	v = (Item[]) new Object[2];
	tot = 2;
	n = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
	return (n == 0);
    }

    // return the number of items on the queue
    public int size() {
	return n;
    }

    // add the item
    public void enqueue(Item item) {
	if (item == null)
	    throw new java.lang.NullPointerException("Trying to add null pointer");

	if (n == tot) resize(2 * tot);
	v[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
	if (this.isEmpty())
	    throw new java.util.NoSuchElementException("Trying to remove from empty RandomizedQueue");

	
	int i = StdRandom.uniform(n);
	Item ret = v[i];
	v[i] = v[n - 1];
	n--;
	return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
	int i = StdRandom.uniform(n);
	Item ret = v[i];
	return ret;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
	return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
	// variáveis do iterador
	private int cur = n;
	private Item[] r;
	private boolean K = false;
	
	public void init() {
	    r = (Item[]) new Object[tot];
	    for (int i = 0; i < n; i++) {
		r[i] = v[i];
	    }
	    K = true;
	}
	
	public boolean hasNext() {
	    // escreva seu método a seguir
	    return (cur != 0);
	}
	
	public Item next() {
	    // escreva seu método a seguir
	    if (!hasNext()) throw new java.util.NoSuchElementException();
	    if (K == false) init();
	    
	    int i = StdRandom.uniform(n);
	    
	    Item item = r[i];
	    r[i] = r[cur - 1];
	    cur--;
	    return item;
	}
	
	public void remove() {
	    throw new java.lang.UnsupportedOperationException();
	}
	
    }
    
    private void resize(int tam) {
	Item[] temp = (Item[]) new Object[tam];
	for (int i = 0; i < n; i++) {
	    temp[i] = v[i];    
	}
	v = temp;
	tot = tam;
    }

    // unit testing (required)
    public static void main(String[] args) {
	RandomizedQueue<Integer> R = new RandomizedQueue<Integer>();
	
	for (int i = 0; i <= 10; i++) {
	    R.enqueue(i);
	}
	
	StdOut.println(R.size());
	StdOut.println(R.sample());
	
	for (int i = 0; i <= 10; i++) {
	    StdOut.println("---> " + R.dequeue());
	}
    }
}
