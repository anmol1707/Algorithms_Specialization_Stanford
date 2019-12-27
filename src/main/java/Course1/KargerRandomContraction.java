package Course1;

import Helpers.RandomizedHelpers;

import java.util.*;

public class KargerRandomContraction {
    private Map<ContractedVertex, List<ContractedVertex>> adjacencyList;
    private List<ContractedVertex> listKeys;
    private RandomizedHelpers helpers;

    public KargerRandomContraction(Map<ContractedVertex, List<ContractedVertex>> adjacencyList) {
        if(adjacencyList.size() < 2) {
            throw new IllegalArgumentException("Graph must contain 2 vertices!");
        }
        this.adjacencyList = adjacencyList;
        this.listKeys = new ArrayList<>();
        this.helpers = new RandomizedHelpers();
        this.listKeys.addAll(this.adjacencyList.keySet());
    }

    public int getMinimumCut() {
        while (this.adjacencyList.size() > 2) {
            ContractedVertex vertex1 = this.getRandomVertex();
            List<ContractedVertex> connectedVertices1 = this.removeVertexAndGetConnections(vertex1);

            ContractedVertex vertex2 = this.getRandomVertex(connectedVertices1);
            List<ContractedVertex> connectedVertices2 = this.removeVertexAndGetConnections(vertex2);

            ContractedVertex mergedVertex = mergeVertices(vertex1, vertex2);
            List<ContractedVertex> toExclude = new ArrayList(Arrays.asList(vertex1, vertex2));
            List<ContractedVertex> mergedConnections = mergeConnections(connectedVertices1, connectedVertices2, toExclude, mergedVertex);
            this.adjacencyList.put(mergedVertex, mergedConnections);
            this.listKeys.add(mergedVertex);
        }

        return this.adjacencyList.values().iterator().next().size();
    }

    private List<ContractedVertex> mergeConnections(List<ContractedVertex> connections1, List<ContractedVertex> connections2, List<ContractedVertex> toExclude, ContractedVertex mergedVertex) {

        List<ContractedVertex> mergedConnections = new ArrayList<>();

        mergeConnectionsHelper(connections1, toExclude, mergedConnections);
        mergeConnectionsHelper(connections2, toExclude, mergedConnections);

        for (ContractedVertex connection : mergedConnections) {
            this.adjacencyList.get(connection).add(mergedVertex);
        }

        return mergedConnections;
    }

    private void mergeConnectionsHelper(List<ContractedVertex> connections, List<ContractedVertex> toExclude, List<ContractedVertex> mergedConnections) {
        for (ContractedVertex vertex : connections) {
            if (!toExclude.contains(vertex)) {
                mergedConnections.add(vertex);
                List<ContractedVertex> indirectConnections = this.adjacencyList.get(vertex);
                for (ContractedVertex willBeMerged : toExclude) {
                    indirectConnections.remove(willBeMerged);
                }
            }
        }
    }

    private ContractedVertex getRandomVertex(List<ContractedVertex> vertices) {
        int length = vertices.size();
        int randomIndex = this.helpers.getRandomBetweenNumbers(0, length - 1);
        return vertices.get(randomIndex);
    }

    private ContractedVertex getRandomVertex() {
        int randomIndex = this.helpers.getRandomBetweenNumbers(0, this.adjacencyList.size() - 1);
        return this.listKeys.get(randomIndex);
    }

    private ContractedVertex mergeVertices(ContractedVertex vertex1, ContractedVertex vertex2) {
        ContractedVertex mergedVertex = new ContractedVertex();
        mergedVertex.addVertices(vertex1.getVertices());
        mergedVertex.addVertices(vertex2.getVertices());
        return mergedVertex;
    }

    private List<ContractedVertex> removeVertexAndGetConnections(ContractedVertex vertex) {
        this.listKeys.remove(vertex);
        return this.adjacencyList.remove(vertex);
    }
}
