import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/luck-balance/problem

class Result {

    /*
     * Complete the 'luckBalance' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. 2D_INTEGER_ARRAY contests
     */

    public static int luckBalance(int k, List<List<Integer>> contests) {
    // Write your code here
        List<Integer> importantContestsLuckList = new ArrayList<>();
        int luckSum = 0;
        for (List<Integer> contest : contests) {
            if (contest.get(1) == 1) {
                importantContestsLuckList.add(contest.get(0));
            } else {
                luckSum += contest.get(0);
            }
        }

        Collections.sort(importantContestsLuckList, Collections.reverseOrder());
        // lose max k important (so add to luck sum)
        for (int i=0; i < importantContestsLuckList.size(); i++) {
            if (i < k) {
                luckSum += importantContestsLuckList.get(i);
                continue;
            }
            luckSum -= importantContestsLuckList.get(i);
        }

        return luckSum;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> contests = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] contestsRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> contestsRowItems = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowTempItems[j]);
                contestsRowItems.add(contestsItem);
            }

            contests.add(contestsRowItems);
        }

        int result = Result.luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
