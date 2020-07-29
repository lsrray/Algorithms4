package ray.sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick3Way {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);   //消除对输入的依赖
        sort(a,0,a.length-1);
    }

    /**
     * 三向切分的快速排序，处理重复元素数组，
     * 切分中lo-lt<v,le=v,i-gt不确定,gt-hi>v，
     * 切分后lo-le<v,[le-gt]=v,gt-hi>v
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo)
            return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i<=gt){
            int cmp = a[i].compareTo(v);
            if (cmp < 0)
                exch(a,lt++,i++);
            else if(cmp > 0)
                exch(a,i,gt--);
            else
                i++;
        }
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);
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
        //String a[] = {"M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E"};
        String a[] = {"R","B","W","W","R","W","B","R","R","W","B","R"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
