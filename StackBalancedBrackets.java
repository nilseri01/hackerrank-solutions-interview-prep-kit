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

// https://www.hackerrank.com/challenges/balanced-brackets/problem

class Result {

    /*
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isBalanced(String s) {
    // Write your code here
        Map<Character, Character> openCloseMap = new HashMap<>();
        openCloseMap.put('}', '{');
        openCloseMap.put(']', '[');
        openCloseMap.put(')', '(');
        
        Stack<Character> charStack = new Stack<>();
        
        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; i++) {
            if (openCloseMap.get(chars[i]) == null) {
                charStack.push(chars[i]);
            } else {
                Character openChar = openCloseMap.get(chars[i]);
                if (charStack.empty()) {
                    return "NO";
                }
                if (charStack.pop() != openChar) {
                    return "NO";
                }
            }
        }
        return charStack.empty() ? "YES" : "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
