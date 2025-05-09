import java.util.LinkedList;
import java.util.List;

public class CustomHashMap <K, V> {
    private static final int SIZE = 16;

    private LinkedList<Node<K, V>>[] buckets;

    static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public CustomHashMap() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[index];

        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        bucket.add(new Node<>(key, value));
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>>  bucket = buckets[index];

        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    public void remove(K key) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[index];

        bucket.removeIf(node -> node.key.equals(key));
    }

    public void printMap() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("Bucket " + i + " = ");
            if (buckets[i].isEmpty()) {
                System.out.println("Empty");
            } else {
                for (Node<K, V> node : buckets[i]) {
                    System.out.print("[" + node.key + " : " + node.value + "] ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("Ruchi", 22);
        map.put("Vandit", 14);
        map.put("Bhadrik", 51);
        map.put("Ami", 48);

        System.out.println("Ruchi's age = " + map.get("Ruchi"));
        map.remove("Vandit");

        map.printMap();
    }
}
