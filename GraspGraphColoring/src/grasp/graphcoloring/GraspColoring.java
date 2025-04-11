package grasp.graphcoloring;

import java.util.*;

public class GraspColoring {
	private final Graph graph;
	private final int maxIterations;
	private final double alpha;
	private final Random random = new Random();

	public GraspColoring(Graph graph, int maxIterations, double alpha) {
		this.graph = graph;
		this.maxIterations = maxIterations;
		this.alpha = alpha;
	}

	public ColoringSolution run() {
		ColoringSolution bestSolution = null;

		for (int iteration = 1; iteration <= this.maxIterations; iteration++) {
			ColoringSolution candidateSolution = this.constructGreedyRandomizedSolution();
			this.applyLocalSearch(candidateSolution);

			if (bestSolution == null || candidateSolution.getColorCount() < bestSolution.getColorCount()) {
				bestSolution = candidateSolution.copy();
				System.out.println("[Iteração " + iteration + "] Nova melhor solução com "
						+ bestSolution.getColorCount() + " cores.");
			}
		}

		return bestSolution;
	}

	private ColoringSolution constructGreedyRandomizedSolution() {
		int totalVertices = this.graph.getTotalVertices();
		ColoringSolution solution = new ColoringSolution(totalVertices);
		Set<Integer> uncoloredVertices = new HashSet<>();
		for (int v = 0; v < totalVertices; v++) {
			uncoloredVertices.add(v);
		}

		while (!uncoloredVertices.isEmpty()) {
			List<VertexCandidate> candidates = new ArrayList<>();

			for (int vertex : uncoloredVertices) {
				BitSet usedColors = new BitSet();
				for (int neighbor : this.graph.getNeighbors(vertex)) {
					int neighborColor = solution.getColor(neighbor);
					if (neighborColor != -1) {
						usedColors.set(neighborColor);
					}
				}
				int minAvailableColor = usedColors.nextClearBit(0);
				candidates.add(new VertexCandidate(vertex, minAvailableColor));
			}

			candidates.sort(Comparator.comparingInt(vc -> vc.minAvailableColor));

			int rclSize = Math.max(1, (int) Math.ceil(alpha * candidates.size()));
			List<VertexCandidate> rcl = candidates.subList(0, rclSize);

			VertexCandidate selected = rcl.get(random.nextInt(rcl.size()));
			solution.setColor(selected.vertex, selected.minAvailableColor);
			uncoloredVertices.remove(selected.vertex);
		}

		return solution;
	}

	private void applyLocalSearch(ColoringSolution solution) {
		boolean improved;
		int totalVertices = this.graph.getTotalVertices();

		do {
			improved = false;
			for (int vertex = 0; vertex < totalVertices; vertex++) {
				int currentColor = solution.getColor(vertex);
				for (int color = 0; color < currentColor; color++) {
					if (this.canUseColor(vertex, color, solution)) {
						solution.setColor(vertex, color);
						improved = true;
						break;
					}
				}
			}
		} while (improved);
	}

	private boolean canUseColor(int vertex, int color, ColoringSolution solution) {
		for (int neighbor : this.graph.getNeighbors(vertex)) {
			if (solution.getColor(neighbor) == color) {
				return false;
			}
		}
		return true;
	}

	private static class VertexCandidate {
		int vertex;
		int minAvailableColor;

		VertexCandidate(int vertex, int minAvailableColor) {
			this.vertex = vertex;
			this.minAvailableColor = minAvailableColor;
		}
	}
}
