import java.util.*;

public class Solution {
  
  // https://www.hackerrank.com/challenges/ctci-fibonacci-numbers/problem

    public static int fibonacci(int n) {
        // Complete the function.
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n-2) + fibonacci(n-1);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}
