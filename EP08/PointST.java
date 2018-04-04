/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;
import java.util.Comparator;

public class PointST<Value> {
    // construct an empty symbol table of points
    RedBlackBST<Point2D, Value> BST;
    Point2D OR;
    
    public PointST() {
	BST = new RedBlackBST<Point2D, Value>();
    }

    // is the symbol table empty?
    public boolean isEmpty() {
	return (BST.size() == 0);
    }
    
    // number of points
    public int size() {
	return BST.size();
    }
    
    // associate the value val with point p
    public void put(Point2D p, Value val) {
	if (p == null)
	    throw new java.lang.NullPointerException("Null pointer in function put");

	if (BST.contains(p) == false)
	    BST.put(p, val);
	else {
	    BST.delete(p);
	    BST.put(p, val);

	}
    }

    // value associated with point p
    public Value get(Point2D p) {
	if (p == null)
	    throw new java.lang.NullPointerException("Null pointer in function get");

	return BST.get(p);
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
	if (p == null)
	    throw new java.lang.NullPointerException("Null pointer in function contains");
	return BST.contains(p);
    }
    
    // all points in the symbol table
    public Iterable<Point2D> points() {
	return BST.keys();
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
	Queue<Point2D> Q = new Queue<Point2D>();
	for (Point2D p : BST.keys()) {
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
	for (Point2D P : BST.keys()) {
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

	for (Point2D P : BST.keys()) {
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
