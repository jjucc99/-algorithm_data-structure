package list;

import java.util.Comparator;

public class ArrayLinkedList<E> {

    // 노드
    class Node<E> {
        private E data;
        //리스트의 뒤쪽 포인터
        private int next;
        //프리 리스트의 뒤쪽 포인테
        private int dnext;

        // data 와 next 를 설정
        void set(E data, int next) {
            this.data = data;
            this.next = next;
        }
    }

    // 리스트 본체
    private Node<E>[] n;
    // 리스트의 용량(가장 큰 데이터 수)
    private int size;
    // 사용 중인 꼬리 record
    private int max;
    // 머리 노드
    private int head;
    // 선택 노드
    private int crnt;
    // 프리 리스트의 머리 노드
    private int deleted;
    // 다음 노드 없음 / 리스트가 가득 참
    private static final int NULL = -1;

    public ArrayLinkedList(int capacity) {
        head = crnt = max = deleted = NULL;
        try {
            n = new Node[capacity];
            for (int i = 0; i < capacity; i++)
                n[i] = new Node<E>();
            size = capacity;
        } catch (OutOfMemoryError e) {
            size = 0;
        }
    }
    // 다음에 삽입하는 record의 인덱스를 구함
    private int getInsertIndex() {
        // 삭제할 record 가 없음
        if (deleted == NULL) {
            if (max < size)
                // 새 record 를 사용
                return ++max;
            else
                // 용량 넘침(Over)
                return NULL;
        } else {
            // 프리 리스트에서 머리 rec 을 꺼냄
            int rec = deleted;
            deleted = n[rec].dnext;
            return rec;
        }
    }

    // record idx 를 프리 리스트에 등록
    private void deleteIndex(int idx) {
        // 삭제할 record 가 없음
        if (deleted == NULL) {
            // idx를 프리 리스트의 머리에 등록
            deleted = idx;
            n[idx].dnext = NULL;
        } else {
            // idx를 프리 리스트의 머리에 삽입
            int rec = deleted;
            deleted = idx;
            n[idx].dnext = rec;
        }
    }

    // 노드를 검색
    public E search(E obj, Comparator<? super E> c) {
        int ptr = head;
        while (ptr != NULL) {
            if (c.compare(obj, n[ptr].data) == 0) {
                crnt = ptr;
                return n[ptr].data;
            }
            ptr = n[ptr].next;
        }
        return null;
    }

    // 머리에 노드를 삽입
    public void addFirst(E obj) {
        int ptr = head;
        int rec = getInsertIndex();
        if (rec != NULL) {
            head = crnt = rec;
            n[head].set(obj, ptr);
        }
    }

    //  꼬리에 노드를 삽입
    public void addLast(E obj) {
        if (head == NULL) {
            addFirst(obj);
        } else {
            int ptr = head;
            while (n[ptr].next != NULL)
                ptr = n[ptr].next;
            int rec = getInsertIndex();
            if (rec != NULL) {
                n[ptr].next = crnt = rec;
                n[rec].set(obj, NULL);
            }
        }
    }

    // 머리 노드를 삭제
    public void removeFirst() {
        // 리스트가 비어 있지 않으면
        if (head != NULL) {
            int ptr = n[head].next;
            deleteIndex(head);
            head = crnt = ptr;
        }
    }

    // 꼬리 노드를 삭제
    public void removeLast() {
        if (head != NULL) {
            // 노드가 하나만 있으면 머리 노드를 삭제
            if (n[head].next == NULL) {
                removeFirst();
            } else {
                // 스캔 중인 노드
                int ptr = head;
                // 스캔 중인 노드의 앞쪽 노드
                int pre = head;

                while (n[ptr].next != NULL) {
                    pre = ptr;
                    ptr = n[ptr].next;
                }
                // pre 는 삭제 후의 꼬리 노드
                n[pre].next = NULL;
                deleteIndex(ptr);
                crnt = pre;
            }
        }
    }

    // record p를 삭제
    public void remove(int p) {
        if (head != NULL) {
            if (p == head) {
                removeFirst();
            } else {
                int ptr = head;

                while (n[ptr].next != p) {
                    ptr = n[ptr].next;
                    // p가 리스트에 없음
                    if (ptr == NULL) return;
                }
                n[ptr].next = NULL;
                deleteIndex(p);
                n[ptr].next = n[p].next;
                crnt = ptr;
            }
        }
    }

    // 선택 노드를 삭제
    public void removeCurrentNode() {
        remove(crnt);
    }

    // 모든 노드를 삭제
    public void clear() {
        // 텅 빌 때까지 머리 노드를 삭제
        while (head != NULL)
            removeFirst();
        crnt = NULL;
    }

    // 선택 노드를 하나 뒤쪽으로 진행
    public boolean next() {
        if (crnt == NULL || n[crnt].next == NULL)
            return false;
        crnt = n[crnt].next;
        return true;
    }

    // 선택 노드를 출력
    public void printCurrentNode() {
        if (crnt == NULL)
            System.out.println("선택 노드가 없습니다.");
        else
            System.out.println(n[crnt].data);
    }

    // 모든 노드를 출력
    public void dump() {
        int ptr = head;

        while (ptr != NULL) {
            System.out.println(n[ptr].data);
            ptr = n[ptr].next;
        }
    }
}
