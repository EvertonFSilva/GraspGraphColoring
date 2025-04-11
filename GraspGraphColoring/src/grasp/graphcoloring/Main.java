package grasp.graphcoloring;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\evert\\Desktop\\Test\\grafo.txt";
		String outputPath = "C:\\Users\\evert\\Desktop\\Test\\output.txt";

		Graph graph = GraphIO.loadFromFile(filePath);

		if (graph == null) {
			System.out.println("Erro ao ler o grafo.");
			return;
		}

		int maxIterations = 1000;
		double alpha = 0.3;

		GraspColoring grasp = new GraspColoring(graph, maxIterations, alpha);
		ColoringSolution bestSolution = grasp.run();

		System.out.println("\nMelhor solução encontrada:");
		System.out.println("Número de cores utilizadas: " + bestSolution.getColorCount());
		bestSolution.printColorAssignment();

		try {
            GraphIO.writeColoringSummary(bestSolution, outputPath);
			System.out.println("\nResultado salvo em: " + outputPath);
		} catch (IOException e) {
			System.err.println("Erro ao salvar resultado: " + e.getMessage());
		}
	}
}
