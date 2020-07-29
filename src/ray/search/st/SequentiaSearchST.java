package ray.search.st;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 顺序查找：基于无序链表
 */
public class SequentiaSearchST<Key, Value> {

    private Node first;
    private int N;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }

    public Value get(Key key) {
        //顺序查找给定的键,返回关联的值
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    public int size(){
        return N;
    }

    public void put(Key key, Value val) {
        //顺序查找给定的键，找到更新，找不到新增
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        N++;
    }

    public void delete(Key key) {
        Node tmp = null;
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                if (tmp == null)
                    first = x.next;
                else
                    tmp.next = x.next;
                x = null;
                N--;
                return;
            }
            tmp = x;
        }
    }

    public Iterable<Key> keys() {
        return new ListIterable();
    }

    private class ListIterable implements Iterable<Key> {
        @Override
        public Iterator iterator() {

            Iterator iterator = new Iterator<Key>() {

                private Node current = first;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public Key next() {
                    Key key = current.key;
                    current = current.next;
                    return key;
                }
            };
            return iterator;
        }

    }

    public static void main(String[] args) {
        String a[] = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        SequentiaSearchST<String, Integer> st = new SequentiaSearchST<>();
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }

        st.keys().forEach(s -> {
            StdOut.println(s + " " + st.get(s));
        });
        System.out.println(st.size());
    }
}

