package ray.sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);   //消除对输入的依赖
        sort(a,0,a.length-1);
    }

    /**
     * 递归调用切分来排序
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo)
            return;
        int j = partition(a, lo, hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }

    /**
     * 切分后 左边都不大于a[j],右边都不小于a[j]，
     * 对于某个j, a[j]已经排定（位置确定）
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo, j = hi + 1; //左右扫描指针
        Comparable v = a[lo];
        while (true){
            while (less(a[++i],v))
                if(i == hi) break;
            while (less(v,a[--j]))
                if (j == lo) break;

            if (j <= i)
                break;

            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }

    public static  boolean less (Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show (Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]+" ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i-1]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String a[] = {"M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
