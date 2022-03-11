import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/special-palindrome-again/problem

public class Solution {

    // Complete the substrCount function below.
     static long substrCount(int n, String s) {
        long count = n;
        // get for 2 and 3 letters
        for (int i = 2; i <= 3; i++) {
            for (int j = 0; j <= n - i; j++) {
                String subStr = s.substring(j, j + i);
                boolean isPalindromic = subStr.charAt(0) == subStr.charAt(i - 1);
                if (isPalindromic) {
                    count ++;
                    int k = 1;
                    while ((j - k > -1) && ((j + i - 1 + k) < n) &&
                            s.charAt(j - k) == s.charAt(j + i - 1 + k) &&
                            s.charAt(j) == s.charAt(j - k)) {
                        count++;
                        k++;
                    }
                }
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
