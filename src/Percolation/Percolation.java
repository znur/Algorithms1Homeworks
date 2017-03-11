package Percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* We model a percolation system using an n-by-n grid of sites.
 Each site is either open or blocked.  A full site is an open site 
 that can be connected to an open site in the top row via a chain of
 neighboring (left, right, up, down) open sites. We say the system 
 percolates if there is a full site in the bottom row. In other words,
 a system percolates if we fill all open sites connected to the top row
 and that process fills some open site on the bottom row.
 */

public class Percolation {
	private boolean[][] grid; // true == open, false == blocked
	private WeightedQuickUnionUF weighted; // sites as a 1D array holding connections
	private int gridSize; // size of square grid
	private int numOpenSites = 0;
	private int vtop; // location of virtual top
	private int vbot; // location of virtual bottom

	// creates n by n boolean array with all sites closed.
	// creates n*n+2 sized WeightedQuickUnion object holding connected sites
	public Percolation(int n) {

		if (n <= 0)
			throw new IllegalArgumentException("size must be greater than zero");

		this.gridSize = n;
		this.vtop = gridSize * gridSize;
		this.vbot = vtop + 1;
		// size: gridsize*gridsize + 2 for virtual top and bottom
		this.weighted = new WeightedQuickUnionUF(vbot + 1); 
		this.grid = new boolean[gridSize][gridSize];

		// // connect virtual top to the first row & virtual bot to the last row
		// for (int i = 0; i < gridSize; i++) {
		// weighted.union(vtop, i);
		// weighted.union(vbot, xyTo1D(gridSize - 1, i));
		// }

		// make all sites initially closed
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j] = false;
			}
		}

	}

	public void open(int row, int col) {
		validateIndices(row, col);
		if (isOpen(row, col))
			return;
		int rrow = row - 1, rcol = col - 1;
		grid[rrow][rcol] = true;
		numOpenSites += 1;
		connectWithNeighbours(rrow, rcol);
	}

	public boolean isOpen(int row, int col) {
		// is site (row, col) open?
		validateIndices(row, col);
		return grid[row - 1][col - 1];
	}

	public boolean isFull(int row, int col) {

		validateIndices(row, col);
		return isOpen(row, col) && weighted.connected(vtop, xyTo1D(row - 1, col - 1));
	}

	public int numberOfOpenSites() {
		return numOpenSites;
	}

	public boolean percolates() {
		return weighted.connected(vtop, vbot);
	}

	// works on real indices, i.e starting from 0

	private int xyTo1D(int row, int col) {
		return gridSize * (row) + (col);
	}

	private boolean isValid(int row, int col) {
		int rrow = row - 1;
		int rcol = col - 1;
		return (0 <= rrow && 0 <= rcol && rrow < gridSize && rcol < gridSize);
	}

	private void validateIndices(int row, int col) {
		if (!isValid(row, col))
			throw new IndexOutOfBoundsException("indexes are out of bounds");
	}

	private void connectWithNeighbours(int rrow, int rcol) {
		if (0 == rrow)
			weighted.union(xyTo1D(rrow, rcol), vtop);
		if (gridSize - 1 == rrow)
			weighted.union(xyTo1D(rrow, rcol), vbot);
		if (0 <= rrow - 1 && isOpen(rrow, rcol + 1))
			weighted.union(xyTo1D(rrow, rcol), xyTo1D(rrow - 1, rcol)); // top
		if (rrow + 1 < gridSize && isOpen(rrow + 2, rcol + 1))
			weighted.union(xyTo1D(rrow, rcol), xyTo1D(rrow + 1, rcol)); // bottom
		if (0 <= rcol - 1 && isOpen(rrow + 1, rcol))
			weighted.union(xyTo1D(rrow, rcol), xyTo1D(rrow, rcol - 1)); // left
		if (rcol + 1 < gridSize && isOpen(rrow + 1, rcol + 2))
			weighted.union(xyTo1D(rrow, rcol), xyTo1D(rrow, rcol + 1)); // right
	}

	public static void main(String[] args) {

	}

}
