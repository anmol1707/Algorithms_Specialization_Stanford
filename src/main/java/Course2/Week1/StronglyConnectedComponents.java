package Course2.Week1;

import java.util.*;

public class StronglyConnectedComponents {

    public StronglyConnectedComponents() {
    }

    public List<List<Integer>> findConnectedComponents(Map<Integer, List<Integer>> graph) {
        return runKosarajuAlgorithm(graph);
    }

    private List<List<Integer>> runKosarajuAlgorithm(Map<Integer, List<Integer>> graph) {
        Map<Integer, List<Integer>> reversedGraph = getReversedGraph(graph);
        List<Integer> verticesByFinishTimes = calculateFinishTimes(reversedGraph);
        Collections.reverse(verticesByFinishTimes);
        return calculateSCCs(verticesByFinishTimes, graph);
    }

    private List<List<Integer>> calculateSCCs(List<Integer> startVertices, Map<Integer, List<Integer>> graph) {
        List<List<Integer>> connectedComponents = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer startVertex : startVertices) {
            if (visited.contains(startVertex)) {
                continue;
            }
            List<Integer> component = new ArrayList<>();
            dfsSubRoutine(graph, startVertex, visited, component);
            connectedComponents.add(component);
        }

        return connectedComponents;
    }

    private List<Integer> calculateFinishTimes(Map<Integer, List<Integer>> graph) {
        List<Integer> verticesByFinishTimes = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer startVertex : graph.keySet()) {
            if (!visited.contains(startVertex)) {
                dfsSubRoutine(graph, startVertex, visited, verticesByFinishTimes);
            }
        }

        return verticesByFinishTimes;
    }

    private void dfsSubRoutine(Map<Integer, List<Integer>> graph, Integer startNode, Set<Integer> visited, List<Integer> nodesProcessedInOrder) {
        Stack<Integer> nodesToProcess = new Stack<>();
        nodesToProcess.push(startNode);

        while (!nodesToProcess.empty()) {
            startNode = nodesToProcess.pop();
            if (visited.contains(startNode)) {
                continue;
            }

            visited.add(startNode);
            List<Integer> endVertices = graph.getOrDefault(startNode, new ArrayList<>());
            for (Integer endVertex : endVertices) {
                if (!visited.contains(endVertex)) {
                    nodesToProcess.push(endVertex);
                }
            }

            nodesProcessedInOrder.add(startNode);
        }
    }

    private Map<Integer, List<Integer>> getReversedGraph(Map<Integer, List<Integer>> graph) {
        Map<Integer, List<Integer>> reversedGraph = new HashMap<>();

        for (Integer start : graph.keySet()) {
            List<Integer> endVertices = graph.get(start);
            for (Integer endVertex : endVertices) {
                reversedGraph.putIfAbsent(endVertex, new ArrayList<>());
                reversedGraph.get(endVertex).add(start);
            }
        }

        return reversedGraph;
    }
}
