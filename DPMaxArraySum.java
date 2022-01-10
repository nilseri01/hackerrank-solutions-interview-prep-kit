import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/max-array-sum/problem

public class Solution {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        if (arr.length == 1) {
            return arr[0] > 0 ? arr[0] : 0;
        } else if (arr.length == 2) {
            int max = Math.max(arr[0], arr[1]);
            return max > 0 ? max: 0;
        } else {
            int[] sum = new int[arr.length];
            sum[0] = arr[0];
            sum[1] = Math.max(arr[0], arr[1]);
            for(int i = 2; i < arr.length; i++) {
                sum[i] = Math.max(arr[i], Math.max(arr[i] + sum[i - 2], sum[i - 1]));
            }
            int max = sum[arr.length - 1];
            return max > 0 ? max: 0;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
