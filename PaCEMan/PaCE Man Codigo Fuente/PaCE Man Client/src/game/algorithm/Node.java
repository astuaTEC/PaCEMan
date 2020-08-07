package game.algorithm;

/**
 * Pathfinding A * algorithm for route search
 * Source code: https://github.com/paulyv/pathfinding
 * Modified by: Saymon Astúa
 */

public class Node {

	private int col;
	private int row;
	private int distToGoal;
	private int distToStart;
	
	public Node(int row, int col) {
		this.col = col;
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
	
	public int getDistToGoal() {
		return distToGoal;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public void setDistToGoal(int dist) {
		this.distToGoal = dist;
	}

	public int getDistToStart() {
		return distToStart;
	}

	public void setDistToStart(int distToStart) {
		this.distToStart = distToStart;
	}
	
}
