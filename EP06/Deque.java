/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;
import java.util.*;

public class Deque<Item> implements Iterable<Item> {
    private Item[] v;
    private int ini, fim, n, l, r, tot;
    
    // construct an empty dequeem
    public Deque() {
        v = (Item[]) new Object[3];
	l = 0;
	r = 2;
	ini = fim = 1;
	n = 0;
	tot = 3;
    }

    // is the deque empty?
    public boolean isEmpty() {
	return (n == 0);
    }
    // return the number of items on the deque
    public int size() {
	return n;
    }
    
    // add the item to the front
    public void addFirst(Item item) {
	// Exception
	if (item == null)
	    throw new java.lang.NullPointerException("Trying to add null pointer");
	if (ini == l) {
	    resize (3 * tot);
	}

	v[--ini] = item;
	n++;
    }

    // add the item to the end
    public void addLast(Item item) {
	// Exception
	if (item == null)
	    throw new java.lang.NullPointerException("Trying to add null pointer");

	if (fim == (r + 1)) {
	    resize (3 * tot);
	}

	
	v[fim++] = item;
	n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
	// Exception
	if (this.isEmpty())
	    throw new java.util.NoSuchElementException("Trying to remove from empty Deque");
	
	n--;
	return v[ini++];
    }

    // remove and return the item from the end
    public Item removeLast() {
	// Exception
	if (this.isEmpty())
	    throw new java.util.NoSuchElementException("Trying to remove from empty Deque");

	n--;
	return v[--fim];
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
	return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
            // variáveis do iterador
            private int cur = ini;

            public boolean hasNext() {
                // escreva seu método a seguir
                return (cur != fim);
            }

            public Item next() {
                // escreva seu método a seguir
                if (!hasNext()) throw new java.util.NoSuchElementException();
                Item item = v[cur];
                cur++;
                return item;
            }

            public void remove() {
                throw new java.lang.UnsupportedOperationException();
            }

    }

    
    private void resize (int tam) {
	Item[] temp = (Item[]) new Object[tam];
	for (int i = ini; i < fim; i++) {
	    temp[i + tam / 3] = v[i];    
	}
	ini = ini + tam / 3;
	fim = ini + n;
	v = temp;
	tot = tam;
	l = 0;
	r = tam - 1;
    }


    // unit testing (required)
    public static void main(String[] args) {
	Deque<Integer> D = new Deque<Integer>();

	D.addLast(1);
	StdOut.println("---> " + D.removeLast());
	D.addFirst(2);
	StdOut.println("---> " + D.removeLast());
	StdOut.println(D.isEmpty());
	StdOut.println(D.size());
	
	for (int i = 0; i <= 10; i++) {
	    if (i % 2 == 1) D.addLast(i);
	    else D.addFirst(i);
	}
	StdOut.println(D.size());
	
	for (int i = 0; i <= 10; i++) {
	    StdOut.println("---> " + D.removeFirst());
	}
    }
}
