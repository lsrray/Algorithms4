package ray.sort;

/**
 * 自低向上归并排序，先归并微型数组，然后在成对归并得到子数组
 */
public class MergeBU {

    private static Comparable[] aux; //归并所用的辅助数组

    /**
     * 归并两个有序数组：
     * 从辅助数组aux中取元素，归并到数组a中，
     * 1.左半边用尽取右半边，2.右半边用尽取左半边，
     * 3.右半边的当前元素小于左半边的当前元素取右半边元素
     * 4.右半边的当前元素大于左半边的当前元素取左半边元素
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi ){
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <=hi ; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz+=sz) {
            for (int lo = 0; lo < N - sz; lo += sz+sz) {// N-sz: 确保能分出两个数组归并
                merge(a, lo,lo + sz - 1, Math.min(lo+2*sz-1,N-1));
            }
        }
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
