import Course2.Week1.StronglyConnectedComponents;
import Course2.Week2.Dijkstras;
import Course2.Week2.WeightedDestination;
import Course2.Week3.MedianMaintenance;
import Helpers.GeneralHelpers;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Course2Tests {

    @Test
    public void stronglyConnectedComponentsTest1() throws FileNotFoundException {
        Map<Integer, List<Integer>> graph = GeneralHelpers.readBasicDirectedGraphFromFile("Course2TestResources/SCC_Small.txt");
        StronglyConnectedComponents scc = new StronglyConnectedComponents();
        List<List<Integer>> connectedComponents = scc.findConnectedComponents(graph);

        String expectedResult = "3,3,3,0,0";
        String actualResult = sccResultToString(connectedComponents);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void stronglyConnectedComponentsTest2() throws FileNotFoundException {
        Map<Integer, List<Integer>> graph = GeneralHelpers.readBasicDirectedGraphFromFile("Course2TestResources/SCC_Big.txt");
        StronglyConnectedComponents scc = new StronglyConnectedComponents();
        List<List<Integer>> connectedComponents = scc.findConnectedComponents(graph);

        String expectedResult = "434821,969,459,313,211";
        String actualResult = sccResultToString(connectedComponents);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void dijkstrasTest() throws FileNotFoundException {
        Map<Integer, List<WeightedDestination>> graph = GeneralHelpers.readWeightedUndirectedGraphFromFile("Course2TestResources/DijkstraData.txt");
        final int startVertex = 1;
        Dijkstras dijkstras = new Dijkstras();
        Map<Integer, Integer> shortestPaths = dijkstras.findShortestPath(graph, startVertex);

        String expectedResult = "2599,2610,2947,2052,2367,2399,2029,2442,2505,3068";
        String actualResult = dijkstrasResultToString(shortestPaths);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void medianMaintenanceTest() throws FileNotFoundException {
        InputStream inputStream = GeneralHelpers.readFileAsStream("Course2TestResources/MedianInputData.txt");
        MedianMaintenance medianMaintenance = new MedianMaintenance();
        List<Integer> medians = medianMaintenance.getMedians(inputStream);
        int actualSum = sumOfMedians(medians, 10000);
        int expectedSum = 1213;
        Assert.assertEquals(expectedSum, actualSum);
    }

    private int sumOfMedians(List<Integer> medians, int mod) {
        int sum = 0;
        for (Integer median : medians) {
            sum = (sum + median) % mod;
        }
        return sum;
    }

    private String sccResultToString(List<List<Integer>> connectedComponents) {
        List<Integer> componentsLengths = new ArrayList<>();
        for (List<Integer> connectedComponent : connectedComponents) {
            componentsLengths.add(connectedComponent.size());
        }
        while (componentsLengths.size() < 5) {
            componentsLengths.add(0);
        }

        componentsLengths.sort(Comparator.reverseOrder());

        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < 5; i++) {
            joiner.add(String.valueOf(componentsLengths.get(i)));
        }
        return joiner.toString();
    }

    private String dijkstrasResultToString(Map<Integer, Integer> shortestPaths) {
        int[] requiredDestinations = {7, 37, 59, 82, 99, 115, 133, 165, 188, 197};
        StringJoiner joiner = new StringJoiner(",");
        for (int endVertex : requiredDestinations) {
            joiner.add(String.valueOf(shortestPaths.get(endVertex)));
        }
        return joiner.toString();
    }
}
