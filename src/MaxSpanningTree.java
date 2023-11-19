import java.util.*;

public class MaxSpanningTree {
    private final Map<Photo, List<Edge>> adjVertices;
    MaxSpanningTree(Graph graph) {
        this.adjVertices = graph.adjVertices;
    }
    public Set<Edge> maximumSpanningTree(Photo start) {
        Set<Photo> inTree = new HashSet<>();
        PriorityQueue<Edge> edges = new PriorityQueue<>((a, b) -> b.getWeight()- a.getWeight());
        Set<Edge> maxTree = new HashSet<>();

        inTree.add(start);
        edges.addAll(adjVertices.get(start));

        while (!edges.isEmpty()) {
            Edge highestEdge = edges.poll();

            if (!inTree.contains(highestEdge.getDestination())) {
                inTree.add(highestEdge.getDestination());
                maxTree.add(highestEdge);
                edges.addAll(adjVertices.get(highestEdge.getDestination()).stream()
                        .filter(edge -> !inTree.contains(edge.getDestination()))
                        .toList());
            }
        }


        return maxTree;
    }
}
