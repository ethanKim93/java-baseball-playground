package service.baseball.practice1;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BaseBallGame {
    private static final Logger logger = Logger.getLogger(BaseBallGame.class.getName());
    private Random random = new Random(); // Random 객체를 클래스 멤버로 선언
    private static final Scanner sc = new Scanner(System.in);

    public void playGame() {

        List<Integer> answerNumber = makeAnswerNumbers();

        boolean endGame = false;
        while (!endGame) {
            endGame = choiceBaseBallNumber(answerNumber);
        }
        selectNextGame();
    }

    private static boolean choiceBaseBallNumber(List<Integer> answerNumber) {
        int[] inputAnswer = userInsertNumber();
        if (inputAnswer.length == 0) return false;
        return baseballResult(answerNumber, inputAnswer);
    }

    private static boolean baseballResult(List<Integer> answerNumber, int[] inputAnswer) {
        Result result = getStrikeAndBallCount(answerNumber, inputAnswer);

        StringBuilder sb = new StringBuilder();

        if (result.ball > 0) {
            sb.append(String.format("%d 볼 ", result.ball));
        }
        if (result.strike > 0) {
            sb.append(String.format("%d 스트라이크", result.strike));
        }

        if (sb.length() == 0) {
            sb.append("낫싱");
        }
        if (logger.isLoggable(Level.INFO)) {
            logger.info(sb.toString());
        }

        if (result.strike == 3) {
            logger.info("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return true;
        }
        return false;
    }

    private static Result getStrikeAndBallCount(List<Integer> answerNumber, int[] inputAnswer) {

        Result result = new Result(0 ,0);
        for (int i = 0; i < answerNumber.size(); i++) {
            result = addStrikeAndBall(result,answerNumber.get(i) == inputAnswer[i],answerNumber.contains(inputAnswer[i]));
        }
        return result;
    }

    private static Result addStrikeAndBall(Result result , Boolean strikeResult , Boolean ballResult){
        if (strikeResult) {
            result.strike += 1;
            return result;
        }
        if (ballResult) {
            result.ball += 1;
        }
        return result;
    }

    private static class Result {
        public  int ball;
        public  int strike;

        public Result(int ball, int strike) {
            this.ball = ball;
            this.strike = strike;
        }
    }

    private static int[] userInsertNumber() {
        logger.info("숫자를 입력해 주세요 : ");
        int[] inputAnswer;
        try {
            inputAnswer = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            logger.info("숫자만 입력 가능합니다");
            return new int[0];
        }
        return inputAnswer;
    }

    private void selectNextGame() {
        logger.info("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        if ("1".equals(sc.nextLine())) {
            playGame();
        }
    }


    private List<Integer> makeAnswerNumbers() {
        List<Integer> answerNumber = new ArrayList<>();
        while (answerNumber.size() != 3) {
            addNumber(answerNumber);
        }
        return answerNumber;
    }

    private void addNumber(List<Integer> answerNumber) {
        int number = random.nextInt(10);

        if (number == 0) {
            return;
        }

        if (answerNumber.contains(number)) {
            return;
        }

        answerNumber.add(number);
    }

    public static void main(String[] args) {

        BaseBallGame baseBallGame = new BaseBallGame();
        baseBallGame.playGame();


        logger.info("게임이 종료 되었습니다.");

    }
}
