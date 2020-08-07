package game.algorithm;

/**
 * Pathfinding A * algorithm for route search
 * Source code: https://github.com/paulyv/pathfinding
 * Modified by: Saymon Astúa
 */

public class Dist {
	
	public int calculateManhattanDist(int row1, int row0, int col1, int col0) {

		return Math.abs(row1-row0) + Math.abs(col1-col0);
	}
	
	/* EUCLIDEAN DISTANCE IS NOT USED AT THE MOMENT */
	
	public double calculateEuclidean(int x1, int y1, int x2, int y2) {
	    return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}

}
