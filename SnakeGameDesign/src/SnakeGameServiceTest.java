
import dto.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.SnakeGameService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeGameServiceTest {

  private SnakeGameService snakeGameService;

  @BeforeEach
  public void setup() {
    // Common setup if required before each test case
  }

  @ParameterizedTest
  @MethodSource("provideTestCasesForMove")
  public void testMove(String direction, List<List<Integer>> food, int width, int height, int expectedScore) {
    Set<Position> foodPositions = convertFoodListToSet(food);  // Convert List to Set of Positions
    snakeGameService = new SnakeGameService(width, height, foodPositions);
    assertEquals(expectedScore, snakeGameService.move(direction));
  }

  @ParameterizedTest
  @MethodSource("provideTestCasesForBoundaries")
  public void testBoundary(String direction, List<List<Integer>> food, int width, int height, int expectedScore) {
    Set<Position> foodPositions = convertFoodListToSet(food);  // Convert List to Set of Positions
    snakeGameService = new SnakeGameService(width, height, foodPositions);
    assertEquals(expectedScore, snakeGameService.move(direction));
  }

  // Data provider using Object[][]
  public static Object[][] provideTestCasesForMove() {
    return new Object[][]{
        {"R", Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2)), 3, 3, 1},
        {"D", Arrays.asList(Arrays.asList(0, 1), Arrays.asList(2, 2)), 3, 3, 0},
        {"L", Arrays.asList(Arrays.asList(0, 2)), 4, 4, -1},
        {"U", Arrays.asList(Arrays.asList(2, 1)), 2, 3, -1}
    };
  }

  public static Object[][] provideTestCasesForBoundaries() {
    return new Object[][]{
        {"U", Arrays.asList(Arrays.asList(0, 1)), 2, 3, -1},  // Boundary crossing upwards
        {"D", Arrays.asList(Arrays.asList(1, 1)), 2, 3, 0},   // Within boundary, no food
        {"R", Arrays.asList(Arrays.asList(0, 1)), 3, 3, 1},   // Eat food
        {"L", Arrays.asList(), 3, 3, -1}                      // Cross boundary left
    };
  }

  // Convert List<List<Integer>> to Set<Position>
  private Set<Position> convertFoodListToSet(List<List<Integer>> foodList) {
    Set<Position> foodPositions = new HashSet<>();
    for (List<Integer> food : foodList) {
      foodPositions.add(new Position(food.get(0), food.get(1)));
    }
    return foodPositions;
  }
}