package list;

import java.util.Comparator;

public class LinkedList<E> {
    static class Node<E> {
        private E data;
        private Node<E> next;

        Node(E date, Node<E> next) {
            this.data = date;
            this.next = next;
        }
    }

    // 머리 노드를 가르킨다.
    private Node<E> head;
    // 현재 선택한 노드를 가르킨다.
    private Node<E> crnt;

    public LinkedList() {
        head = crnt = null;
    }

    //노드 검색
    public E search(E obj, Comparator<? super E> c) {
        Node<E> ptr = head;
        while (ptr != null) {
            if (c.compare(obj, ptr.data) == 0) {
                crnt = ptr;
                return ptr.data;
            }
            ptr = ptr.next;
        }
        return null;
    }

    // 머리에 노드 삽입
    public void addFirst(E obj) {
        Node<E> next = head;
        head = crnt = new Node<E>(obj, next);
    }

    // 꼬리에 노드 삽입
    public void addLast(E obj) {
        // 리스트가 비어 있으면 머리에 삽입
        if (head == null) {
            addFirst(obj);
        } else {
            Node<E> ptr = head;
            // while 문을 종료 할 때 ptr 은 꼬리 노드를 가르킨다.
            while (ptr.next != null)
                ptr = ptr.next;
            ptr.next = crnt = new Node<E>(obj, null);
        }
    }

    // 머리 노드를 삭제
    public void removeFirst() {
        if (head != null) {
            head = crnt = head.next;
        }
    }

    // 꼬리 노드를 삭제
    public void removeLast() {
        if (head != null) {
            if (head.next == null)
                removeFirst();
        } else {
            // 스캔 중인 노드
            Node<E> ptr = head;
            // 스캔 중인 노드의 앞쪽 노드
            Node<E> pre = head;

            // while 문이 끝날 때 ptr 은 꼬리노드 pre은 꼬리에서 두번째 노드를 가르킨다.
            while (ptr.next != null) {
                pre = ptr;
                ptr = ptr.next;
            }
            pre.next = null;
            crnt = pre;
        }
    }

    // 노드 p를 삭제
    public void remove(Node p) {
        if (head != null) {
            // p 가 머리 노드면 머리 노드를 삭제
            if (p == head)
                removeFirst();
        } else {
            Node<E> ptr = head;
            while (ptr.next != p) {
                ptr = ptr.next;
                // p 가 리스트에 없음
                if (ptr == null) return;
            }
            ptr.next = p.next;
            crnt = ptr;
        }
    }

    // 선택 노드를 삭제
    public void removeCurrentNode() {
        remove(crnt);
    }

    // 모든 노드를 삭제
    public void clear() {
        while (head != null)
            removeFirst();
        crnt = null;
    }

    // 선택 노드를 하나 뒤쪽으로 진행
    public boolean next() {
        if (crnt == null || crnt.next == null)
            return false;
        crnt = crnt.next;
        return true;
    }

    // 선택 노드를 출력
    public void printCurrentNode() {
        if (crnt == null)
            System.out.println("선택한 노드가 없습니다.");
        else
            System.out.println(crnt.data);
    }

    //모든 노드를 출력
    public void dump() {
        Node<E> ptr = head;

        while (ptr != null) {
            System.out.println(ptr.data);
            ptr = ptr.next;
        }
    }

    //---【연습8-1】 컴퍼레이터c로 서로 같은 노드를 찾아 모든 노드를 삭제 ---//
    public void purge(Comparator<? super E> c) {
        Node<E> ptr = head;

        while (ptr != null) {
            int count = 0;
            Node<E> ptr2 = ptr;
            Node<E> pre = ptr;

            while (pre.next != null) {
                ptr2 = pre.next;
                if (c.compare(ptr.data, ptr2.data) == 0) {
                    pre.next = ptr2.next;
                    count++;
                } else
                    pre = ptr2;
            }
            if (count == 0)
                ptr = ptr.next;
            else {
                Node<E> temp = ptr;
                remove(ptr);
                ptr = temp.next;
            }
        }
        crnt = head;
    }

    //---【연습8-2】 머리부터 n개 뒤 노드의 데이터에 대한 참조를 반환 ---//
    public E retrieve(int n) {
        Node<E> ptr = head;

        while (n >= 0 && ptr != null) {
            if (n-- == 0) {
                crnt = ptr;
                return ptr.data;                // 검색 성공
            }
            ptr = ptr.next;                    // 다음 노드를 선택
        }
        return null;
    }
}
