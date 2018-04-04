/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;
//import java.util.*;
import java.lang.*;

public class CBSPaths {
    public static void main(String[] args) {
	int n, m, k;
	int num = 0;
	
	SeparateChainingHashST<String, Integer> map;
	map = new SeparateChainingHashST<String, Integer>();

	SeparateChainingHashST<Integer, String> inv;
	inv = new SeparateChainingHashST<Integer, String>();
	
	n = StdIn.readInt();
	m = StdIn.readInt();
	k = StdIn.readInt();


	
	EdgeWeightedDigraph G = new EdgeWeightedDigraph(n);
	
	Double[] dist = new Double[n];
	Double[] MP   = new Double[n];

	Queue<Integer> lula = new Queue<Integer>();
	Queue<Integer> moro = new Queue<Integer>();
	
	for (int i = 0; i < m; i++) {
	    String a, b;
	    int A, B;
	    double w;
	    a = StdIn.readString();
	    b = StdIn.readString();
	    w = StdIn.readDouble();
	    
	    if (!map.contains(a)) {
		map.put(a, num);
		inv.put(num, a);
		num++;
	    }

	    A = map.get(a);
	    
	    if (!map.contains(b)) {
		map.put(b, num);
		inv.put(num, b);
		num++;
	    }

	    B = map.get(b);
	    
	    DirectedEdge E = new DirectedEdge(A, B, w);
	    G.addEdge(E);
	}
	
	for (int i = 0; i < k; i++) {
	    String a;
	    int A;
	    a = StdIn.readString();

	    A = map.get(a);

	    lula.enqueue(A);
	}

	String a;
	int A;
	a = StdIn.readString();
	
	A = map.get(a);
	
        moro.enqueue(A);

	for (int i = 0; i < n; i++) {
	    dist[i] = MP[i] = -1.0;
	}

	for (int cunha : lula) {
	    DijkstraSP D = new DijkstraSP(G, cunha);
	    for (int i = 0; i < n; i++) {
		if (D.distTo(i) > dist[i])
		    dist[i] = D.distTo(i);
	    }
	}

	for (int juiz : moro) {
	    DijkstraSP D = new DijkstraSP(G, juiz);
	    for (int i = 0; i < n; i++) {
		if (D.distTo(i) > MP[i])
		    MP[i] = D.distTo(i);
	    }
	}

	SET<String> ans = new SET<String>();
	    
	for (int i = 0; i < n; i++) {
	    if (dist[i] < MP[i])
		ans.add(inv.get(i));   
	}

	if (ans.size() == 0) {
	    StdOut.println("VENHA COMIGO PARA CURITIBA");
	    StdOut.println();
	}
	else {
	    for (String safe : ans) {
		StdOut.println(safe);
		StdOut.println();
	    }
	}
    }
}
