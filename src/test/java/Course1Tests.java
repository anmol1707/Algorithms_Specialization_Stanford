import Course1.CountInversions;
import Course1.KaratsubaMultiplication;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Course1Tests {

    @Test
    public void karatsubaMultiplicationTest() throws FileNotFoundException {
        InputStream inputStream = HelperFunctions.readFileAsStream("Course1TestResources/KaratsubaMultiplicationInput.txt");
        Scanner in = new Scanner(inputStream);
        KaratsubaMultiplication multiplication = new KaratsubaMultiplication();

        while(in.hasNextLine()) {
            String[] input = in.nextLine().split(",");

            BigInteger result = multiplication.doKaratsuba(input[0], input[1]);
            Assert.assertEquals(input[2], result.toString());
        }
    }

    @Test
    public void countInversionsTest() throws FileNotFoundException {
        InputStream inputStream = HelperFunctions.readFileAsStream("Course1TestResources/CountInversionsTest.txt");
        Scanner in = new Scanner(inputStream);
        CountInversions countInversions = new CountInversions();

        List<Integer> numbers = new ArrayList<>();
        while(in.hasNextInt()) {
            numbers.add(in.nextInt());
        }

        int[] arr = numbers.stream().mapToInt(i -> i).toArray();
        Assert.assertEquals(2407905288L, countInversions.countInversionsFast(arr));

        // to avoid referencing issues as countInversionsFast sorts the array while counting inversions
        arr = numbers.stream().mapToInt(i -> i).toArray();
        Assert.assertEquals(2407905288L, countInversions.countInversionsSlow(arr));
    }
}
