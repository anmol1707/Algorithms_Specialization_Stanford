package Course2.Week2;

import java.util.*;

public class Dijkstras {

    private static final int defaultDistance = 1000000;

    public Dijkstras() {
    }

    public Map<Integer, Integer> findShortestPath(Map<Integer, List<WeightedDestination>> graph, Integer startVertex) {
        Map<Integer, Integer> shortestPaths = new HashMap<>();
        int totalVertices = initializeResult(graph, shortestPaths);

        Set<Integer> processed = new HashSet<>();
        PriorityQueue<WeightedDestination> minHeap = new PriorityQueue<>(Comparator.comparingInt(WeightedDestination::getWeight));

        minHeap.add(new WeightedDestination(startVertex, 0));

        while(processed.size() != totalVertices && !minHeap.isEmpty()) {
            WeightedDestination path = minHeap.poll();
            if(processed.contains(path.getVertexId())) {
                continue;
            }
            shortestPaths.put(path.getVertexId(), path.getWeight());

            List<WeightedDestination> weightedEdges = graph.get(path.getVertexId());
            for (WeightedDestination edge : weightedEdges) {
                minHeap.add(new WeightedDestination(edge.getVertexId(), path.getWeight() + edge.getWeight()));
            }

            processed.add(path.getVertexId());
        }

        return shortestPaths;
    }

    private int initializeResult(Map<Integer, List<WeightedDestination>> graph, Map<Integer, Integer> shortestPaths) {
        Set<Integer> allVertices = new HashSet<>();
        for (Integer startVertex : graph.keySet()) {
            shortestPaths.putIfAbsent(startVertex, defaultDistance);

            allVertices.add(startVertex);
            List<WeightedDestination> edges = graph.get(startVertex);
            for (WeightedDestination edge : edges) {
                allVertices.add(edge.getVertexId());
            }
        }
        return allVertices.size();
    }
}
