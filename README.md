# 🎨 GRASP - Coloração de Vértices em Grafos

Este projeto implementa a metaheurística **GRASP (Greedy Randomized Adaptive Search Procedure)** em Java para resolver o **Problema de Coloração de Vértices (PCV)** em grafos simples e não direcionados. O objetivo é minimizar o número de cores utilizadas, garantindo que vértices adjacentes recebam cores diferentes.

---

## 📁 Organização do Código

O projeto está organizado em pacotes com responsabilidades bem definidas:

```
grasp/
└── graphcoloring/
    ├── Graph.java               # Representação do grafo e suas operações
    ├── ColoringSolution.java    # Armazena e gerencia a coloração dos vértices
    ├── GraphIO.java             # Leitura e escrita de grafos e soluções
    └── GraspColoring.java       # Implementação da metaheurística GRASP
    └── Main.java                # Classe de entrada para executar o algoritmo
```

---

## ⚙️ Funcionalidades

- 🧠 Aplicação da metaheurística GRASP com:
  - Construção gulosa randomizada com Lista Restrita de Candidatos (RCL) baseada em `alpha`;
  - Busca local para refinar soluções.
- 📥 Leitura de grafos via arquivo `.txt` contendo matriz de adjacência;
- 📤 Geração de relatório da coloração aplicada (número de cores e coloração por vértice);
- 🖨️ Impressão do resultado no terminal.

---

## 🗂️ Formato do Arquivo de Entrada

O grafo deve ser descrito em um arquivo `.txt`, com:

- Primeira linha: número de vértices e número de arestas (separados por espaço);
- Linhas seguintes: matriz de adjacência.

### Exemplo - `grafo.txt`:

```
4 5
0 1 1 1
1 0 1 0
1 1 0 1
1 0 1 0
```

---

## 🚀 Como Executar

### 1. Compilação
```bash
javac grasp/graphcoloring/*.java Main.java
```

### 2. Execução
```bash
java Main
```

### 3. Exemplo de uso no código (`Main.java`):
```java
Graph graph = GraphIO.loadFromFile("grafo.txt");
GraspColoring grasp = new GraspColoring(graph, 100, 0.3); // 100 iterações, alpha = 0.3
ColoringSolution solution = grasp.run();
solution.printColorAssignment();
GraphIO.writeColoringSummary(solution, "saida/solucao.txt");
```

## Colaboradores

- 👩‍💻[@BrunaSoug](https://github.com/BrunaSoug)
- 👨‍💻[@EvertonFSilva](https://github.com/EvertonFSilva)
- 👨‍💻[@Messias](https://github.com/messias003)
- 👨‍💻[@Maria](https://github.com/mariantoniafreitas)
