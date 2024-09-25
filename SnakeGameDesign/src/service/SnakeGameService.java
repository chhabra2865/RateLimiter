package service;

import dto.Position;
import dto.Snake;

import constants.GameConstants;
import java.util.Set;

public class SnakeGameService {
  private Snake snake;
  private int totalScore;
  private int width;
  private int height;
  private Set<Position> foodSet;  // Use HashSet for faster food search

  public SnakeGameService(int width, int height, Set<Position> foodSet) {
    this.width = width;
    this.height = height;
    this.totalScore = 0;
    this.foodSet = foodSet;
    this.snake = new Snake();
    initializeSnake();
  }

  private void initializeSnake() {
    snake.addPositionToFront(new Position(0, 0));  // Snake starts at (0, 0)
  }

  public int move(String direction) {
    Position snakeHead = snake.getSnakeTraceQueue().peekFirst();
    Position nextPoint = getNextPoint(snakeHead.getX(), snakeHead.getY(), direction);

    // Check if next move is within boundaries
    if (!isValidPoint(nextPoint.getX(), nextPoint.getY())) {
      return -1;
    }

    // Check if next move hits the snake's body (except for the tail, which will move)
    if (snake.getSnakeTraceSet().contains(nextPoint) && !nextPoint.equals(snake.getSnakeTraceQueue().peekLast())) {
      return -1;
    }

    // Move the snake's head to the new position
    snake.addPositionToFront(nextPoint);

    // Check if the snake eats food at the current position by checking the food set
    if (foodSet.contains(nextPoint)) {
      totalScore++;  // Increase score
      foodSet.remove(nextPoint);  // Remove the eaten food
    } else {
      // No food eaten: move the tail
      snake.removeTail();
    }

    return totalScore;
  }

  private boolean isValidPoint(int x, int y) {
    return x >= 0 && x < height && y >= 0 && y < width;
  }

  private Position getNextPoint(int x, int y, String direction) {
    switch (direction) {
      case GameConstants.UP: return new Position(x - 1, y);
      case GameConstants.DOWN: return new Position(x + 1, y);
      case GameConstants.LEFT: return new Position(x, y - 1);
      case GameConstants.RIGHT: return new Position(x, y + 1);
      default: throw new IllegalArgumentException("Invalid direction");
    }
  }
}