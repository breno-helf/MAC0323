/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;
import java.util.*;
import java.lang.*;

public class WordFinder {
    
    List< SeparateChainingHashST<String, Integer> > H;
    SeparateChainingHashST<String, Integer> X;    
    int n;
    
    public WordFinder (String[] V) {
	n = V.length;
	H = new ArrayList< SeparateChainingHashST<String, Integer> >();
	X = new SeparateChainingHashST<String, Integer>();
	
	for (int i = 0; i < n; i++) {
	    String[] words = V[i].split(" ");
	    SeparateChainingHashST<String, Integer> B;    
	    B = new SeparateChainingHashST<String, Integer>();
	    for (int j = 0; j < words.length; j++) {
		if (B.contains(words[j]) == true) {
		    int qtd = B.get(words[j]);
		    B.delete(words[j]);
		    B.put(words[j], qtd + 1);
		} else {
		    B.put(words[j], 1);

		    if (X.contains(words[j]) == false) {
			X.put(words[j], 1);
		    } else {
			int qtd = X.get(words[j]);
			X.delete(words[j]);
			X.put(words[j], qtd + 1);
		    }
		}
	    }

	    H.add(B);
	}
	
    }

    public String getMax() {
	String ret = null;
	int mx = 0;
	for (String s : X.keys()) {
	    if (X.get(s) > mx) {
		mx = X.get(s);
		ret = s;
	    }
	}
	return ret;
    }

    public String containedIn(int a, int b) {
	SeparateChainingHashST<String, Integer> A = H.get(a);
	SeparateChainingHashST<String, Integer> B = H.get(b);
	if (A.size() > A.size()) {
	    int t = a;
	    a = b;
	    b = t;
	}

	for (String s : A.keys()) {
	    if (B.contains(s) == true) return s;
	}
	return null;
    }

    public int[] appearsIn(String s) {
	List<Integer> L = new ArrayList<Integer>();
	for (int i = 0; i < n; i++) {
	    SeparateChainingHashST<String, Integer> C = H.get(i);
	    if (C.contains(s) == true) {
		L.add(i);
	    }
	}
	int tam = L.size();
	int[] ret = new int[tam];

	for (int i = 0; i < tam; i++) {
	    ret[i] = L.get(i).intValue();
	}
	return ret;
    }


    public static void main(String[] args) {
	String[] V = new String[2];
	V[0] = "Oi meu nome eh breno";
	V[1] = "Meu nome telefone cassa";

	WordFinder W = new WordFinder(V);
	StdOut.println(W.getMax());
	StdOut.println(W.containedIn(0, 1));
 
    }
}


