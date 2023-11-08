import org.junit.Test;

import java.util.Arrays;

public class DigraphTest {
    @Test
    public void testGraph() {
        Digraph<Integer> g = new Digraph<>();

        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);

        assert g.hasVertex(1);
        assert g.order() == 4;

        g.removeVertex(4);
        assert g.order() == 3;

        g.addEdge(1, 2, 5);
        g.addEdge(2, 3, 8);

        assert g.hasEdge(1, 2);
        assert g.hasEdge(2, 3);
    }

    @Test
    public void testFloyd() {
        Digraph<Integer> g = new Digraph<>();

        g.addVertex(0);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);

        g.addEdge(0, 1 ,10);
        g.addEdge(0, 3 ,5);
        g.addEdge(1, 2 ,1);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(3, 1, 3);
        g.addEdge(3, 2, 9);
        g.addEdge(3, 4, 2);
        g.addEdge(4, 2, 6);
        g.addEdge(4, 0, 7);

        Digraph.FloydResult res = g.floydWarshall();

        System.out.println(Arrays.deepToString(res.minCosts));
        System.out.println(Arrays.deepToString(res.predecessor));
    }
}
