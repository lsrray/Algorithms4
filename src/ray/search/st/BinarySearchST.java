package ray.search.st;

public class BinarySearchST<key extends Comparable<key>, Value> {

    private key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    /**
     * 循环结束时 lo的值正好等于表中小于被查找键的数量
     * @param key
     * @return
     */
    public int rank(key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi){
            int mid = lo + (lo + hi)/2;
            int cmp = keys[mid].compareTo(key);
            if (cmp < 0)
                lo = mid +1;
            else if (cmp > 0)
                hi = mid -1;
            else
                return mid;
        }
        return lo;
    }

    public Value get(key key) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else
            return null;
    }

    public void put(key key, Value value) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    public key min(){
        return keys[0];
    }

    public key max(){
        return keys[N-1];
    }

    public key select(int k){
        return keys[k];
    }

    public key ceiling(key key){
        int i = rank(key);
        return keys[i];
    }

    /**
     * 递归的二分查找
     * @param key
     * @param lo
     * @param hi
     * @return
     */
    public int rank(key key, int lo, int hi) {
        if (lo > hi)
            return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = keys[mid].compareTo(key);
        if (cmp < 0)
            return rank(key, mid + 1, hi);
        else if (cmp > 0)
            return rank(key, lo, mid - 1);
        else
            return mid;
    }
}
