package onboarding;

import java.util.List;

import static java.lang.Math.max;

class Problem1 {

    // 문제 기능 목록
    // 왼쪽 페이지, 오른쪽 페이지가 범위내에 있는지 확인하는 기능
    // 왼쪽, 오른쪽 페이지가 홀수, 짝수로 되어있는지 확인하는 기능
    // 왼쪽 오른쪽 페이지의 차가 1인지 확인하는 기능
    // 위 기능이 모두 성립하는지 확인하는 기능
    // 각 자릿수 더하는 기능
    // 각 자릿수 곱하는 기능
    // 더한것과 곱한것중 더 큰 것 선택하는 기능
    // 왼쪽과 오른쪽중 더 큰 것 선택하는 기능
    // 포비와 크롱의 결과에 따른 숫자 리턴하는 기능


    // solution함수가 static이기 때문에 다른 함수들도 static으로 해야한다.
    // 모든 조건을 만족하는지 판별(페이지가 범위내에 있는지 * 서로 인접한 페이지인지 * 왼쪽이 홀수 오른쪽이 짝수인지)
    static boolean checkValid(List<Integer> pages) {
        return checkInterval(pages) && checkDiff(pages) && checkOddEven(pages);
    }

    // 서로 차이가 1인지 판별
    static boolean checkDiff(List<Integer> pages) {
        return pages.get(1) - pages.get(0) == 1;
    }
    // 책 왼쪽 페이지 홀수, 오른쪽 페이지 짝수인지 판별 0으로 나눠서 오류나올 수 있으니 예외처리 해놓음.
    static boolean checkOddEven(List<Integer> pages) {
        try {
            return pages.get(0) % 2 == 1 && pages.get(1) % 2 == 0;
        }catch(ArithmeticException e) {
            return false;
        }
    }

    // 페이지가 범위내에 있는지
    static boolean checkInterval(List<Integer> pages) {
        Integer left = pages.get(0);
        Integer right = pages.get(1);
        return (1 <= left && left <= 399) && (2 <= right && right <= 400);
    }

    // 각 자릿수 더하기
    static Integer addEachDigit(Integer page) {
        char[] eachDigit = String.valueOf(page).toCharArray(); // Integer -> char[]로 바꾸기.
        Integer total = 0;
        for(int i=0; i<eachDigit.length; i++) {
            total += eachDigit[i] - '0';
        }
        return total;
    }

    // 각 자릿수 곱하기
    static Integer dotEachDigit(Integer page) {
        char[] eachDigit = String.valueOf(page).toCharArray();
        Integer total = 1;
        for(int i=0; i<eachDigit.length; i++) {
            total *= eachDigit[i] - '0';
        }
        return total;
    }

    // 각 자릿수 더한거랑 곱한것중 더 큰거 리턴하기.
    static Integer maxAddDot(Integer page) {
        return max(addEachDigit(page), dotEachDigit(page));
    }

    // 왼쪽 페이지와 오른쪽 페이지중 더 큰것 리턴
    static Integer maxLeftRight(List<Integer> pages) {
        Integer left = pages.get(0);
        Integer right = pages.get(1);
        return max(maxAddDot(left), maxAddDot(right));
    }

    // 둘의 비교를 통해 상황에 따른 값 출력.
    static int maxPobiCrong(Integer maxpobi, Integer maxcrong) {
        if(maxpobi < maxcrong) {
            return 2;
        } else if(maxpobi > maxcrong) {
            return 1;
        } else {
            return 0;
        }
    }
    public static int solution(List<Integer> pobi, List<Integer> crong) {
        if(!(checkValid(pobi) && checkValid(crong))) {
            return -1;
        }
        return maxPobiCrong(maxLeftRight(pobi), maxLeftRight(crong));
    }
}