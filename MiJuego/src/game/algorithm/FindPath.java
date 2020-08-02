package game.algorithm;

import java.util.ArrayList;

public class FindPath {
	
	public ArrayList<Node> closedList = new ArrayList<Node>();
	private ArrayList<Node> neighbourList = new ArrayList<Node>();
	private Dist calcDist = new Dist();
	private Node start;
	private Node end;
	private int[][] map;
	
	public FindPath(Node start, Node end, int[][] map) {
	
	this.map = map;
	this.start = start;
	this.end = end;
	closedList.add(start);
	
	System.out.println("Starting from: (" + start.getRow() +","+ start.getCol()+")");
	
	populateNeighboursList(start);
	calculateDistances(neighbourList);
	moveBestToClosedList(neighbourList);
	
	do {
		
	populateNeighboursList(closedList.get(closedList.size() -1));
	calculateDistances(neighbourList);
	moveBestToClosedList(neighbourList);
	
	} while((closedList.get(closedList.size() -1).getRow() != end.getRow()) || (closedList.get(closedList.size() -1).getCol() != end.getCol()));
	
	// print closed list when done
	for(int i = 0; i < closedList.size(); i++) {
		System.out.print("-> (" + closedList.get(i).getRow() + "," + closedList.get(i).getCol() + ") ");
	}
}
	private void populateNeighboursList(Node n) {
		int row = n.getRow();
		int col = n.getCol();
		
		// Check Upper side
		if(row > 0){
			if(col > 0) {
				if(map[row-1][col-1] == 1) {neighbourList.add(new Node(row-1, col-1));}
			}
			if(map[row-1][col] == 1) {neighbourList.add(new Node(row-1, col));}
			
			if(col < map[row].length - 1){
				if(map[row-1][col+1] == 1 && col < map[row-1].length) {neighbourList.add(new Node(row-1, col+1));}
			}
		}
		
		// Check Lower side
		if(row < map.length - 1){
			if(col > 0){
				if(map[row+1][col-1] == 1 ) {neighbourList.add(new Node(row+1, col-1));}
			}
			if(map[row+1][col] == 1) {neighbourList.add(new Node(row+1, col));}
			
			if(col < map[row].length - 1){
				if(map[row+1][col+1] == 1 && col < map[row+1].length) {neighbourList.add(new Node(row+1, col+1));}
			}
		}
		
		// Check Middle
		if(col < map[row].length - 1){
			if(map[row][col+1] == 1){ neighbourList.add(new Node(row, col+1));}
		}
		if(col > 0){
			if(map[row][col-1] == 1){ neighbourList.add(new Node(row, col-1));}
		}
		
	}

	private void calculateDistances(ArrayList<Node> list) {
		for(Node node : list) {
			node.setDistToGoal(calcDist.calculateManhattanDist(node.getCol(), end.getCol(), node.getRow(), end.getRow()));
			node.setDistToStart(calcDist.calculateManhattanDist(node.getCol(), start.getCol(), node.getRow(), start.getRow()));
		}
	}
	
	private boolean findDuplicate(Node n, ArrayList<Node> list) {
		for(Node iterator : list ) {
			if(iterator.getRow() == n.getRow() && iterator.getCol() == n.getCol()) {
				return true;
			}
		}
		return false;	
	}
	
	private void moveBestToClosedList(ArrayList<Node> list){
		int row = 0;
		int col = 0;
		int distGoal = Integer.MAX_VALUE;
		int distHome = Integer.MAX_VALUE;
		
		for(Node node : list) {
			if(node.getDistToGoal() <= distGoal && findDuplicate(node, closedList) == false) {
				
				if(node.getDistToGoal() == distGoal && node.getDistToStart() >= distHome) {
					continue;
				}
				
				row = node.getRow();
				col = node.getCol();
				distGoal = node.getDistToGoal();
				distHome = node.getDistToStart();
			}
		}
		closedList.add(new Node(row, col));
		neighbourList.removeAll(neighbourList);
	}

	public ArrayList<Node> getClosedList() {
		return closedList;
	}
}
