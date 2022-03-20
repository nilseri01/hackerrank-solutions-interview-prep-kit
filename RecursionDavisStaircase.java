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

// https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem

class Result {

    final static int MODULO_VALUE = 1000000007;
    /*
     * Complete the 'stepPerms' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER n as parameter.
     */
    private static int stepPerms(int n, int[] steps) {
        if(n < 3) {
            return (n > 0) ? n : 0;
        }
        
        if(steps[n] == 0) {
            steps[n] = (stepPerms(n - 1, steps)
                        + stepPerms(n - 2, steps)
                        + stepPerms(n - 3, steps)) % MODULO_VALUE;
                                
        }
        return steps[n];  
    }
    
    public static int stepPerms(int n) {
    // Write your code here
        int[] steps = new int[n + 1];
        int count = 1;
        for(int i = 1; i < n; i++) {
            count += stepPerms(i, steps);
        }
        return count;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, s).forEach(sItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                int res = Result.stepPerms(n);

                bufferedWriter.write(String.valueOf(res));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
