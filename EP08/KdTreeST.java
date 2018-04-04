/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;
import java.util.Comparator;


public class KdTreeST<Value> {
    // construct an empty symbol table of points
    node root;
    Point2D OR;
    int n;
    public class node {
	node esq, dir;
	Point2D key;
	Value val;
	public node (Point2D p, Value v, node e, node d) {
	    key = p;
	    esq = e;
	    dir = d;
	    val = v;
	}
    }       
    
    public KdTreeST() {
	root = null;
	n = 0;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
	return (n == 0);
    }

    // number of points
    public int size() {
	return n;
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
	if (p == null)
	    throw new java.lang.NullPointerException("Null pointer in function put");

	if (root == null) {
	    root = new node (p, val, null, null);
	} else {
	    node prv = null;
	    node cur = root;
	    int rank = 0;

	    while (cur != null && cur.key != p) {
		if (rank % 2 == 0) {
		    if (p.x() < cur.key.x()) {
			prv = cur;
			cur = cur.esq;
		    } else {
			prv = cur;
			cur = cur.dir;
		    }
		} else {
		    if (p.y() < cur.key.y()) {
			prv = cur;
			cur = cur.esq;
		    } else {
			prv = cur;
			cur = cur.dir;
		    }
		}
		rank++;
	    }

	    if (cur == null) {
		node nd = new node (p, val, null, null);
		if (rank % 2 == 0) {
		    if (p.y() < prv.key.y()) {
			prv.esq = nd;
		    } else {
			prv.dir = nd;
		    }
		} else {
		    if (p.x() < prv.key.x()) {
			prv.esq = nd;
		    } else {
			prv.dir = nd;
		    }
		}
	    } else {
		cur.val = val;
	    }
	}
    }

    // value associated with point p
    public Value get(Point2D p) {
	if (p == null)
	    throw new java.lang.NullPointerException("Null pointer in function get");

	node prv = null;
	node cur = root;
	int rank = 0;

	while (cur != null && cur.key != p) {
	    if (rank % 2 == 0) {
		if (p.x() < cur.key.x()) {
		    prv = cur;
		    cur = cur.esq;
		} else {
		    prv = cur;
		    cur = cur.dir;
		}
	    } else {
		if (p.y() < cur.key.y()) {
		    prv = cur;
		    cur = cur.esq;
		} else {
		    prv = cur;
		    cur = cur.dir;
		}
	    }
	    rank++;
	}

	if (cur == null) {
	    return null;
	} else {
	    return cur.val;
	}
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
	if (p == null)
	    throw new java.lang.NullPointerException("Null pointer in function contains");

	node prv = null;
	node cur = root;
	int rank = 0;

	while (cur != null && cur.key != p) {
	    if (rank % 2 == 0) {
		if (p.x() < cur.key.x()) {
		    prv = cur;
		    cur = cur.esq;
		} else {
		    prv = cur;
		    cur = cur.dir;
		}
	    } else {
		if (p.y() < cur.key.y()) {
		    prv = cur;
		    cur = cur.esq;
		} else {
		    prv = cur;
		    cur = cur.dir;
		}
	    }
	    rank++;
	}

	if (cur == null) {
	    return false;
	} else {
	    return true;
	}
    }

    // all points in the symbol table - level order
    public Iterable<Point2D> points() {
	Queue<node> Q = new Queue<node>();
	Queue<Point2D> ret = new Queue<Point2D>();

	Q.enqueue(root);

	while (!Q.isEmpty()) {
	    node cur = Q.dequeue();
	    ret.enqueue(cur.key);
	    if (cur.esq != null) 
		Q.enqueue(cur.esq);
	    if (cur.dir != null)
		Q.enqueue(cur.dir);	    
	}
	
	return ret;
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
	Queue<Point2D> Q = new Queue<Point2D>();

	for (Point2D p : points()) {
	    if (rect.contains(p) == true) {
		Q.enqueue(p);
	    }
	}
	
	return Q;

    }
    
    // a nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
	double dist = Double.POSITIVE_INFINITY;
	Point2D near = null;
	for (Point2D P : points()) {
	    if (p.distanceSquaredTo(P) < dist) {
		dist = p.distanceSquaredTo(P);
		near = P;
	    }
	}
	return near;
    }
    

    public class cmp implements Comparator<Point2D> {
	public int compare (Point2D a, Point2D b) {
	    double A = OR.distanceTo(a);
	    double B = OR.distanceTo(b);

	    if (A < B) return -1;
	    else if (A > B) return 1;
	    else return 0;
	}
    }
    
    public Iterable<Point2D> nearest(Point2D p, int k) {
	Queue<Point2D> ret = new Queue<Point2D>();
	if (p == null)
	    throw new java.lang.NullPointerException("Null pointer in function nearest");
	OR = p;
	cmp C = new cmp();
	MinPQ<Point2D> Q = new MinPQ<Point2D>(C);

	for (Point2D P : points()) {
	    Q.insert(P);
	}
	for (int i = 0; i < k; i++) {
	    ret.enqueue(Q.delMin());	    
	}
	return ret;
    }
    
    // unit testing (required)
    public static void main(String[] args) {

    }
}
