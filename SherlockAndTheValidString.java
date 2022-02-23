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

// https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
    // Write your code here
        long[] charArray = new long[26];
        for (int i = 0; i < s.length(); i++) {
            charArray[s.charAt(i) - 'a']++;
        }
        
        long existingCharCount = 0;
        for (int i = 0; i < 26; i++) {
            existingCharCount += (charArray[i] == 0 ? 0 : 1);
        }
        
        Set<Long> charSet = new HashSet<Long>();
        long singleCharCounter = 0;
        for (int i = 0; i < 26; i++) {
            if (charArray[i] != 0) {
                charSet.add(charArray[i]);
            }
            if (charArray[i] == 1) {
                singleCharCounter++;
            }
        }
        
        if (charSet.size() > 2) {
            return "NO";
        }
        
        if (charSet.size() == 1) {
            return "YES";
        }
        
        if (singleCharCounter > 1 && (singleCharCounter + 1) != existingCharCount) {
            return "NO";
        }
        
        List<Long> charList = new ArrayList<>(charSet);
        if (charSet.size() == 2 && 
            (charList.contains(new Long("1"))) && 
            singleCharCounter == 1) {
            return "YES";
        }
        
        if (Math.abs(charList.get(0) - charList.get(1)) == 1) {
            return "YES";
        }
        
        return "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
