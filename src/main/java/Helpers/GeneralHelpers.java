package Helpers;

import Course1.Week4.ContractedVertex;

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

    public static Map<ContractedVertex, List<ContractedVertex>> readGraphFromFileAdvanced(String filePath) throws FileNotFoundException {
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

    public static Map<Integer, List<Integer>> readGraphFromFileBasic(String filePath) throws FileNotFoundException {
        InputStream inputStream = readFileAsStream(filePath);
        Scanner in = new Scanner(inputStream);
        String input;
        String[] vertices;
        int start, end;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        while(in.hasNextLine()) {
            input = in.nextLine();
            vertices = input.split(" ");
            start = Integer.parseInt(vertices[0]);
            end = Integer.parseInt(vertices[1]);
            graph.putIfAbsent(start, new ArrayList<>());
            graph.get(start).add(end);
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
}
