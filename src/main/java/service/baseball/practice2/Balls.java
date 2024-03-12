package service.baseball.practice2;

import java.util.ArrayList;
import java.util.List;

public class Balls {
    private final List<Ball> answers;

    public Balls(List<Integer> answers) {
        this.answers = mapBall(answers);
    }


    private List<Ball> mapBall(List<Integer> answers) {
        List<Ball> ballList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ballList.add(new Ball(i + 1, answers.get(i)));
        }
        return ballList;
    }

    public PlayResult play(List<Integer> balls) {
        Balls userBalls = new Balls(balls);
        PlayResult result = new PlayResult();
        for (Ball answer : answers) {
            BallStatus ballStatus = userBalls.play(answer);
            result.report(ballStatus);
        }
        return result;
    }

    public BallStatus play(Ball userBall) {
        return answers.stream()
                .map(answer -> answer.play(userBall))
                .filter(BallStatus::isNotNothing)
                .findFirst()
                .orElse(BallStatus.NOTHING);
    }
}
