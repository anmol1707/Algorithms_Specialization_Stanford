import Course2.Week1.StronglyConnectedComponents;
import Helpers.GeneralHelpers;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.*;

public class Course2Tests {

    @Test
    public void stronglyConnectedComponentsTest1() throws FileNotFoundException {
        Map<Integer, List<Integer>> graph = GeneralHelpers.readGraphFromFileBasic("Course2TestResources/SCC_Small.txt");
        StronglyConnectedComponents scc = new StronglyConnectedComponents();
        List<List<Integer>> connectedComponents = scc.findConnectedComponents(graph);

        String expectedResult = "3,3,3,0,0";
        String actualResult = sccResultToString(connectedComponents);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void stronglyConnectedComponentsTest2() throws FileNotFoundException {
        Map<Integer, List<Integer>> graph = GeneralHelpers.readGraphFromFileBasic("Course2TestResources/SCC_Big.txt");
        StronglyConnectedComponents scc = new StronglyConnectedComponents();
        List<List<Integer>> connectedComponents = scc.findConnectedComponents(graph);

        String expectedResult = "434821,969,459,313,211";
        String actualResult = sccResultToString(connectedComponents);
        Assert.assertEquals(expectedResult, actualResult);
    }

    private String sccResultToString(List<List<Integer>> connectedComponents) {
        List<Integer> componentsLengths = new ArrayList<>();
        for (List<Integer> connectedComponent : connectedComponents) {
            componentsLengths.add(connectedComponent.size());
        }
        while(componentsLengths.size() < 5) {
            componentsLengths.add(0);
        }

        componentsLengths.sort(Comparator.reverseOrder());

        StringJoiner joiner = new StringJoiner(",");
        for(int i = 0; i < 5; i++) {
            joiner.add(String.valueOf(componentsLengths.get(i)));
        }
        return joiner.toString();
    }
}
