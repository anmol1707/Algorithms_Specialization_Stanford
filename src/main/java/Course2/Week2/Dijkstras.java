package Course2.Week2;

import java.util.*;

public class Dijkstras {

    private static final int defaultDistance = 1000000;

    public Dijkstras() {
    }

    public Map<Integer, Integer> findShortestPath(Map<Integer, List<WeightedDestination>> graph, Integer startVertex) {
        Map<Integer, Integer> shortestPaths = initializeResult(graph);
        int reachableVertexCount = findVerticesReachableCount(graph, startVertex);

        Set<Integer> processed = new HashSet<>();
        PriorityQueue<WeightedDestination> minHeap = new PriorityQueue<>(Comparator.comparingInt(WeightedDestination::getWeight));

        minHeap.add(new WeightedDestination(startVertex, 0));

        while(processed.size() != reachableVertexCount) {
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

    private Map<Integer, Integer> initializeResult(Map<Integer, List<WeightedDestination>> graph) {
        Map<Integer, Integer> initialResult = new HashMap<>();
        for (Integer endVertex : graph.keySet()) {
            initialResult.putIfAbsent(endVertex, defaultDistance);
        }
        return initialResult;
    }

    private int findVerticesReachableCount(Map<Integer, List<WeightedDestination>> graph, Integer startVertex) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> toVisit = new Stack<>();
        int result = 0;

        toVisit.add(startVertex);

        while(!toVisit.empty()) {
            Integer vertex = toVisit.pop();
            if(visited.contains(vertex)) {
                continue;
            }
            List<WeightedDestination> edges = graph.getOrDefault(vertex, new ArrayList<>());
            for (WeightedDestination edge : edges) {
                if(!visited.contains(edge.getVertexId())) {
                    toVisit.push(edge.getVertexId());
                }
            }
            visited.add(vertex);
            result++;
        }

        return result;
    }
}
