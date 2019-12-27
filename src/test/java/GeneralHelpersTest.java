import Helpers.GeneralHelpers;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class GeneralHelpersTest {

    @Test(expected = FileNotFoundException.class)
    public void fileNotFoundTest1() throws FileNotFoundException {
        InputStream inputStream = GeneralHelpers.readFileAsStream("some_non_existing_filename.txt");
        System.out.println(inputStream);
    }

    @Test
    public void readThreeLinesCorrectlyTest() throws FileNotFoundException {
        InputStream inputStream = GeneralHelpers.readFileAsStream("HelperFunctionsTestResources/TestReadFileAsStream.txt");
        String[] expectedInput = {"line1", "line2", "line3"};
        int counter = 0;
        Scanner in = new Scanner(inputStream);
        while(in.hasNextLine()) {
            String input = in.nextLine();
            assertEquals(expectedInput[counter++], input);
        }
        assertEquals(3, counter);
    }

    @Test
    public void readEmptyFileTest() throws FileNotFoundException {
        InputStream inputStream = GeneralHelpers.readFileAsStream("HelperFunctionsTestResources/EmptyFile.txt");
        Scanner in = new Scanner(inputStream);
        int counter = 0;
        while(in.hasNextLine()) {
            counter++;
        }
        assertEquals(0, counter);
    }
}
