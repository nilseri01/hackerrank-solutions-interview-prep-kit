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

// https://www.hackerrank.com/challenges/count-triplets-1/problem

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long tripletCount = 0;
        Map<Long, Long> prevNumberList = new HashMap<>();
        Map<Long, Long> nextNumberMap = new HashMap<>();
        
        for (int i=0; i<arr.size(); i++) {
            long num = arr.get(i);
            if (num % r == 0L) {
                nextNumberMap.put(num, nextNumberMap.getOrDefault(num, 0L) + 1);
            }
        }
        
        for (int i=0; i<arr.size(); i++) {
            long num = arr.get(i);
            if (nextNumberMap.get(num) != null) {
                nextNumberMap.put(num, nextNumberMap.getOrDefault(num, 0L) - 1);
            }
            
            if (num % r != 0L) {
                prevNumberList.put(num, prevNumberList.getOrDefault(num, 0L) + 1);
                continue;
            }
            
            Long prev = num / r;
            long prevCount = prevNumberList.getOrDefault(prev, 0L);
            if (prevCount == 0L) {
                prevNumberList.put(num, prevNumberList.getOrDefault(num, 0L) + 1); 
                continue;
            }
            
            Long next = num * r;
            long nextCount = nextNumberMap.getOrDefault(next, 0L);
            
            tripletCount += (prevCount * nextCount);
            
            prevNumberList.put(num, prevNumberList.getOrDefault(num, 0L) + 1);       
        }
        return tripletCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
