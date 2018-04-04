/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;


public class Percolation {
    private UF U;
    private boolean[][] grid;
    private int open_sites, size, st, end;
    private static final int[] dx = new int[]{0, 1, 0, -1};
    private static final int[] dy = new int[]{1, 0, -1, 0};
    
    // create n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
	if (n <= 0) {
	    throw new java.lang.IllegalArgumentException("Grid can't have size <= 0");
	}
	grid = new boolean[n][n];
	U = new UF(n * n + 2);
	open_sites = 0;
	size = n;
	st = n * n;
	end = n * n + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
	if (row < 0 || row >= size || col < 0 || col >= size)
	    throw new java.lang.IndexOutOfBoundsException("Index out of range");

	if (grid[row][col] == false) {
	    int x, y;
	    grid[row][col] = true;
	    open_sites++;
	    for (int k = 0; k < 4; k++) {
		x = row + dx[k];
		y = col + dy[k];
		if (x >= 0 && x < size && y >= 0 && y < size) {
		    if (grid[x][y] == true && !U.connected(row * size + col, x * size + y)) {
			U.union(row * size + col, x * size + y);
		    }
		}
	    }
	    if (row == 0) U.union(row * size + col, st);
	    if (row == (size - 1)) U.union(row * size + col, end);
	}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
	if (row < 0 || row >= size || col < 0 || col >= size)
	    throw new java.lang.IndexOutOfBoundsException("Index out of range");
	return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
	if (row < 0 || row >= size || col < 0 || col >= size)
	    throw new java.lang.IndexOutOfBoundsException("Index out of range");
	return (U.connected(row * size + col, st) || U.connected(row * size + col, end));
    }

    // number of open sites
    public int numberOfOpenSites() {
	return open_sites;
    }

    // does the system percolate?
    public boolean percolates() {
	return (U.connected(st, end));
    }

    // unit testing (required)
    public static void main(String[] args) {
	Percolation perc = new Percolation(10);

	for (int i = 0; i < 10; i++) {
	    for (int j = (i % 2); j < 10; j += 2) {
		perc.open(i, j);
	    }
	}
	for (int i = 0; i < 10; i++) {
	    for (int j = 0; j < 10; j++) {
		StdOut.println(i + " " + j + (perc.isOpen(i, j) ? " OPEN" : " CLOSED") + (perc.isFull(i, j) ? " and FULL" : " and EMPTY"));
	    }
	}
	StdOut.println(perc.numberOfOpenSites());
	StdOut.println(perc.percolates());
	for (int i = 0; i < 10; i++) {
	    perc.open(i, 0);
	}
	StdOut.println(perc.percolates());
	
    }
}

