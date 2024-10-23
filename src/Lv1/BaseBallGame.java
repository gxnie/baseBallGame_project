package Lv1;

import java.lang.reflect.Array;
import java.util.*;

public class BaseBallGame {
    HashSet<String> answerSet = new LinkedHashSet<>(); // 정답 숫자를 담을 컬렉션
    Scanner sc = new Scanner(System.in); // 입력값
    Random random = new Random(); // 랜덤 수 객체 생성

    int strikeCount;
    int ballCount;

    // 기본 생성자 -> 객체 생성시 바로 정답값 생성
    public BaseBallGame() {
        while (answerSet.size() < 3) { // answerSet 의 길이가 3미만이면 무한 반복
            int rNum = random.nextInt(9) + 1;
            String rNumString = "" + rNum;
            answerSet.add(rNumString); // 랜덤 값 추가
        }
    }

    int gameNumber = 0;

    // 게임 시작 시, 입력 받을 값
    public void gamePlay() {
        HashSet<String> inputSet = new LinkedHashSet<>();

        while (true) {
            // 입력값 숫자를 담을 컬렉션
            System.out.println("< 게임을 시작합니다 >");
            System.out.println("숫자를 입력하세요");

            try {
                String inputNumber = sc.nextLine(); // 문자열(숫자) 입력
                if (inputNumber.length() > 3) {
                    // 만약 inputNumber 의 인덱스가 3보다 큰 값을 입력하면 강제로 에러 발생 시키기
                    throw new ArithmeticException("올바르지 않은 입력값입니다.");
                }
                // inputNumber 의 값을 하나씩 추출
                for (int i = 0; i < inputNumber.length(); i++) {
                    String inNum = inputNumber.substring(i, i + 1);
                    inputSet.add(inNum);
                }
            } catch (ArithmeticException | InputMismatchException e) {
                // int가 아닌 다른 값을 입력했을 시
                System.out.println("올바르지 않은 입력값입니다.");
            }
            // 입력값을 받았는지 검증후, 게임 진행 횟수 증가
            gameNumber++;

//         스트라이크 : 입력값과 정답값을 비교해, 같은 자리에 같은 숫자가 있는 경우
//         set 컬렉션은 get 메소드가 없음 => Iterator 사용
            Iterator<String> inputIter = inputSet.iterator();
            Iterator<String> answerIter = answerSet.iterator();


            // hasNext = inputIter에 값이 있는지 true or false로 나옴
            while (inputIter.hasNext()) {
                // next()는 inputIter에 값 순서대로 출력
                String inputIterString = inputIter.next();
                String answerIterString = answerIter.next();

                // 문자 비교 : inputSet의 길이만큼 증가하고, 0번째 ~ 마지막 글자까지 비교하기
                for (int i = 0; i < inputIterString.length(); i++) {
                    for (int j = 0; j < answerIterString.length(); j++) {
                        if (inputIterString.charAt(i) == answerIterString.charAt(j)) {
                            strikeCount += 1;
                        } else if (inputIterString.charAt(i) != answerIterString.charAt(j) && answerSet.contains(inputIterString)) {
                            ballCount += 1;
                        } else {
                            break;
                        }
                    }
                }
            }


            if (strikeCount == 3) {
                System.out.println("정답입니다!");
                break;
            } else {
                System.out.println(strikeCount + "스트라이크 " + ballCount + "볼");
            }

        }

    }
}
