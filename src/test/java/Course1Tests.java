import Course1.KaratsubaMultiplication;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
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
            Assert.assertEquals(result.toString(), input[2]);
        }
    }

}
