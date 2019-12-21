package Course1;

import java.math.BigInteger;
import java.util.Scanner;

public class KaratsubaMultiplication {

    public BigInteger doKaratsuba(String s1, String s2) {
        BigInteger num1 = new BigInteger(s1);
        BigInteger num2 = new BigInteger(s2);

        return processInput(num1, num2);
    }

    private BigInteger processInput(BigInteger num1, BigInteger num2) {
        int length1 = num1.toString().length();
        int length2 = num2.toString().length();

        // Basic operation of multiplying two 1-digit numbers
        if(length1 == 1 && length2 == 1) {
            return num1.multiply(num2);
        }

        BigInteger powOfTen = BigInteger.TEN.pow(Math.max(length1, length2) / 2);

        /*
            Divide and conquer
            num1 is divided as num = (a * powOfTen) + b
            num2 is divided as num = (c * powOfTen) + d
        */
        BigInteger a = num1.divide(powOfTen);
        BigInteger b = num1.mod(powOfTen);
        BigInteger c = num2.divide(powOfTen);
        BigInteger d = num2.mod(powOfTen);

        // 3 recursive calls
        BigInteger ac = processInput(a, c);
        BigInteger bd = processInput(b, d);
        BigInteger a_plus_b_times_c_plus_d = processInput(a.add(b), c.add(d));
        // This value represents ac + ad  + bc + bd

        BigInteger ad_plus_bc = a_plus_b_times_c_plus_d.subtract(ac).subtract(bd);

        // The product num1 * num2 is calculated as (10^n).(ac) + bd + {10^(n/2)}.(ad + bc)
        return ac.multiply(powOfTen.pow(2)).add(bd).add(powOfTen.multiply(ad_plus_bc));
    }

    public static void main(String[] args) {
        KaratsubaMultiplication multiplication = new KaratsubaMultiplication();

        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();

        BigInteger result = multiplication.doKaratsuba(s1, s2);
        System.out.println(result.toString());
    }
}
