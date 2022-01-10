import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/abbr/problem

// https://iawale.medium.com/abbreviation-hackerrank-medium-4601219c07d1
// https://www.youtube.com/watch?v=cl1rOsKxZOI

class Result {

    /*
     * Complete the 'abbreviation' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    public static String abbreviation(String a, String b) {
    // Write your code here
        boolean[][] isValid = new boolean[a.length()+1][b.length()+1];
        isValid[0][0] = true;
        for (int i= 1; i <= a.length(); i++) {
            if (Character.isUpperCase(a.charAt(i - 1))) {
                isValid[i][0] = false;
            } else {
                isValid[i][0] = true;
            }
        }
        
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    isValid[i][j] = isValid[i-1][j-1];
                } else if (Character.toUpperCase(a.charAt(i-1)) == b.charAt(j-1)) {
                    isValid[i][j] = isValid[i-1][j-1] || isValid[i-1][j];
                } else if (Character.isUpperCase(a.charAt(i-1))) {
                    isValid[i][j] = false;
                } else {
                    isValid[i][j] = isValid[i-1][j];
                }
            }
        }
        
        return isValid[a.length()][b.length()]? "YES" : "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();

                String result = Result.abbreviation(a, b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
