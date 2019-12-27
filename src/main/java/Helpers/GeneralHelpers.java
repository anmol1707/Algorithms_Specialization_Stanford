package Helpers;

import Course1.ContractedVertex;

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

    public static Map<ContractedVertex, List<ContractedVertex>> readGraphFromFile(String filePath) throws FileNotFoundException {
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
}
