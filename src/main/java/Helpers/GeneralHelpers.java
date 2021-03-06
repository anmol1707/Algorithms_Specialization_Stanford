package Helpers;

import Course1.Week4.ContractedVertex;
import Course2.Week2.WeightedDestination;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class GeneralHelpers {
    public static InputStream readFileAsStream(String filePath) throws FileNotFoundException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found at the specified filePath!");
        }
        return inputStream;
    }

    public static List<Integer> readArrayFromFile(String filePath) throws FileNotFoundException {
        InputStream inputStream = readFileAsStream(filePath);
        Scanner in = new Scanner(inputStream);

        List<Integer> numbers = new ArrayList<>();
        while (in.hasNextInt()) {
            numbers.add(in.nextInt());
        }

        return numbers;
    }

    public static Map<Long, Integer> readArrayAsFreqMapFromFile(String filePath) throws FileNotFoundException {
        InputStream inputStream = readFileAsStream(filePath);
        Scanner in = new Scanner(inputStream);

        Map<Long, Integer> freqMap = new HashMap<>();
        while (in.hasNextLong()) {
            Long number = in.nextLong();
            freqMap.put(number, freqMap.getOrDefault(number, 0) + 1);
        }

        return freqMap;
    }

    public static Map<ContractedVertex, List<ContractedVertex>> readAdvancedDirectedGraphFromFile(String filePath) throws FileNotFoundException {
        InputStream inputStream = readFileAsStream(filePath);
        Scanner in = new Scanner(inputStream);

        Map<ContractedVertex, List<ContractedVertex>> adjacencyList = new HashMap<>();
        String line;
        int length, startVertex, endVertex;
        while(in.hasNextLine()) {
            line = in.nextLine();
            String[] numbers = line.split("\\s+");

            startVertex = Integer.parseInt(numbers[0]);
            ContractedVertex contractedVertex1 = new ContractedVertex();
            contractedVertex1.addVertex(startVertex);
            adjacencyList.put(contractedVertex1, new ArrayList<>());

            length = numbers.length;
            for(int i = 1; i < length; i++) {
                endVertex = Integer.parseInt(numbers[i]);
                ContractedVertex contractedVertex2 = new ContractedVertex();
                contractedVertex2.addVertex(endVertex);
                adjacencyList.get(contractedVertex1).add(contractedVertex2);
            }
        }

        return adjacencyList;
    }

    public static Map<Integer, List<Integer>> readBasicDirectedGraphFromFile(String filePath) throws FileNotFoundException {
        InputStream inputStream = readFileAsStream(filePath);
        Scanner in = new Scanner(inputStream);
        String input;
        String[] vertices;
        int startVertex, endVertex;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        while(in.hasNextLine()) {
            input = in.nextLine();
            vertices = input.split(" ");
            startVertex = Integer.parseInt(vertices[0]);
            endVertex = Integer.parseInt(vertices[1]);
            graph.putIfAbsent(startVertex, new ArrayList<>());
            graph.get(startVertex).add(endVertex);
        }

        return graph;
    }

    public static Map<Integer, List<WeightedDestination>> readWeightedUndirectedGraphFromFile(String filePath) throws FileNotFoundException {
        InputStream inputStream = readFileAsStream(filePath);
        Scanner in = new Scanner(inputStream);
        String input;
        String[] lineData, edgeData;
        int startVertex, endVertex, weight, length;

        Map<Integer, List<WeightedDestination>> graph = new HashMap<>();

        while(in.hasNextLine()) {
            input = in.nextLine();
            lineData = input.split("\\s+");
            startVertex = Integer.parseInt(lineData[0]);
            graph.putIfAbsent(startVertex, new ArrayList<>());
            length = lineData.length;
            for(int i = 1; i < length; i++) {
                edgeData = lineData[i].split(",");
                endVertex = Integer.parseInt(edgeData[0]);
                weight = Integer.parseInt(edgeData[1]);
                graph.get(startVertex).add(new WeightedDestination(endVertex, weight));
            }
        }

        return graph;
    }

    public static int[] copyElements(int[] arr, int start, int end) {
        int[] result = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            result[i - start] = arr[i];
        }
        return result;
    }

    public static int[] getLargeArrayForSorting(List<Integer> numbers) {
        return numbers.stream().mapToInt(i -> i).toArray();
    }

    public static int[] getSmallArray() {
        return new int[]{3, 8, 2, 5, 1, 4, 7, 6};
    }
}
