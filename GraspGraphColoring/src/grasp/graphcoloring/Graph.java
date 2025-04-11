package grasp.graphcoloring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
	private final int totalVertices;
	private final List<Set<Integer>> adjacencyList;

	public Graph(int totalVertices) {
		this.totalVertices = totalVertices;
		this.adjacencyList = new ArrayList<>(totalVertices);
		for (int i = 0; i < totalVertices; i++) {
			adjacencyList.add(new HashSet<>());
		}
	}

	public void addEdge(int v1, int v2) {
		adjacencyList.get(v1).add(v2);
		adjacencyList.get(v2).add(v1);
	}

	public boolean isAdjacent(int v1, int v2) {
		return adjacencyList.get(v1).contains(v2);
	}

	public Set<Integer> getNeighbors(int v) {
		return adjacencyList.get(v);
	}

	public int getDegree(int v) {
		return adjacencyList.get(v).size();
	}

	public int getTotalVertices() {
		return totalVertices;
	}
}
