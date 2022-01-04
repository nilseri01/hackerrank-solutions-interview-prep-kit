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

// https://www.hackerrank.com/challenges/frequency-queries/problem

public class Solution {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> dbMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> answerList = new ArrayList<>();
        Integer count = null;
        for (List<Integer> query: queries) {
            Integer value = query.get(1);
            switch(query.get(0)) {
                case 1:
                    count = dbMap.getOrDefault(value, 0);
                    // do not mind zeros
                    countMap.put(count, countMap.getOrDefault(count, 1) - 1);
                    
                    count = count + 1;
                    dbMap.put(value, count);
                    countMap.put(count, countMap.getOrDefault(count, 0) + 1);
                    
                    break;
                case 2:
                    if (dbMap.getOrDefault(value, 0) > 0) {
                        count = dbMap.get(value);
                        countMap.put(count, countMap.get(count) - 1);
                        
                        count = count - 1;
                        // do not mind zeros
                        dbMap.put(value, count);
                        countMap.put(count, countMap.getOrDefault(count, 0) + 1);
                    }
                    break;
                case 3:
                    answerList.add(countMap.getOrDefault(value, 0) > 0 ? 1 : 0);
                    break;
            }
        }
        return answerList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
