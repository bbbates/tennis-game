package test;


import com.tennis.Player;
import com.tennis.TennisGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisGameTest {

    private TennisGame game;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void initGame() {
        player1 = new Player("Player 1", Player.LEFT_SIDE);
        player2 = new Player("Player 2", Player.RIGHT_SIDE);
        game = new TennisGame(player1, player2);
    }

    @Test
    public void testTennisGameStart() {
        assertEquals("0-0, 0-0", game.score());
    }

    @Test
    public void testFirstScore() {
        game.pointWonBy(player1);
        assertEquals("0-0, 15-0", game.score());
    }

    @Test
    public void testFirstAndSecondScore() {
        game.pointWonBy(player1);
        game.pointWonBy(player2);
        assertEquals("0-0, 15-15", game.score());
    }

    @Test
    public void testPlayerOneWins() {
        game.pointWonBy(player1);
        game.pointWonBy(player2);
        assertEquals("0-0, 15-15", game.score());

        game.pointWonBy(player1);
        game.pointWonBy(player1);
        assertEquals("0-0, 40-15", game.score());
    }

    @Test
    public void testDeuce() {
        game.pointWonBy(player1);
        game.pointWonBy(player2);
        game.pointWonBy(player1);
        game.pointWonBy(player2);
        game.pointWonBy(player1);
        game.pointWonBy(player2);
        assertEquals("0-0, DEUCE", game.score());
    }

    @Test
    public void testAdvantage() {
        game.pointWonBy(player1);
        game.pointWonBy(player2);
        game.pointWonBy(player1);
        game.pointWonBy(player2);
        game.pointWonBy(player1);
        game.pointWonBy(player2);
        assertEquals("0-0, DEUCE", game.score());

        game.pointWonBy(player1);
        assertEquals("0-0, Advantage Player 1", game.score());

        game.pointWonBy(player2);
        assertEquals("0-0, DEUCE", game.score());

        game.pointWonBy(player1);
        assertEquals("0-0, Advantage Player 1", game.score());

        game.pointWonBy(player2);
        assertEquals("0-0, DEUCE", game.score());

        game.pointWonBy(player2);
        assertEquals("0-0, Advantage Player 2", game.score());

        game.pointWonBy(player2);
        assertEquals("0-1, 0-0", game.score());
    }

    @Test
    public void testInitializePlayer() {
        Executable closureContainingCodeToTest =
                () -> player1 = new Player("Player 2", 2);
        Assertions.assertThrows(IllegalArgumentException.class, closureContainingCodeToTest, "Should not " +
                "be able to create player with wrong position");
    }

    @Test
    public void testWonSet() {
        game.setWonBy(player1);
        assertEquals("1-0, 0-0", game.score());
    }

    @Test
    public void testMultipleSetWon() {
        game.setWonBy(player1);
        game.setWonBy(player1);
        game.setWonBy(player1);

        game.setWonBy(player2);
        game.setWonBy(player2);

        game.pointWonBy(player1);
        assertEquals("3-2, 15-0", game.score());
    }

    @Test
    public void testWinner() {
        game.setWonBy(player1);
        game.setWonBy(player1);
        game.setWonBy(player1);
        game.setWonBy(player1);
        game.setWonBy(player1);
        game.setWonBy(player1);
        game.setWonBy(player1);
        game.setWonBy(player2);

        game.pointWonBy(player1);
        assertEquals("Player 1 has won the game", game.score());
    }

    @Test
    public void testWinGameViaPoints() {
        for (int i = 0; i <= 28; i++) {
            System.out.println(game.score());
            game.pointWonBy(player2);
        }
        game.setWonBy(player1);
        assertEquals("Player 2 has won the game", game.score());
    }
}
