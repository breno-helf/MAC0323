/*
  Nome: Breno Helfstein Moura
  Num USP: 9790972
*/

import edu.princeton.cs.algs4.*;


public class PercolationStats {
    private double[] data;
    private int N, T;
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials)   {
	if (n <= 0 || trials <= 0)
	    throw new java.lang.IllegalArgumentException("N <= 0 or trials <= 0");

	N = n;
	T = trials;
	data = new double[trials];
	for (int k = 0; k < trials; k++) {
	    Percolation perc = new Percolation(n);
	    int[] v = new int[n * n];
	    for (int i = 0; i < (n * n); i++)
		v[i] = i;
	    while (!perc.percolates()) {
		int j = StdRandom.uniform(n * n - perc.numberOfOpenSites()); 
		int x = v[j] / n;
		int y = v[j] % n;
		perc.open(x, y);
		v[j] = v[n * n - perc.numberOfOpenSites()];
		
	    }
	    data[k] = (double)perc.numberOfOpenSites() / (double)(n * n);
	}
    }

    // sample mean of percolation threshold
    public double mean() {
	double sum = 0;
	for (int k = 0; k < T; k++) sum += data[k];
	return sum / (double)(T);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
	double M = mean();
	double sum = 0;
	for (int k = 0; k < T; k++) sum += (data[k] - M) * (data[k] - M);
	if (T == 1) return 0;
	return Math.sqrt(sum / (double)(T - 1)); 
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLow() {
	return (mean() - (1.96 * stddev())/Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
	return (mean() + (1.96 * stddev())/Math.sqrt(T));
    }

    public static void main(String[] args) {
	PercolationStats P = new PercolationStats(200, 100);
	StdOut.println(P.mean());
	StdOut.println(P.stddev());
	StdOut.println(P.confidenceLow());
	StdOut.println(P.confidenceHigh());
    }

}
