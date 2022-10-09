package hash;

public class OpenHash<K, V>{
    // 버킷의 상태
    enum Status {OCCUPIED, EMPTY, DELETED};

    // 해시 테이블의 크기
    private int size;
    // 해시 테이블
    private Bucket<K, V>[] table;

    // 생성자
    public OpenHash(int size) {
        try {
            table = new Bucket[size];
            for (int i = 0; i < size; i++)
                table[i] = new Bucket<K, V>();
            this.size = size;
        } catch (OutOfMemoryError e) {
            this.size = 0;
        }
    }

    // 해시 값을 구함
    public int hashValue(Object key) {
        return key.hashCode() % size;
    }

    // 재해시 값을 구함
    public int rehashValue(int hash) {
        return (hash + 1) % size;
    }

    // 키 값이 key인 버킷을 검색
    private Bucket<K, V> searchNode(K key) {
        // 검색할 데이터의 해쉬값
        int hash = hashValue(key);
        // 선택한 버킷
        Bucket<K, V> p = table[hash];

        for (int i = 0; p.stat != Status.EMPTY && i< size ; i++) {
            if (p.stat == Status.OCCUPIED && p.getKey().equals(key))
                return p;
            // 재해쉬
            hash = rehashValue(hash);
            p = table[hash];
        }
        return null;
    }

    // 키 값이 key인 요소를 검색(데이터를 반환)
    public V search(K key) {
        Bucket<K, V> p = searchNode(key);
        if (p != null)
            return p.getValue();
        else
            return null;
    }

    // 키 값이 key 이고 데이터가 data 인 요소를 추가
    public int add(K key, V data) {
        if (search(key) != null)
            // 이 키값은 이미 등록됨
            return 1;
        // 추가할 데이터의 해시값
        int hash = hashValue(key);
        // 선택한 버킷
        Bucket<K, V> p = table[hash];
        for (int i = 0; i < size; i++) {
            if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
                p.set(key, data, Status.OCCUPIED);
                return 0;
            }
            // 재해시
            hash = rehashValue(hash);
            p = table[hash];
        }
        // 해시 테이블이 가득참
        return 2;
    }

    // 키값이 key 인 요소를 삭제
    public int remove(K key) {
        // 선택한 버킷
        Bucket<K, V> p = searchNode(key);
        if (p == null)
            // 이 키값은 등록되지 않음
            return 1;
        p.setStat(Status.OCCUPIED);
        return 0;
    }

    // 해시 테이블을 덤프
    public void dump() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%02d ", i);
            switch (table[i].stat) {
                case OCCUPIED ->
                        System.out.printf("%s (%s)\n ", table[i].getKey(), table[i].getValue());
                case EMPTY ->
                        System.out.println("--비어 있음--");
                case DELETED ->
                        System.out.println("--삭제 마침--");
            }
        }
    }

    //버킷
    static class Bucket<K, V> {
        private K key;
        private V data;
        private Status stat;

        // 생성자
        Bucket() {
            stat = Status.EMPTY;
        }

        // 모든 필드에 값을 생성
        void set(K key, V data, Status stat) {
            this.key = key;
            this.data = data;
            this.stat = stat;
        }

        // 상태를 설정
        void setStat(Status stat) {
            this.stat = stat;
        }

        // 키 값을 반환
        K getKey() {
            return key;
        }

        // 데이터를 반환
        V getValue() {
            return data;
        }

        // 키의 해시 값을 반환
        public int hashCode() {
            return key.hashCode();
        }

    }
}
