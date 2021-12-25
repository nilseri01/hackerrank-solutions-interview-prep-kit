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

// https://www.hackerrank.com/challenges/ctci-ransom-note/problem

class Result {

    /*
     * Complete the 'checkMagazine' function below.
     *
     * The function accepts following parameters:
     *  1. STRING_ARRAY magazine
     *  2. STRING_ARRAY note
     */

    public static void checkMagazine(List<String> magazine, List<String> note) {
    // Write your code here
       Map<String, Integer> magazineMap = getWordMap(magazine);
       Map<String, Integer> noteMap = getWordMap(note);
       
       for (Map.Entry<String, Integer> entry : noteMap.entrySet()) {
          String key = entry.getKey();
          Integer value = entry.getValue();
          
          Integer count = magazineMap.get(key);
          if (count == null || count < value) {
              System.out.println("No");
              return;
          }
        }
        System.out.println("Yes");
    }

    private static Map<String, Integer> getWordMap(List<String> wordList) {
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        for (String word: wordList) {
            if (wordMap.get(word) != null) {
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }
        return wordMap;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        List<String> magazine = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        List<String> note = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        Result.checkMagazine(magazine, note);

        bufferedReader.close();
    }
}
