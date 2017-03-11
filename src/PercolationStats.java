import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int gridSize;
	private int trialNumber;
	private double[] results;

	// Performs T independent computational experiments on an N-by-N grid.
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException("n and trials must be greater than oe equal to 0");

		gridSize = n;
		trialNumber = trials;
		results = new double[trialNumber];

		for (int i = 0; i < trialNumber; i++) {
			Percolation percolation = new Percolation(gridSize);
			int count = 0;
			while (!percolation.percolates()) {
				int row = 1 + StdRandom.uniform(gridSize);
				int col = 1 + StdRandom.uniform(gridSize);
				if (!percolation.isOpen(row, col)) {
					percolation.open(row, col);
					count += 1;

				}
			}
			double result = (double) count / (gridSize * gridSize);
			results[i] = result;

		}

	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(results);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(results);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - ((1.96 * stddev()) / Math.sqrt(trialNumber));
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + ((1.96 * stddev()) / Math.sqrt(trialNumber));
	}

	// test client
	public static void main(String[] args) {

		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);

		PercolationStats percolationStats = new PercolationStats(n, trials);
		String confidence = percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi();

		StdOut.println("mean                    = " + percolationStats.mean());
		StdOut.println("stddev                  = " + percolationStats.stddev());
		StdOut.println("95% confidence interval =  " + confidence);
	}
}
