import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem

public class Solution {
    
    private static class MyQueue<T> {
        private Stack<T> front = new Stack<>();
        private Stack<T> end = new Stack<>();
        
        public void transfer() {
            while(!end.empty()) {
                front.push(end.pop());
            }
        }
        
        public void enqueue(T n) {
            end.push(n);
        }
        
        public T dequeue() {
            if (front.empty()) {
                transfer();
            }
            return front.pop();
        }
        
        public T peek() {
            if (front.empty()) {
                transfer();
            }
            return front.peek();
        }
    }
    
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.dequeue();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}
