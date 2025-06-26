package src.main.java.org.selfCode.ui;


import src.main.java.org.selfCode.core.TicTactToe;
import src.main.java.org.selfCode.models.CombinedResult;
import src.main.java.org.selfCode.models.Player;

import java.util.List;
import java.util.Scanner;

public class TicTacToeUI {
    private final Scanner scanner;
    private final char player1Symbol = 'X';
    private final char player2Symbol = 'O';
    private boolean gameWon = false;
    private TicTactToe mTicTacToe = null;

    public TicTacToeUI() {
        scanner = new Scanner(System.in);
        mTicTacToe = new TicTactToe();
    }

    public static void main(String[] args) {
        TicTacToeUI game = new TicTacToeUI();
        game.startGame();
    }

    private void startGame() {
        System.out.println("Welcome to Tic-Tac-Toe Game!");
        System.out.println("=============================");

        // Get player names
        System.out.print("Please enter first player name: ");
        Player player1 = new Player(scanner.nextLine().trim());
        mTicTacToe.setPlayer1(player1);

        System.out.print("Please enter second player name: ");
        Player player2 = new Player(scanner.nextLine().trim());
        mTicTacToe.setPlayer2(player2);

        System.out.println("\n" + mTicTacToe.getPlayer1().getPlayerName() + " will be 'X' and " + mTicTacToe.getPlayer2().getPlayerName() + " will be 'O'");
        mTicTacToe.setCurrentPlayer(player1);
        // Game loop
        while (!gameWon && !mTicTacToe.isBoardFull()) {
            drawBoard();
            char currentSymbol = mTicTacToe.getCurrentPlayer().getPlayerName().equals(player1.getPlayerName()) ? player1Symbol : player2Symbol;
            System.out.print(mTicTacToe.getCurrentPlayer().getPlayerName() + "'s turn (" + currentSymbol + "). Enter position (0-8): ");
            String input = scanner.nextLine().trim();
            int position = Integer.parseInt(input);
            CombinedResult result = mTicTacToe.handleClick(position, String.valueOf(currentSymbol));
            if (result.isSuccess()) {
                gameWon = true;
                drawBoard();
                drawWinningLine(result.getSuccessIndexList());
                System.out.println("\n🎉 Congratulations " + mTicTacToe.getCurrentPlayer().getPlayerName() + "! You won! 🎉");
            } else if (result.isBoardFilled()) {
                drawBoard();
                System.out.println("\n🤝 It's a tie! Good game both players! 🤝");
            } else {
                mTicTacToe.switchPlayer();
            }
        }

        System.out.println("\nThanks for playing!");
        scanner.close();
    }

    private void drawBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("  " + mTicTacToe.getIndexMap().get(0) + " | " + mTicTacToe.getIndexMap().get(1) + " | " + mTicTacToe.getIndexMap().get(2));
        System.out.println(" ---|---|---");
        System.out.println("  " + mTicTacToe.getIndexMap().get(3) + " | " + mTicTacToe.getIndexMap().get(4) + " | " + mTicTacToe.getIndexMap().get(5));
        System.out.println(" ---|---|---");
        System.out.println("  " + mTicTacToe.getIndexMap().get(6) + " | " + mTicTacToe.getIndexMap().get(7) + " | " + mTicTacToe.getIndexMap().get(8));
        System.out.println();
    }

    private void drawWinningLine(List<Integer> winningPositions) {
        if (winningPositions == null) return;

        System.out.println("Winning combination:");

        // Create a visual representation of the winning line
        String[] lineBoard = new String[9];
        for (int i = 0; i < 9; i++) {
            lineBoard[i] = " ";
        }

        // Mark winning positions with asterisks
        for (int pos : winningPositions) {
            lineBoard[pos] = "*";
        }

        System.out.println("  " + lineBoard[0] + " | " + lineBoard[1] + " | " + lineBoard[2]);
        System.out.println(" ---|---|---");
        System.out.println("  " + lineBoard[3] + " | " + lineBoard[4] + " | " + lineBoard[5]);
        System.out.println(" ---|---|---");
        System.out.println("  " + lineBoard[6] + " | " + lineBoard[7] + " | " + lineBoard[8]);

        // Draw ASCII line based on winning pattern
        if (winningPositions.getFirst() == 0 && winningPositions.get(2) == 2) {
            System.out.println(" ═══════════  ← Top row wins!");
        } else if (winningPositions.get(0) == 3 && winningPositions.get(2) == 5) {
            System.out.println(" ═══════════  ← Middle row wins!");
        } else if (winningPositions.get(0) == 6 && winningPositions.get(2) == 8) {
            System.out.println(" ═══════════  ← Bottom row wins!");
        } else if (winningPositions.get(0) == 0 && winningPositions.get(2) == 6) {
            System.out.println(" ║\n ║  ← Left column wins!\n ║");
        } else if (winningPositions.get(0) == 1 && winningPositions.get(2) == 7) {
            System.out.println("    ║\n    ║  ← Middle column wins!\n    ║");
        } else if (winningPositions.get(0) == 2 && winningPositions.get(2) == 8) {
            System.out.println("       ║\n       ║  ← Right column wins!\n       ║");
        } else if (winningPositions.get(0) == 0 && winningPositions.get(2) == 8) {
            System.out.println(" ╲\n   ╲  ← Diagonal wins!\n     ╲");
        } else if (winningPositions.get(0) == 2 && winningPositions.get(2) == 6) {
            System.out.println("     ╱\n   ╱  ← Diagonal wins!\n ╱");
        }
    }
}