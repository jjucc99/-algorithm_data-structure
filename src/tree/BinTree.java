package tree;

import java.util.Comparator;

public class BinTree<K, V> {

    // 노드
    static class Node<K, V> {
        // 키 값
        private K key;
        // 데이터
        private V data;
        // 왼쪽 포인터(왼쪽 자식 노드에 대한 참조)
        private Node<K, V> left;
        // 오른쪽 포인터(오른쪽 자식 노드에 대한 참조)
        private Node<K, V> right;

        // 생성자
        Node(K key, V data, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        // 키 값을 반환
        K getKey() {
            return key;
        }

        // 데이터를 반환
        V getValue() {
            return data;
        }

        // 데이터 출력
        void print() {
            System.out.println(data);
        }
    }

    // 루트
    private Node<K, V> root;
    // 키 값의 대소 관계를 판단하는 비교자. 이진 검색트리를 생성하는 생성자에서
    // 비교자를 명시적으로 설정하지 않으면 자동으로 null이 되도록 초기화 null을 주어 선언
    private Comparator<? super K> comparator = null;

    // 생성자
    public BinTree() {
        root = null;
    }

    // 생성자
    public BinTree(Comparator<? super K> c) {
        this();
        comparator = c;
    }

    // 두  키값을 비교
    private int comp(K key1, K key2) {
        // key 1 > key 2 면 양수
        // key 1 < key 2 면 음수
        // key 1 == key 2 면 0
        return (comparator == null) ? ((Comparable<K>) key1).compareTo(key2)
                : comparator.compare(key1, key2);
    }

    public V search(K key) {
        // 루트에 주목
        Node<K, V> p = root;

        while (true) {
            // 더 이상 진행할 수 없으면 검색 실패
            if (p == null)
                return null;
            // key 와 노드 P의 키 값을 비교
            int cond = comp(key, p.getKey());
            // 같으면 검색 성공
            if (cond == 0)
                return p.getValue();
            // key가 작으면 왼쪽 (이진 검색트리는 왼쪽이 작다.)
            else if (cond < 0)
                p = p.left;
            // key가 크면 오른쪽 (이진 검색트라는 오른쪽이 크다.)
            else
                p = p.right;
        }
    }

    // node를 루트로 하는 서브트리에 노드<K, V> 을 삽입
    private void addNode(Node<K, V> node, K key, V data) {
        // 값을 비교 시작
        int cond = comp(key, node.getKey());
        // key 가 이진검색트리에 이미 있음
        if (cond == 0)
            return;
        else if (cond < 0) {
            // 왼쪽 서브트리의 값이 존재하지 않는다.
            if (node.left == null)
                node.left = new Node<K, V>(key, data, null, null);
                // 왼쪽 서브트리에 이미 값이 존재한다.
            else
                addNode(node.left, key, data);
        } else {
            if (node.right == null)
                node.right = new Node<K, V>(key, data, null, null);
            else
                addNode(node.right, key, data);
        }
    }

    // 노드를 삽입
    public void add(K key, V data) {
        if (root == null)
            root = new Node<K, V>(key, data, null, null);
        else
            addNode(root, key, data);
    }

    public boolean remove(K key) {
        // 스캔 중인 노드
        Node<K, V> p = root;
        // 스캔 중인 노드의 부모 노드
        Node<K, V> parent = null;
        // p가 부모의 왼쪽 자식 노드? == 자식이 부모 노드보다 작다.
        boolean isLeftChild = true;

        while (true) {
            // 더 이상 나아갈 수 없으면 그 키값은 없음
            if (p == null)
                return false;
            int cond = comp(key, p.getKey());

            // key 와 노드 p 의 키값을 비교 같으면 검색 성공
            if (cond == 0) {
                break;
            } else {
                // 가지로 내려가기 전에 부모를 설정
                parent = p;
                // key 쪽이 작으면 왼쪽 자식으로 내려감
                // 왼쪽 서브트리에서 검색
                if (cond < 0) {
                    isLeftChild = true;
                    p = p.left;
                // key 쪽이 크면 오른쪽 자식으로 내려감
                // 오른쪽 서브트리에서 검색
                } else {
                    isLeftChild = false;
                    p = p.right;
                }
            }
        }

        // p 에 왼쪽 자식이 없음
        if (p.left == null) {
            // 부모와 자식이 같아. 그럼 나(p)를 나의 오른쪽 자식(p.right)으로 대체한다.
            if (p == root)
                root = p.right;
                // 부모가 자식(parent.left == p)을 나의 오른쪽 자식으로 대체
            else if (isLeftChild)
                parent.left = p.right;
                // 부모가 자식(parent.right == p)을 나의 오른쪽 자식으로 대체
            else
                parent.right = p.right;
            // p 에 오른쪽 자식이 없음
        } else if (p.right == null) {
            // // 부모와 자식이 같아. 그럼 나(p)를 나의 왼쪽 자식(p.right)으로 대체한다.
            if (p == root)
                root = p.left;
                // 부모가 자식(parent.left == p)을 나의 왼쪽 자식으로 대체
            else if (isLeftChild)
                parent.left = p.left;
                // 부모가 자식(parent.right == p)을 나의 왼쪽 자식으로 대체
            else
                parent.right = p.left;
        } else {
            parent = p;
            // 서브 트리 가운데 가장 큰 노드
            Node<K, V> left = p.left;
            isLeftChild = true;
            // 가장 큰 노드 left를 검색
            while (left.right != null) {
                parent = left;
                left = left.right;
                isLeftChild = false;
            }
            // left의 키 값을 p로 옮김
            p.key = left.key;
            // left의 데이터를 p로 옮김
            p.data = left.data;
            if (isLeftChild)
                // left를 삭제
                parent.left = left.left;
            else
                // left를 삭제
                parent.right = left.left;
        }
        return true;
    }

    // node를 루트로 하는 서브트리의 노드를 키값의 오름차순으로 출력
    private void printSubTree(Node node) {
        if (node != null) {
            // 왼쪽 서브트리를 키값의 오름차순으로 출력
            printSubTree(node.left);
            // node를 출력
            System.out.println(node.key + " " + node.data);
            // 오론쪽 서브트리를 키값의 오름차순으로 출력
            printSubTree(node.right);
        }
    }

    // 모든 노드를 키값의 오름차순으로 출력
    public void print() {
        printSubTree(root);
    }
}
