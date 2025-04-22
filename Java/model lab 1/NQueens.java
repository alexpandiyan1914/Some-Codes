import java.util.*;

public class NQueens {
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of queens: ");
        N = sc.nextInt();

        char[][] board = new char[N][N];
        for (char[] row : board)
            Arrays.fill(row, '.');

        if (solve(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    static boolean solve(char[][] board, int row) {
        if (row == N) return true;

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                if (solve(board, row + 1))
                    return true;
                board[row][col] = '.'; // Backtrack
            }
        }
        return false;
    }

    static boolean isSafe(char[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q') return false;

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q') return false;

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 'Q') return false;

        return true;
    }

    static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
    }
}
