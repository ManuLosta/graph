import java.util.List;

public class Digraph<T> implements Graph<T>{
    @Override
    public void addVertex(T x) {
        
    }

    @Override
    public void removeVertex(T x) {

    }

    @Override
    public boolean hasVertex(T v) {
        return false;
    }

    @Override
    public List<T> getVertexes() {
        return null;
    }

    @Override
    public void addEdge(T v, T w) {

    }

    @Override
    public void removeEdge(T v, T w) {

    }

    @Override
    public boolean hasEdge(T v, T w) {
        return false;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public int alpha() {
        return 0;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        return null;
    }
}
