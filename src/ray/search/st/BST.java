package ray.search.st;

import edu.princeton.cs.algs4.Queue;

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

    public key min() {
        return this.min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public key max() {
        return this.max(root).key;
    }

    private Node max(Node x) {
        if (x.right != null) {
            return x;
        }
        return max(x.right);
    }

    public key floor(key key) {
        Node node = this.floor(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node floor(Node x, key key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    public key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }

        int t = this.size(x.left);
        if (k < t) {
            return select(x.left, k);
        } else if (k > t) {
            return select(x.right, k - size(x.left) - 1);
        } else {
            return x;
        }

    }

    public int rank(key key) {
        return rank(root, key);
    }

    private int rank(Node x, key key) {
        if (x == null) {
            return 0;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp > 0) {
            return size(x.left) + 1 + rank(x.right, key);
        } else {
            return size(x.left);
        }
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        //找到max后把max的左子树变成父节点的右子树
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + 1 + size(x.right);
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        //找到min后把min的右子树变成父节点的左子树
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + 1 + size(x.right);
        return x;
    }

    public void delete(key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + 1 + size(x.right);
        return x;
    }

    public Iterable<key> keys() {
        return this.keys(min(), max());
    }

    public Iterable<key> keys(key lo, key hi) {
        Queue<key> queue = new Queue<>();
        this.keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue queue, key lo, key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left,queue,lo,hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right,queue,lo,hi);
        }
    }
}
