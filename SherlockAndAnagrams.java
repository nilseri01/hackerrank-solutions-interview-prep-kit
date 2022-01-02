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

// https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem

class Result {

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    

    public static int sherlockAndAnagrams(String s) {
    // Write your code here
        int count = 0;
        for (int k=1; k<s.length(); k++) {
            for (int i=0; i<s.length()-k; i++) {
                String subStr1 = s.substring(i, i+k);
                Map<Character, Integer> charMap = new HashMap<Character, Integer>();
                subStr1.chars().forEach(c -> {
                    if (charMap.get((char) c) == null) {
                        charMap.put((char) c, 0);
                    }
                    charMap.put((char) c, charMap.get((char) c)+1);
                });
                        
                for (int j=i+1; j<s.length()-k+1; j++) {
                    HashMap<Character, Integer> charMapCopy = new HashMap<>(charMap);              
                    String subStr2 = s.substring(j, j+k);
                    subStr2.chars().forEach(c -> {
                        if (charMapCopy.get((char) c) != null && charMapCopy.get((char) c) > 0) {
                            charMapCopy.put((char) c, charMapCopy.get((char) c)-1);
                        }
                    });
                    Integer sum = charMapCopy.values().stream().mapToInt(Integer::intValue).sum();
                    count = count + (sum == 0 ? 1 : 0);
                }
            } 
        }
        return count;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.sherlockAndAnagrams(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
