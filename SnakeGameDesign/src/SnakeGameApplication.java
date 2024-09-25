import dto.Position;
import service.SnakeGameService;

import java.util.HashSet;
import java.util.Set;

public class SnakeGameApplication {
  public static void main(String[] args) {
    Set<Position> foodPositions = new HashSet<>();
    foodPositions.add(new Position(1, 2));  // Example food positions
    foodPositions.add(new Position(0, 1));

    SnakeGameService snakeGame = new SnakeGameService(5, 5, foodPositions);

    // Move the snake and display the results
    System.out.println("Move right: " + snakeGame.move("R"));  // Expected score: 0
    System.out.println("Move down: " + snakeGame.move("D"));   // Expected score: 1 if food is eaten
    System.out.println("Move right: " + snakeGame.move("R"));  // Expected score: 1
    System.out.println("Move down: " + snakeGame.move("D"));   // Continue moving
  }
}