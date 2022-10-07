package linearSearch03_02;// 연습5-8
// 하노이의 탑(비재귀적으로 구현)

class HanoiN {
    int point = 0;

    IntStack first = new IntStack(100);
    IntStack second = new IntStack(100);
    IntStack third = new IntStack(100);


    public void move(int no) {
        int num = no;
        while (true) {
            if (point == 0) {
                if (num == 1) {
                    point += 1;
                }
                // 값을 처음 스택에 넣는다.
                first.push(num);
                System.out.println("첫번째 스택에 " + num + " 을 넣었습니다.");
                num--;
                // 모든 값들이 다 넣어 졌다면 포인터를 늘린다.
                continue;
            }
            if (point == 1) {
                if (!first.isEmpty()) {
                    // first 스택에 들어간 값들을 빼와서 second 에 넣는다.
                    int pop = first.pop();

                    // 제일 마지막 값은 넣지 않는다.
                    if (pop != no) {
                        second.push(pop);
                        System.out.println("두번째 스택에 " + pop + " 을 넣었습니다.");
                    }
                    // 제일 마지막 값은 세번째 스택에 넣어준다.
                    else {
                        point += 1;
                        third.push(pop);
                        System.out.println("마지막 값을 " + pop +"을 세번째 스택에 넣었습니다.");
                        continue;
                    }
                    continue;
                }
            }
            if (point == 2) {
                // second 의 값들을 찾아서 third 에 넣어준다.
                if (!second.isEmpty()) {
                    int pop = second.pop();
                    System.out.println("세번째 스택에 "+pop+" 을 넣었습니다.");
                    third.push(pop);
                }else{
                    System.out.print("세번째 스택의 값입니다. ");
                    third.dump();
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        HanoiN hanoiN = new HanoiN();
        hanoiN.move(4);
    }

}

