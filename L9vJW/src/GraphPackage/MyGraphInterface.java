package GraphPackage;

import java.util.List;

public interface MyGraphInterface <V extends Comparable<V>>
{
    void addVertex(V vertex);
    void addEdge(V source, V destination, float weight);
    Edge<V> getEdge(V sourceVertex, V destinationVertex);
    List<V> getVertexList();
    List<Edge<V>> getEdgeList();
    List<V> getAdjacentVertexesList(V source);
    List<Edge<V>> getAdjacentEdgeList(V source);
    boolean hasEdge(V source, V destination);
    List<Path<V>> getShortestPathsFromSource(V source);
    Path<V> getShortestPath(V source, V destination);
    Edge.DirectionType getGraphType();
}
