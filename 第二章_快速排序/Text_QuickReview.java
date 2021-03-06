package 第二章_快速排序;

import static 第二章_初级排序算法.Text_Array.*;

import java.util.Arrays;

import edu.princeton.cs.algs4.*;

public class Text_QuickReview {
    public static double quick(int[] a) {
        Stopwatch timer = new Stopwatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }
    private static void exch(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    private static void compare_exchange(int[] a, int i, int j) {
        if (a[i] > a[j]) exch(a, i, j);
    }
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int t = a[i], j;
            for (j = i - 1; j >= 0 && t < a[j]; j--)
                a[j + 1] = a[j];
            a[j + 1] = t;
        }
    }
    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i - 1]) return false;
        return true;
    }
    private static boolean log = false;
    static int M = 3;
    public static void quick(int[] a, int lo, int hi) {
        
            if (hi - lo  < 3) {
                insertion(a, lo, hi);
                return;
            }
            
            int mid = (lo + hi) / 2;
            if (a[mid] < a[lo]) exch(a, mid, lo);
            if (a[hi] < a[lo])  exch(a, hi, lo);
            if (a[mid] < a[hi]) exch(a, mid, hi);
            
            if (log) {
                StdOut.printf("三分取样后 : %d %d %d", lo, mid, hi);
                print(a);
                StdOut.println();
            }
            
          int v = a[hi];
          int i = lo - 1, p = lo - 1 , j = hi, q = hi;
          while (true) {
            while (a[++i] < v);
            while (a[--j] > v);
            if (i >= j) break;
            
            if (log) {
                StdOut.print("=======================");
                print(a);
                StdOut.printf("交换 %d %d", i, j);
            }
            exch(a, i, j);
            
            if (log) {
                print(a);
                StdOut.print("=======================\n");
            }
            
            if (a[i] == v) { exch(a, ++p, i); if (log) { StdOut.printf("左边 p = %d i = %d\n", p, i); }}
            if (a[j] == v) { exch(a, --q, j); if (log) { StdOut.printf("右边 q = %d j = %d\n", p, j); }}
          
          }
            if (log) {
              StdOut.print("++++++++++++++++++++++++++");
              print(a);
              StdOut.printf("把 %d 移动到合适位置 %d", hi, i);
            }
            
          exch(a, i, hi);
          
          if (log) {
              print(a);
              StdOut.print("++++++++++++++++++++++++++\n");
            }
          
          j = i - 1;
          i = i + 1;
          
          if (log) {
              StdOut.printf("当前边界 lo = %d hi = %d\n", lo, hi);
              StdOut.printf("移动元素前 : j = %d i = %d", j, i);
              print(a);
              StdOut.println();
          }
          
          int k = lo, m = hi - 1;;
          while (k <= p) exch(a, k++, j--);
          while (m >= q) exch(a, m--, i++);
          
          if (log) {
              StdOut.printf("移动元素后 : j = %d i = %d", j, i);
              print(a);
              StdOut.println();
          }
          
          quick(a, lo, j);
          quick(a, i, hi);
    }
    public static void correctTest() {
        while (true) {
            int[] a = intsVrgWithEachAmount(100, 1, 2, 3, 4, 5);
            int[] copy = intsCopy(a);
            quick(a);
            if (!isSorted(a)) {
                print(copy);
                assert isSorted(copy);
                break;
            }
        }
    }
    public static void main(String[] args) {
        log = false;
        int[] a = parseInts("3   5   3   4   2   2   2   1   4   4   1   5   5   1   3  ");  
        quick(a);
        assert isSorted(a);
    }
    // output
    /*
     *  三分取样后 : 0 9 19
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        3   3   3   1   4   2   5   5   1   5   2   1   3   5   1   2   2   4   4   4   
        
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        3   3   3   1   4   2   5   5   1   5   2   1   3   5   1   2   2   4   4   4   
        交换 4 18
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        3   3   3   1   4   2   5   5   1   5   2   1   3   5   1   2   2   4   4   4   
        =======================
        左边 p = 0 i = 4
        右边 q = 0 j = 18
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   3   3   1   3   2   5   5   1   5   2   1   3   5   1   2   2   4   4   4   
        交换 6 17
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   3   3   1   3   2   4   5   1   5   2   1   3   5   1   2   2   5   4   4   
        =======================
        左边 p = 1 i = 6
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   4   3   1   3   2   3   5   1   5   2   1   3   5   1   2   2   5   4   4   
        交换 7 16
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   4   3   1   3   2   3   2   1   5   2   1   3   5   1   2   5   5   4   4   
        =======================
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   4   3   1   3   2   3   2   1   5   2   1   3   5   1   2   5   5   4   4   
        交换 9 15
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   4   3   1   3   2   3   2   1   2   2   1   3   5   1   5   5   5   4   4   
        =======================
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   4   3   1   3   2   3   2   1   2   2   1   3   5   1   5   5   5   4   4   
        交换 13 14
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   4   3   1   3   2   3   2   1   2   2   1   3   1   5   5   5   5   4   4   
        =======================
        ++++++++++++++++++++++++++
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   4   3   1   3   2   3   2   1   2   2   1   3   1   5   5   5   5   4   4   
        把 19 移动到合适位置 14
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   4   3   1   3   2   3   2   1   2   2   1   3   1   4   5   5   5   4   5   
        ++++++++++++++++++++++++++
        当前边界 lo = 0 hi = 19
        移动元素前 : j = 13 i = 15
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        4   4   3   1   3   2   3   2   1   2   2   1   3   1   4   5   5   5   4   5   
        
        移动元素后 : j = 11 i = 16
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   3   3   1   3   2   3   2   1   2   2   1   4   4   4   4   5   5   5   5   
        
        三分取样后 : 0 5 11
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   3   3   1   3   2   3   2   1   2   2   1   4   4   4   4   5   5   5   5   
        
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   3   3   1   3   2   3   2   1   2   2   1   4   4   4   4   5   5   5   5   
        交换 0 8
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   3   3   1   3   2   3   2   1   2   2   1   4   4   4   4   5   5   5   5   
        =======================
        左边 p = 0 i = 0
        右边 q = 0 j = 8
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   3   3   1   3   2   3   2   2   2   1   1   4   4   4   4   5   5   5   5   
        交换 1 3
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   3   3   3   2   3   2   2   2   1   1   4   4   4   4   5   5   5   5   
        =======================
        左边 p = 1 i = 1
        ++++++++++++++++++++++++++
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   3   3   3   2   3   2   2   2   1   1   4   4   4   4   5   5   5   5   
        把 11 移动到合适位置 2
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   3   3   2   3   2   2   2   1   3   4   4   4   4   5   5   5   5   
        ++++++++++++++++++++++++++
        当前边界 lo = 0 hi = 11
        移动元素前 : j = 1 i = 3
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   3   3   2   3   2   2   2   1   3   4   4   4   4   5   5   5   5   
        
        移动元素后 : j = -1 i = 4
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   3   2   3   2   2   2   3   3   4   4   4   4   5   5   5   5   
        
        三分取样后 : 4 7 11
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   3   3   2   2   3   3   4   4   4   4   5   5   5   5   
        
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   3   3   2   2   3   3   4   4   4   4   5   5   5   5   
        交换 6 10
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   3   3   2   2   3   3   4   4   4   4   5   5   5   5   
        =======================
        左边 p = 4 i = 6
        右边 q = 4 j = 10
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   3   2   2   3   2   2   3   3   4   4   4   4   5   5   5   5   
        交换 7 9
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   3   2   2   2   2   3   3   3   4   4   4   4   5   5   5   5   
        =======================
        右边 q = 4 j = 9
        ++++++++++++++++++++++++++
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   3   2   2   2   2   3   3   3   4   4   4   4   5   5   5   5   
        把 11 移动到合适位置 9
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   3   2   2   2   2   3   3   3   4   4   4   4   5   5   5   5   
        ++++++++++++++++++++++++++
        当前边界 lo = 4 hi = 11
        移动元素前 : j = 8 i = 10
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   3   2   2   2   2   3   3   3   4   4   4   4   5   5   5   5   
        
        移动元素后 : j = 7 i = 12
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        
        三分取样后 : 4 5 7
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        交换 4 6
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        =======================
        左边 p = 4 i = 4
        右边 q = 4 j = 6
        ++++++++++++++++++++++++++
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        把 7 移动到合适位置 5
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        ++++++++++++++++++++++++++
        当前边界 lo = 4 hi = 7
        移动元素前 : j = 4 i = 6
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        
        移动元素后 : j = 3 i = 7
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        
        三分取样后 : 16 17 19
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        
        =======================
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        交换 16 18
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        =======================
        左边 p = 16 i = 16
        右边 q = 16 j = 18
        ++++++++++++++++++++++++++
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        把 19 移动到合适位置 17
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        ++++++++++++++++++++++++++
        当前边界 lo = 16 hi = 19
        移动元素前 : j = 16 i = 18
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5   
        
        移动元素后 : j = 15 i = 19
        0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  
        1   1   1   1   2   2   2   2   3   3   3   3   4   4   4   4   5   5   5   5        
     */
}
