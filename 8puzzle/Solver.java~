/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;
import java.util.Comparator;

public class Solver {
    private Stack<Board> sol;
    private int mn;

    private class node {
        Board cur;
	node prev;
	int dist;
	public node(Board C, node P, int D) {
	    cur = C;
	    prev = P;
	    dist = D;
	}
    }

    public class cmp implements Comparator<node> {
	public int compare (node a, node b) {
	    int A = a.cur.manhattan() + a.dist;
	    int B = b.cur.manhattan() + b.dist;
	    if (A < B) return -1;
	    else if (A > B) return 1;
	    else return 0;
	}
    }
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
	if (initial == null)
	    throw new java.lang.NullPointerException("Null board");
	
	if (!initial.isSolvable())
	    throw new java.lang.IllegalArgumentException("Impossible to solve");

	sol = new Stack<Board>();
	cmp C = new cmp();
	MinPQ<node> Q = new MinPQ<node>(C);
	Q.insert(new node(initial, null, 0));
	mn = 0;
	node finish = null;

	while (!Q.isEmpty()) {
	    node u = Q.delMin();
	    if (u.cur.isGoal()) {
		finish = u;
		break;
	    }
	    for (Board nb : u.cur.neighbors()) {
		if ((u.prev != null) && nb.equals(u.prev.cur)) continue;
		Q.insert(new node (nb, u, u.dist + 1));
	    }
	    

	}
	
	while (finish != null) {
	    sol.push(finish.cur);
	    finish = finish.prev;
	    mn++;
	}
	mn--;
    }

    // min number of moves to solve initial board
    public int moves() {
	return mn;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
	return sol;
    }

    // unit testing 
    public static void main(String[] args) {
	int[][] tiles = new int[][] {
	    {8, 1, 3},
	    {4, 0, 2},
	    {7, 6, 5},
	};

	Board B = new Board(tiles);
	Solver S = new Solver(B);
    }
}
