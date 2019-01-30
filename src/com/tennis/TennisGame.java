package com.tennis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class TennisGame {

    private int[] scores = {0, 0};
    private int[] set = {0, 0};
    private Player player1;
    private Player player2;
    private String winner;

    public final static HashMap<Integer, String> SCORE_MAPPING = new HashMap<>();

    static {
        SCORE_MAPPING.put(0, "0");
        SCORE_MAPPING.put(1, "15");
        SCORE_MAPPING.put(2, "30");
        SCORE_MAPPING.put(3, "40");
        SCORE_MAPPING.put(4, "Game");
    }

    public TennisGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

    }

    public String score() {
        return Optional.ofNullable(winner).map(x -> x + " has won the game")
                .orElse(set[player1.getPosition()] + "-" + set[player2.getPosition()] + ", " + getScoreMapping());
    }

    private String getScoreMapping() {
        boolean atLeastThree = Arrays.stream(scores).allMatch(x -> x >= 3);
        if (atLeastThree && scores[0] == scores[1]) {
            return "DEUCE";
        } else if (atLeastThree && (Math.abs(scores[0] - scores[1]) == 1)) {
            return "Advantage " + (scores[player1.getPosition()] > scores[player2.getPosition()] ?
                    player1.getName() : player2.getName());
        } else {
            String sideAScore = Optional.of(SCORE_MAPPING.get(scores[player1.getPosition()]))
                    .orElseThrow(() -> new IllegalArgumentException("should not reach points without someone winning"));
            String sideBScore = Optional.of(SCORE_MAPPING.get(scores[player2.getPosition()]))
                    .orElseThrow(() -> new IllegalArgumentException("should not reach points without someone winning"));
            return sideAScore + "-" + sideBScore;
        }
    }


    public void pointWonBy(Player player) {
        boolean atLeastThree = Arrays.stream(scores).anyMatch(x -> x >= 3);
        scores[player.getPosition()]++;
        int difference = Math.abs(scores[0] - scores[1]);
        if (atLeastThree && difference >= 2) {
            setWonBy(player);
        }
    }

    public void setWonBy(Player player) {
        if (winner == null) {
            boolean oneHasScoredSix = Arrays.stream(set).anyMatch(x -> x >= 6);
            int difference = Math.abs(set[0] - set[1]);
            if (oneHasScoredSix && difference >= 2) {
                winner = player.getName();
            }
            set[player.getPosition()]++;
            scores[0] = 0;
            scores[1] = 0;
        }
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public int[] getSet() {
        return set;
    }

    public void setSet(int[] set) {
        this.set = set;
    }
}
