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

// https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem

class Result {

    /*
     * Complete the 'whatFlavors' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY cost
     *  2. INTEGER money
     */

    public static void whatFlavors(List<Integer> cost, int money) {
    // Write your code here
        Map<Integer, List<Integer>> valueIndexMap = new HashMap<>();
        for (int i = 0; i < cost.size(); i++) {
            if (valueIndexMap.get(cost.get(i)) == null) {
                valueIndexMap.put(cost.get(i), new ArrayList<Integer>());
            }
            List<Integer> indicesList = valueIndexMap.get(cost.get(i));
            indicesList.add(new Integer(i));
            valueIndexMap.put(cost.get(i), indicesList);
        }
        
        int[] selectedIndices = new int[2];
        Integer maxTotal = 0;
        for (int i = 0; i < cost.size()-1; i++) {
            Integer first = cost.get(i);
            if (first < money) {
                Integer second = money - first;
                List<Integer> indicesList = 
                    valueIndexMap.getOrDefault(second, new ArrayList<Integer>());
                indicesList.remove(new Integer(i));
                
                Integer total = first + second;
                if (indicesList.size() > 0 && total > maxTotal) {
                    maxTotal = total;
                    selectedIndices[0] = i+1;
                    selectedIndices[1] = indicesList.get(0) + 1;
                }
            }
        }
        if (selectedIndices[0] != 0) {
           System.out.println(selectedIndices[0] + " " + selectedIndices[1]); 
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int money = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.whatFlavors(cost, money);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
