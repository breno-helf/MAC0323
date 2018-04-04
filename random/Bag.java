/*************************************************************************
 *  Compilation:  javac Bag.java
 *  Execution:    java Bag < input.txt
 *
 *  A generic bag or multiset, implemented using a singly-linked list.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java Bag < tobe.txt
 *  size of bag = 14
 *  is
 *  -
 *  -
 *  -
 *  that
 *  -
 *  -
 *  be
 *  -
 *  to
 *  not
 *  or
 *  be
 *  to
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

// The Bag class represents a bag (or multiset) of generic items,
// not necessarily paiwise distinct.
// It supports insertion and iterating over the items in arbitrary order.
// <p>
// This implementation uses a singly-linked list
// with a static nested class Node.
// See LinkedBag class for the version from the textbook
// that uses a non-static nested class.
// The add, isEmpty, and size operations take constant time.
// Iteration takes time proportional to the number of items.
// <p>
// Removing items is not supported.
// But you can iterate through the itens
// (the order in which items are taken during iteration is not specified).
// <p>
// For additional documentation,
// see <a href="http://algs4.cs.princeton.edu/13stacks/">Section 1.3</a> of
// Algorithms, 4th Edition, by Robert Sedgewick and Kevin Wayne
// (page 121 os printed version).
//
// @author Robert Sedgewick
// @author Kevin Wayne
/**
 * Esta classe implementa um saco (bag) de itens genéricos,
 * não necessariamente todos diferentes.
 * Esse ADT permite inserç£o de novos itens
 * e iteração (em ordem não especificada)
 * sobre os itens do saco.
 * A remoção de itens não está prevista.<P>
 *
 * Essa implementação usa uma lista ligada;
 * os nós da lista são definidas por uma classe estática aninhada Node.
 * (Veja a classe LinkedBag para uma versão que usa
 * uma clase aninhada não estática.)<p>
 *
 * As operações add, isEmpty, e size consomem tempo constante.
 * A iteração consome tempo proporcional ao do número de itens no saco.<p>
 *
 * Documentação adicional: veja
 * <a href="http://algs4.cs.princeton.edu/13stacks/">Section 1.3</a>
 * (página 121 da versão impressa)
 * do livro Algorithms, 4th Edition, de Robert Sedgewick e Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class Bag<Item> implements Iterable<Item> {

    private int N;               // number of elements in bag
    private Node<Item> first;    // beginning of bag

    // helper linked list class
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /** Creates an empty bag.
     * Cria um saco (bag) vazio.
     */
    public Bag() {
        first = null;
        N = 0;
    }

    /** Is this bag empty?
     * Esse saco está vazio?
     */
    public boolean isEmpty() {
        return first == null;
    }

    /** Returns the number of items in this bag
     * (each repeated item counts).
     * Devolve o número de itens nesse saco
     * (cada item repetido conta).
     */
    public int size() {
        return N;
    }

    /** Adds the item to this bag.
      * (Even if item is repeated.)
      * <p>
      * Adiciona item ao saco.
      * (Mesmo que o item seja repetido.)
      */
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }


    /** Returns an iterator that iterates over the items in the bag
     * in arbitrary order. ||<br>
     * Devolve um iterador que itera sobre os itens do saco
     * em ordem arbitrária.<br>
     * (Detalhe de implementação:
     * os itens são examinados em ordem LIFO.)
     */
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /** Unit test of this Bag data type.
     * Builds a Bag from the strings given on standard input
     * (to signal end of input, type Ctrl-z in Windows and Ctrl-d in Unix);
     * then prints the contents of the Bag (in LIFO order).
     */
    public static void main(String[] args) {
        Bag<String> bag = new Bag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }

}
