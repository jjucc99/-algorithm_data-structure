package list;

import java.util.Comparator;

public class DoubleLinkedList<E> {
    class Node<E> {
        // 데이터
        private E data;
        // 앞쪽 포인터
        private Node<E> prev;
        // 뒤쪽 포인터
        private Node<E> next;

        // 생성자
        Node() {
            data = null;
            prev = next = this;
        }

        // 생성자
        Node(E obj, Node<E> prev, Node<E> next) {
            data = obj;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> crnt;

    // 생성자
    public DoubleLinkedList() {
        // 더미 노드를 생성
        head = crnt = new Node<E>();
    }

    // 리스트가 비어 있는가 ?
    public boolean isEmpty() {
        return head.next == head;
    }

    // 노드를 검색
    public E search(E obj, Comparator<? super E> c) {
        // 현재 스캔 중인 노드 더미 노드에서 벋어나기
        Node<E> ptr = head.next;

        while (ptr != head) {
            if (c.compare(obj, ptr.data) == 0) {
                crnt = ptr;
                // 검색 성공
                return ptr.data;
            }
            // 다음 노드를 검색
            ptr = ptr.next;
        }
        // 검색 실패
        return null;
    }

    //선택 노드를 출력
    public void printCurrentNode() {
        if (isEmpty())
            System.out.println("선택 노드가 없습니다.");
        else
            System.out.println(crnt.data);
    }

    // 모든 노드를 출력
    public void dump() {
        // 더미 노드의 다음 노드
        Node<E> ptr = head.next;

        while (ptr != head) {
            System.out.println(ptr.data);
            ptr = ptr.next;
        }
    }

    // 모든 노드를 거꾸로 출력
    public void dumpReverse() {
        // 더미 노드의 앞쪽 노드
        Node<E> ptr = head.prev;

        while (ptr != head) {
            System.out.println(ptr.data);
            ptr = ptr.prev;
        }
    }

    // 선택 노드를 하나 뒤쪽으로 진행
    public boolean next() {
        if (isEmpty() || crnt.next == head) {
            // 진행할 수 없음
            return false;
        }
        crnt = crnt.next;
        return true;
    }

    // 선택 노드를 하나 앞쪽으로 진행
    public boolean prev() {
        if (isEmpty() || crnt.prev == head)
            return false;
        crnt = crnt.prev;
        return true;
    }

    // 선택 노드를 바로 뒤에 노드를 삽입
    public void add(E obj) {
        Node<E> node = new Node<E>(obj, crnt, crnt.next);
        crnt.next = crnt.next.prev = node;
        crnt = node;
    }

    // 선택 노드를 삭제
    public void removeCurrentNode() {
        if (!isEmpty()) {
            crnt.prev.next = crnt.next;
            crnt.next.prev = crnt.prev;
            if (crnt == head) crnt = head.next;
        }
    }

    // 노드 p를 삭제
    public void remove(Node p) {
        Node<E> ptr = head.next;

        while (ptr != head) {
            // p를 찾음
            if (ptr == p) {
                crnt = p;
                removeCurrentNode();
                break;
            }
            ptr = ptr.next;
        }
    }

    // 머리 노드를 삭제
    public void removeFirst() {
        // 머리 노드 head.next 를 삭제
        crnt = head.next;
        removeCurrentNode();

    }

    // 꼬리 노드를 삭제
    public void removeLast() {
        // 꼬리 노드 head.prev 를 삭제
        crnt = head.prev;
        removeCurrentNode();
    }

    // 모든 노드를 삭제
    public void clear() {
        // 텅 빌 때까지 머리 노드를 삭제
        while (!isEmpty())
            removeFirst();
    }
}
