package ray.sort;

public class Heap0 {

    //private Comparable[] pq;
    //基于堆的完全二叉树
    private static int N = 0; //存储于qp[1...N]之间，pq[0]没有使用

    public static boolean isEmpty() {
        return N == 0;
    }

    public static int size() {
        return N;
    }

    public static boolean less(Comparable[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public static void exch(Comparable[] pq, int i, int j) {
        Comparable t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private static void swim(Comparable[] pq, int k) {
        while (k > 1 && less(pq, k / 2, k)) {
            exch(pq, k / 2, k);
            k = k / 2;
        }
    }

    private static void sink(Comparable[] pq, int k) {
        //2k and 2k+1  0-1,2  1,3-4  2-5,6
        //(k+1)*2 and (k+1)*2-1 = 2*k+2 and 2*k + 1
        while (2 * k + 1 <= N) {
            int j = 2 * k + 1;
            if (j < N && less(pq, j, j + 1))
                j = j + 1;
            if (!less(pq, k, j))
                break;
            exch(pq, k, j);
            k = j;
        }
    }

    public static void insert(Comparable[] pq, Comparable v) {
        pq[++N] = v;
        swim(pq, N);
    }

    public static Comparable delMax(Comparable[] pq) {
        Comparable max = pq[1];
        exch(pq, 1, N--);
        pq[N + 1] = null;
        sink(pq, 1);
        return max;
    }


    public static void sort(Comparable[] pq) {
        N = pq.length - 1;
        for (int k = N / 2 - 1; k >= 0; k--) {
            sink(pq, k);
        }

        while (N > 0) {
            exch(pq, 0, N--);
            sink(pq, 0);
        }
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String a[] = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        show(a);
    }

}
