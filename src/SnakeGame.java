import java.util.ArrayDeque;
import java.util.Iterator;

class SnakeGame {

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    int width, height;
    ArrayDeque<int[]> snake;
    int[][] food;
    int whichFood;
    String[][] board;
    boolean gameOver;
    boolean winner;
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        snake = new ArrayDeque<>();
        snake.offer(new int[]{0,0});
        whichFood = 0;
        board = new String[height][width];
        newBoard();
        winner = false;
        gameOver = false;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] dir;
        switch (direction) {
            case "U":
                dir = new int[]{-1, 0};
                break;
            case "L":
                dir = new int[]{0, -1};
                break;
            case "R":
                dir = new int[]{0, 1};
                break;
            default:
                dir = new int[]{1,0};
        }
        int[] currPos = snake.getLast();
        if (dir[0] + currPos[0] >= height
                || dir[0] + currPos[0] < 0) {
            gameOver = true;
            return -1;
        }
        if (dir[1] + currPos[1] >= width
                || dir[1] + currPos[1] < 0) {
            gameOver = true;
            return -1;
        }
        int[] newSpot = {dir[0] + currPos[0], dir[1] + currPos[1]};
        if (whichFood >= food.length || (newSpot[0] != food[whichFood][0] || newSpot[1] != food[whichFood][1])) {
            snake.poll();
        }  else {
            whichFood++;
        }
        if(checkInSnake(newSpot)) {
            gameOver = true;
            return -1;
        }
        snake.offer(newSpot);
        newBoard();
        isGameOver();
        return whichFood;
    }

    public void isGameOver() {
        if (whichFood >= food.length) {
            winner = true;
            gameOver = true;
        }
    }

    public void newBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int[] currFood = food[whichFood];
                if (checkInSnake(new int[]{i,j})) {
                    board[i][j] = ("S");
                } else if (currFood[0] == i && currFood[1] == j) {
                    board[i][j] = ("F");
                } else {
                    board[i][j] = ("|");
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            for (int j = 0; j < board[i].length; j++) {
                String pos = board[i][j];
                for (int k = 0; k < 20; k++) {
                    System.out.print("");
                }
                System.out.print(pos);
            }
        }
    }

    public boolean checkInSnake(int[] newSpot) {
        Iterator snakeIter = snake.iterator();
        while (snakeIter.hasNext()) {
            int[] snakePos = (int[]) snakeIter.next();
            if (snakePos[0] == newSpot[0] && snakePos[1] == newSpot[1]) {
                return true;
            }
        }
        return false;
    }
}