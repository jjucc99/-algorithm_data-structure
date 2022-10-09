package hash;

public class ChainHash<K, V> {

    // 해시 테이블의  크기
    private int size;
    // 해시 테이블
    private Node<K, V>[] table;

    // 생성자
    public ChainHash(int capacity) {
        try {
            table = new Node[capacity];
            this.size = capacity;
        }
        // 테이블을 생성할 수 없음
        catch (OutOfMemoryError e) {
            this.size = 0;
        }
    }

    // 해시 값을 구함
    public int hashValue(Object key) {
        return key.hashCode() % size;
    }

    // 키 값이 key 인 요소를 검색(데이터를 반환)
    public V search(K key) {
        // 검색할 데이터의 해시값
        int hash = hashValue(key);
        // 선택 노드
        Node<K, V> p = table[hash];

        while (p != null) {
            if (p.getKey().equals(key))
                // 검색 성공
                return p.getValue();
            // 다음 요소를 선택
            p = p.next;
        }
        // 검색 실패
        return null;
    }

    // 키 값이 key 이고 데이터가 data 인 요소를 추가
    public int add(K key, V data) {
        // 추가할 데이터의 해시값
        int hash = hashValue(key);
        // 선택 노드
        Node<K, V> p = table[hash];

        while (p != null) {
            if (p.getKey().equals(key))
                // 이미 키 값은 등록됨
                return 1;
            // 다음 노드를 선택
            p = p.next;
        }
        Node<K, V> temp = new Node<K, V>(key, data, table[hash]);
        // 노드를 삽입
        table[hash] = temp;
        return 0;
    }

    public int remove(K key) {
        // 삭제할 데이터의 해시값
        int hash = hashValue(key);
        // 선택 노드
        Node<K, V> p = table[hash];
        // 바로 앞의 데이터 노드
        Node<K, V> pp = null;

        while (p != null) {
            if (p.getKey().equals(key)){
                if (pp == null)
                    table[hash] = p.next;
                else
                    pp.next = p.next;
                return 0;
            }
            // 다음 노드를 선택
            pp = p;
            p = p.next;
        }
        // 그 키값은 없음
        return 1;
    }

    // 해시 테이블 덤프
    public void dump() {
        for (int i = 0; i < size; i++) {
            Node<K, V> p = table[i];
            System.out.printf("%02d " , i);
            while (p != null) {
                System.out.printf("-> %s (%s) ", p.getKey(), p.getValue());
                p = p.next;
            }
            System.out.println();
        }
    }


    static class Node<K, V> {
        //  키 값
        private K key;
        // 데이터
        private V data;
        // 다음 노드에 대한 참조
        private Node<K, V> next;

        // 생성자
        Node(K key, V data, Node<K, V> next) {
            this.key = key;
            this.data = data;
            this.next = next;
        }

        // 키 값을 반환
        K getKey() {
            return key;
        }

        // 데이터를 반환
        V getValue() {
            return data;
        }

        // 키의 해시값을 반환
        public int hashCode() {
            return key.hashCode();
        }
    }
}
