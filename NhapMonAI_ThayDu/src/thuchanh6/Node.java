package thuchanh6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node implements Comparable<Node>{
	public static final int N = 8;
	Queen[] state;

	public Node() {
		// generateBoard();
		state = new Queen[N];
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N; j++) {
				if (state[i].isConflict(state[j])) {
					heuristic++;
				}
			}
		}
		return heuristic;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();
		for (int i = 0; i < N; i++) {
			Node n = new Node(this.state);
			n.state[i].move();
			result.add(n);
		}
		return result;
	}

	public Node execute(Node initialState) {
		Node current = initialState;
		Node neighbor = null;
		while (true) {
			neighbor = current.getBestCandidates();
			if (neighbor.getH() < current.getH()) {
				current = neighbor;
			} else {
				return current;
			}
		}
	}

	private Node getBestCandidates() {
		List<Node> list= generateAllCandidates();
		int num=list.get(0).getH();
		
		return null;
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		Node current = initialState;
		Node next = null;
		int T= 1000;
		while(current.getH()!=0) {
			next= current.selectNextRandomCandidate();
			int deltaE = next.getH()-current.getH()	;
			if(deltaE<0) {
				current=next;
			}else {
				if(Math.exp(deltaE/T)>Math.random()) {
					current=next;
				}
			}
		}
		return current;
	}
	

	public Node selectNextRandomCandidate() {
			Random random = new Random();
			int index = random.nextInt(N);
			Node result = new Node();
			result.state[index].setRow(random.nextInt(N));
			return result;
		
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
