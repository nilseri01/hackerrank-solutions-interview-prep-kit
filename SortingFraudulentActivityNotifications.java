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

// https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem
// solved with "counting sort" https://medium.com/weekly-webtips/sorting-code-challenge-breakdown-19229b32be02

class Result {

    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */
     
    private static int getMedianMultipliedByTwo(int[] countArr, int d) {
        int sum = 0;
        for (int i = 0; i < countArr.length; i++) {
            sum += countArr[i];
            if (sum * 2 == d) {
                return (i * 2 + 1);
            }
            if (sum * 2 > d) {
               return (i * 2); 
            }
        }
        return 0;
    }

    public static int activityNotifications(List<Integer> expenditure, int d) {
    // Write your code here
        int[] countArr = new int[201];
        int noticeCount = 0;
        for (int i = 0; i < d; i++) {
            countArr[expenditure.get(i)]++;
        }
        for (int i = d; i < expenditure.size(); i++) {
            int threshold = getMedianMultipliedByTwo(countArr, d);
            if (expenditure.get(i) >= threshold) {
                noticeCount++;
            }
            if (i == expenditure.size() - 1) {
                break;
            }
            countArr[expenditure.get(i - d)]--;
            countArr[expenditure.get(i)]++;
        }
        return noticeCount;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
