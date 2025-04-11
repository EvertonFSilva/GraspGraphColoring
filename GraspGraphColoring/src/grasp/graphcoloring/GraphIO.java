package grasp.graphcoloring;

import java.io.*;

public class GraphIO {

	public static Graph loadFromFile(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String[] firstLine = br.readLine().trim().split("\\s+");
			int totalVertices = Integer.parseInt(firstLine[0]);
			Graph graph = new Graph(totalVertices);

			for (int i = 0; i < totalVertices; i++) {
				String line = br.readLine();
				if (line == null) {
					throw new IOException("Linhas insuficientes no arquivo para a matriz de adjacência.");
				}

				String[] values = line.trim().split("\\s+");
				if (values.length != totalVertices) {
					throw new IOException("A matriz de adjacência deve ser quadrada. Linha " + i + " tem "
							+ values.length + " colunas.");
				}

				for (int j = 0; j < totalVertices; j++) {
					int value = Integer.parseInt(values[j]);
					if (value > 0 && i != j) {
						graph.addEdge(i, j);
					}
				}
			}

			return graph;

		} catch (IOException | NumberFormatException e) {
			System.err.println("Erro ao ler o arquivo de grafo: " + e.getMessage());
			return null;
		}
	}

	public static void writeColoringSummary(ColoringSolution solution, String filePath) throws IOException {
		File file = new File(filePath);
		file.getParentFile().mkdirs();

		int[] colors = solution.getVertexColors();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write("Número de cores utilizadas: " + solution.getColorCount());
			writer.newLine();
			writer.newLine();
			for (int i = 0; i < colors.length; i++) {
				writer.write("Vértice " + i + " - Cor " + colors[i]);
				writer.newLine();
			}
		}
	}
}
