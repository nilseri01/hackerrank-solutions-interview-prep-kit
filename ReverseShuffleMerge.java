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

// https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem
// updated version of solution => https://sungjun221.github.io/algorithm/reverse-shuffle-merge/

class Result {

    /*
     * Complete the 'reverseShuffleMerge' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String reverseShuffleMerge(String s) {
    // Write your code here
        HashMap<Character, Integer> addedCharacters = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            addedCharacters.put(s.charAt(i), 
                    addedCharacters.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        for (Character c : addedCharacters.keySet()) {
            addedCharacters.put(c, addedCharacters.get(c) / 2);
        }
        
        HashMap<Character, Integer> skippedCharacters = (HashMap) addedCharacters.clone();
        Stack<Character> stack = new Stack<>();
        for (int i=s.length()-1; i>=0; i--) {
            Character character = s.charAt(i);
            
            while (!stack.empty() && stack.peek() > character 
            && addedCharacters.get(character) > 0 
            && skippedCharacters.get(stack.peek()) > 0) {
                Character stackCharacter = stack.pop();
                addedCharacters.put(stackCharacter, addedCharacters.get(stackCharacter) + 1);
                skippedCharacters.put(stackCharacter, skippedCharacters.get(stackCharacter) - 1);
            }

            if (addedCharacters.get(character) > 0) {
                stack.push(character);
                addedCharacters.put(character, addedCharacters.get(character) - 1);
            } else {
                skippedCharacters.put(character, skippedCharacters.get(character) - 1);
            }
        }

        String word = "";
        while(!stack.empty()){
            word = stack.pop() + word;
        }
        return word;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.reverseShuffleMerge(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
