package ray.sort;

public class MaxPQ<key extends Comparable<key>> {

    private key[] pq; //基于堆的完全二叉树
    private int N = 0; //存储于qp[1...N]之间，pq[0]没有使用

    public MaxPQ(int maxN) {
        pq = (key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N==0;
    }

    public int size() {
        return N;
    }

    public   boolean less (int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch( int i, int j) {
        key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k>1 && less(k/2,k)){
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N){
            int j = k+k;
            if (j < N && less(j,j+1))
                j = j+1;
            if (!less(k,j))
                break;
            exch(k,j);
            k=j;
        }
    }

    public void insert(key v) {
        pq[++N] = v;
        swim(N);
    }

    public key delMax() {
        key max = pq[1];
        exch(1,N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    public void showPQ() {
        for (int i = 0; i < N+1; i++) {
            System.out.println(pq[i]);
        }
    }

    public static void main(String[] args) {
        MaxPQ<String> maxPQ = new MaxPQ<String>(10);
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        maxPQ.delMax();
        maxPQ.insert("X");
        maxPQ.insert("A");
        maxPQ.insert("M");
        maxPQ.delMax();
        maxPQ.insert("P");
        maxPQ.insert("L");
        maxPQ.insert("E");
        maxPQ.delMax();
        maxPQ.showPQ();
    }
}
