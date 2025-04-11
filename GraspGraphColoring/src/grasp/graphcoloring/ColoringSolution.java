package grasp.graphcoloring;

import java.util.Arrays;

public class ColoringSolution {
	private final int[] vertexColors;

	public ColoringSolution(int totalVertices) {
		this.vertexColors = new int[totalVertices];
		Arrays.fill(this.vertexColors, -1);
	}

	public void setColor(int vertex, int color) {
		this.vertexColors[vertex] = color;
	}

	public int getColor(int vertex) {
		return this.vertexColors[vertex];
	}

	public int getColorCount() {
		int maxColor = -1;
		for (int color : this.vertexColors) {
			if (color > maxColor) {
				maxColor = color;
			}
		}
		return maxColor + 1;
	}

	public void printColorAssignment() {
		for (int vertex = 0; vertex < this.vertexColors.length; vertex++) {
			System.out.println("Vértice " + vertex + " - Cor " + this.vertexColors[vertex]);
		}
	}

	public ColoringSolution copy() {
		ColoringSolution copy = new ColoringSolution(this.vertexColors.length);
		System.arraycopy(this.vertexColors, 0, copy.vertexColors, 0, this.vertexColors.length);
		return copy;
	}

	public int[] getVertexColors() {
		return this.vertexColors;
	}
}