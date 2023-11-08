import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Digraph<T> implements Graph<T> {
    private int alfa;
    private int order;
    private final Map<T, Map<T, Integer>> costMatrix;
    private final List<T> vertexes;

    public Digraph() {
        alfa = 0;
        order = 0;
        costMatrix = new HashMap<>();
        vertexes = new ArrayList<>();
    }

    @Override
    public void addVertex(T x) {
        if (!vertexes.contains(x)) {
            vertexes.add(x);
            costMatrix.put(x, new HashMap<>());
            order++;
        }
    }

    @Override
    public void removeVertex(T x) {
        if (vertexes.contains(x)) {
            vertexes.remove(x);
            costMatrix.remove(x);

            for (T vertex: costMatrix.keySet()) {
                costMatrix.get(vertex).remove(x);
            }
            order--;
        }
    }

    @Override
    public boolean hasVertex(T v) {
        return vertexes.contains(v);
    }

    @Override
    public List<T> getVertexes() {
        return vertexes;
    }

    @Override
    public void addEdge(T v, T w, int cost) {
        if (!vertexes.contains(v) || !vertexes.contains(w)) {
            throw new IllegalArgumentException();
        }
        costMatrix.get(v).put(w, cost);
        alfa++;
    }

    @Override
    public void removeEdge(T v, T w) {
        if (!vertexes.contains(v) || !vertexes.contains(w)) {
            throw new IllegalArgumentException();
        }
        costMatrix.get(v).remove(w);
        alfa--;
    }

    @Override
    public boolean hasEdge(T v, T w) {
        if (!vertexes.contains(v) || !vertexes.contains(w)) {
            throw new IllegalArgumentException();
        }
        return costMatrix.get(v).containsKey(w);
    }

    @Override
    public int order() {
        return order;
    }

    @Override
    public int alpha() {
        return alfa;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        if (vertexes.contains(v)) {
            return new ArrayList<>(costMatrix.get(v).keySet());
        }
        return new ArrayList<>();
    }

    public FloydResult floydWarshall() {
        int [][] costs = new int[order][order];
        int [][] predecessor = new int[order][order];

        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                if (i == j) {
                    costs[i][j] = 0;
                } else if (costMatrix.containsKey(vertexes.get(i)) && costMatrix.get(vertexes.get(i)).containsKey(vertexes.get(j))) {
                    costs[i][j] = costMatrix.get(vertexes.get(i)).get(vertexes.get(j));
                } else {
                    costs[i][j] = Integer.MAX_VALUE;
                }
                predecessor[i][j] = i;
            }
        }

        for (int k = 0; k < order; k++) {
            for (int i = 0; i < order; i++) {
                for (int j = 0; j < order; j++) {
                    if (costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE
                            && costs[i][k] + costs[k][j] < costs[i][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                        predecessor[i][j] = predecessor[k][j];
                    }
                }
            }
        }

        return new FloydResult(costs, predecessor);
    }

    static class FloydResult {
        final int[][] minCosts;
        final int[][] predecessor;

        FloydResult(int[][] minCosts, int[][] predecessor) {
            this.minCosts = minCosts;
            this.predecessor = predecessor;
        }
    }
}
