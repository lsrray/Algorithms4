package ray.search.st;

/**
 * 二叉查找树，每一个节点的键都大于其左边子树的任意节点的键，小于右子树任意节点的键
 */
public class BST<key extends Comparable<key>, Value> {

    private Node root;

    private class Node {
        private key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        else
            return node.N;
    }

    public Value get(key key) {
        return get(root, key);
    }

    private Value get(Node x, key key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }

    }

    public void put(key key, Value val) {
        //key存在更新，否则新增结点加入树
        root = put(root, key, val);
    }

    private Node put(Node x, key key, Value value) {

        if (x == null)
            return new Node(key, value, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
}
