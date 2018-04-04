/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;

public class Board {
    private int[][] board;
    private int n;
    private int man;
    private int ham;
    
    // construct a board from an N-by-N array of tiles
    // (where tiles[i][j] = tile at row i, column j)
    public Board(int[][] tiles) {
	n = tiles[0].length;
	board = new int[n][n];
	for (int i = 0; i < n; i++)
	    for (int j = 0; j < n; j++)
		board[i][j] = tiles[i][j];
 	man = -1;
	ham = -1;
    }

    private int abs (int x) {
	if (x < 0) return -x;
	return x;
    }
    
    private void swap (int i, int j, int x, int y) {
	int temp = board[x][y];
	board[x][y] = board[i][j];
	board[i][j] = temp;
    }
    
    private boolean check (int i, int j) {
	if (i < 0 || i >= n || j < 0 || j >= n)
	    return false;
	else
	    return true;
    }
    
    // return tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {
	if (!check(i, j))
	    throw new java.lang.IndexOutOfBoundsException("Called tileAt at non-existing position");
	
	return board[i][j];
    }

    // board size N
    public int size() {
	return n;
    }

    // number of tiles out of place
    public int hamming() {
	if (ham != -1) return ham;
	ham = 0;
	int cur = 1;
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (board[i][j] == 0) continue;
		if (board[i][j] != (n * i + j + 1)) ham++;
	    }	    
	}
	return ham;
    }
    
    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
	if (man != -1) return man;
	man = 0;
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (board[i][j] == 0) continue;

		int x = (board[i][j] - 1) / n;
		int y = (board[i][j] - 1) % n;
		man += abs(x - i) + abs(y - j);		
	    }
	}

	return man;
    }

    // is this board the goal board?
    public boolean isGoal() {
	return (hamming() == 0);
    }

    // is this board solvable?
    public boolean isSolvable() {
	int[] v = new int[n * n + 5];
	int sz = n * n;
	int inv = 0;
	int row = -1;
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		v[i * n + j] = board[i][j];
		if (board[i][j] == 0) row = i;
	    }
	}

	for (int i = 0; i < sz; i++) {
	    if (v[i] == 0) continue;
	    for (int j = i + 1; j < sz; j++) {
		if (v[j] == 0) continue;
		if (v[i] > v[j]) inv++;
	    }
	}

	if (n % 2 == 1) {
	    return (inv % 2 == 0);
	} else {
	    return ((inv + row) % 2 == 1);
	}
    }

    // does this board equal y?
    public boolean equals(Object y) {
	if (y == this) return true;
	if (y == null) return false;
	if (y.getClass() != this.getClass()) return false;
	
	Board Y = (Board) y;
	if (this.size() != Y.size()) return false;
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (this.tileAt(i, j) != Y.tileAt(i, j)) return false;
	    }	    
	}
	return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
	Queue<Board> Q = new Queue<Board>();
	int zi = -1, zj = -1;
	int[] dx = new int[] {0, 1, 0, -1};
	int[] dy = new int[] {1, 0, -1, 0};
	
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (board[i][j] == 0) {
		    zi = i;
		    zj = j;
		}
	    }
	}

	assert(zi != -1 && zj != -1);
	
	for (int k = 0; k < 4; k++) {
	    int ni = zi + dx[k];
	    int nj = zj + dy[k];
	    if (check(ni, nj)) {
		swap(zi, zj, ni, nj);
		Board K = new Board(board);
		Q.enqueue(K);
		swap(zi, zj, ni, nj);
	    }
	}

	return Q;	
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
	// Método identico ao fornecido em
        // http://www.cs.princeton.edu/courses/archive/spring17/cos226/checklist/8puzzle.html
	StringBuilder s = new StringBuilder();
	s.append(n + "\n");
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		s.append(String.format("%2d ", tileAt(i, j)));
	    }
	    s.append("\n");
	}
	return s.toString();
    }

    // unit testing (required)
    public static void main(String[] args) {
	int[][] tiles = new int[][] {
	    {8, 1, 3},
	    {4, 0, 2},
	    {7, 6, 5},
	};
	Board B = new Board(tiles);
	StdOut.println(B.manhattan());
	StdOut.println(B.hamming());
	StdOut.println(B.isSolvable());
	for (Board N : B.neighbors()) {
	    //	    StdOut.println(N.toString());
	}
    }
}

