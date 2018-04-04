/*
  Breno Helfstein Moura
  9790972 -- IME - USP
*/

/******************************************************************************
 *  Compilation:  javac BulgingSquares.java
 *  Execution:    java BulgingSquares
 *  Dependencies: StdDraw.java, java.awt.Color
 *
 *  Program draws an optical illusion from Akiyoshi Kitaoka. The center appears 
 *  to bulge outwards even though all squares are the same size. 
 *
 *  meu_prompt > java BulgingSquares
 *
 *  Exercise 14 http://introcs.cs.princeton.edu/java/15inout/
 * 
 ******************************************************************************/

// Standard draw. This class provides a basic capability for creating
// drawings with your programs. It uses a simple graphics model that
// allows you to create drawings consisting of points, lines, and
// curves in a window on your computer and to save the drawings to a
// file.
// https://www.ime.usp.br/~pf/sedgewick-wayne/stdlib/documentation/index.html
// http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
import edu.princeton.cs.algs4.StdDraw; // StdDraw.setXscale, StdDraw.setYscale, ...
import edu.princeton.cs.algs4.StdOut; 
import java.awt.Color; // StdDraw.WHITE, StdDraw.BLACK

public class BulgingSquares {
    // constantes... vixe! use se desejar
    private static final double XMIN   = -75;
    private static final double XMAX   =  75;
    private static final double YMIN   = -75;
    private static final double YMAX   =  75;
    private static final double MARGIN =   2;
    private static final double RADIUS_MAX =   5;
    private static final double DIAM_MAX   = 2*RADIUS_MAX;
    private static final double RADIUS_MIN = 1.5;
    private static final double DIAM_MIN   = 2*RADIUS_MIN;

    private static final int[][] COLOR = new int[][]{
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 5, 1, 6, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 5, 5, 5, 1, 6, 6, 6, 0, 0, 0, 0},
	{0, 0, 0, 5, 5, 5, 5, 1, 6, 6, 6, 6, 0, 0, 0},
	{0, 0, 5, 5, 5, 5, 5, 1, 6, 6, 6, 6, 6, 0, 0},
	{0, 0, 5, 5, 5, 5, 5, 1, 6, 6, 6, 6, 6, 0, 0},
	{0, 5, 5, 5, 5, 5, 5, 1, 6, 6, 6, 6, 6, 6, 0},
	{0, 3, 3, 3, 3, 3, 3, 0, 4, 4, 4, 4, 4, 4, 0},
	{0, 6, 6, 6, 6, 6, 6, 2, 5, 5, 5, 5, 5, 5, 0},
	{0, 0, 6, 6, 6, 6, 6, 2, 5, 5, 5, 5, 5, 0, 0},
	{0, 0, 6, 6, 6, 6, 6, 2, 5, 5, 5, 5, 5, 0, 0},
	{0, 0, 0, 6, 6, 6, 6, 2, 5, 5, 5, 5, 0, 0, 0},
	{0, 0, 0, 0, 6, 6, 6, 2, 5, 5, 5, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 6, 2, 5, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    private static void Ilusion (double x, double y, int op, int c) {
	if (c == 0) StdDraw.setPenColor(StdDraw.WHITE);

	if (op == 2) {
	    StdDraw.filledSquare(x - 3, y - 3, RADIUS_MIN);
	    StdDraw.filledSquare(x - 3, y + 3, RADIUS_MIN);
	} else if (op == 1) {
	    StdDraw.filledSquare(x + 3, y - 3, RADIUS_MIN);
	    StdDraw.filledSquare(x + 3, y + 3, RADIUS_MIN);
	} else if (op == 3) {
	    StdDraw.filledSquare(x - 3, y + 3, RADIUS_MIN);
	    StdDraw.filledSquare(x + 3, y + 3, RADIUS_MIN);
	} else if (op == 4) {
	    StdDraw.filledSquare(x - 3, y - 3, RADIUS_MIN);
	    StdDraw.filledSquare(x + 3, y - 3, RADIUS_MIN);
	} else if (op == 6) {
	    StdDraw.filledSquare(x - 3, y - 3, RADIUS_MIN);
	    StdDraw.filledSquare(x + 3, y + 3, RADIUS_MIN);
	} else if (op == 5) {
	    StdDraw.filledSquare(x + 3, y - 3, RADIUS_MIN);
	    StdDraw.filledSquare(x - 3, y + 3, RADIUS_MIN);
	}
	
	if (c == 0) StdDraw.setPenColor(StdDraw.BLACK);
    }
    
    public static void main(String[] args) {
        // set the scale of the coordinate system
        StdDraw.setXscale(XMIN-MARGIN, XMAX+MARGIN);
        StdDraw.setYscale(YMIN-MARGIN, YMAX+MARGIN);
        StdDraw.enableDoubleBuffering();
        
        // clear the background
        StdDraw.clear(StdDraw.WHITE);
	StdDraw.setPenColor(StdDraw.BLACK);
        // Escreva sua solução a seguir

	for (double i = XMIN; i < XMAX; i += DIAM_MAX) {
	    double ST = ((int)(i - XMIN) % 20 != 0) ? YMIN + DIAM_MAX : YMIN;
	    for (double j = ST; j < YMAX; j += (DIAM_MAX)) {
		int x = (int)(i - XMIN) / (int)DIAM_MAX, y = (int)(j - YMIN) / (int)DIAM_MAX;
		int black = (((int)(j - ST) / (int)DIAM_MAX) + 1) % 2;

		if (black == 1)
		    StdDraw.filledSquare(i + RADIUS_MAX, j + RADIUS_MAX, RADIUS_MAX);
		if (COLOR[x][y] > 0) {
		    Ilusion(i + RADIUS_MAX, j + RADIUS_MAX, COLOR[x][y], (black + 1) % 2);
		}
	    }
	}
	
	// copy offscreen buffer to onscreen
        StdDraw.show();
    }

} 
