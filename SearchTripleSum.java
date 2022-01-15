import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/triple-sum/problem
// getCount method implemented with the help of getValidIndex method in 
//   https://shareablecode.com/snippets/java-solution-for-hackerrank-problem-triple-sum-Kg2N-Ft6f

public class Solution {

    static long getCount(List<Integer> numberList, Integer q) {
        int start = 0;
        int end = numberList.size() - 1;
        
        long count = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (numberList.get(mid) <= q) {
                count = mid;
                start = mid + 1;
            } else
                end = mid - 1;
        }
        return count + 1;

    }
    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        Set<Integer> qSet= new HashSet<>();
        Integer max = 0;
        for (int i=0; i < b.length; i++) {
            qSet.add(new Integer(b[i]));
            if (max < b[i]) {
                max = b[i];
            }
        }
        List<Integer> qList = new ArrayList<>(qSet);
        Collections.sort(qList);
        
        Set<Integer> pSet = new HashSet<>();
        for (int i=0; i < a.length; i++) {
            if (max >= a[i]) {
                pSet.add(new Integer(a[i]));
            }
        }
        List<Integer> pList = new ArrayList<>(pSet);
        Collections.sort(pList);
        
        Set<Integer> rSet = new HashSet<>();
        for (int i=0; i < c.length; i++) {
            if (max >= c[i]) {
                rSet.add(new Integer(c[i]));
            }
        }
        List<Integer> rList = new ArrayList<>(rSet);
        Collections.sort(rList);
        
        long count = 0;
        for (Integer q: qList) {
            long pCount = getCount(pList, q);
            long rCount = getCount(rList, q);
            
            count = count + (pCount * rCount);
        }
        
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
