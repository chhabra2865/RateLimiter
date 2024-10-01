import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessControlTest {

  // Data provider method
  public static Object[][] accessTimeProvider() {
    return new Object[][] {
        {
            Arrays.asList(
                Arrays.asList("Alice", "10:00"),
                Arrays.asList("Bob", "10:05"),
                Arrays.asList("Alice", "10:30"),
                Arrays.asList("Alice", "10:59"),
                Arrays.asList("Bob", "11:05"),
                Arrays.asList("Alice", "11:45"),
                Arrays.asList("Charlie", "12:00"),
                Arrays.asList("Bob", "12:10"),
                Arrays.asList("Bob", "12:30"),
                Arrays.asList("Alice", "13:00")
            ),
            Arrays.asList("Alice")
        },
        {
            Arrays.asList(
                Arrays.asList("Alice", "10:00"),
                Arrays.asList("Alice", "10:01"),
                Arrays.asList("Alice", "10:02"),
                Arrays.asList("Bob", "10:05"),
                Arrays.asList("Bob", "10:10"),
                Arrays.asList("Bob", "10:20"),
                Arrays.asList("Alice", "10:30")
            ),
            Arrays.asList("Bob", "Alice")
        },
        {
            Arrays.asList(
                Arrays.asList("Alice", "10:00"),
                Arrays.asList("Charlie", "10:10"),
                Arrays.asList("Alice", "10:30"),
                Arrays.asList("Alice", "11:00")
            ),
            new ArrayList<>() // No employee meets the criteria
        }
    };
  }

  @ParameterizedTest
  @MethodSource("accessTimeProvider")
  public void testFindHighAccessEmployees(List<List<String>> accessTimes, List<String> expected) {
    EmployeeAccessManager employeeAccessManager = new EmployeeAccessManager();
    List<String> actual = employeeAccessManager.findHighAccessEmployees(accessTimes);
    assertEquals(expected, actual);
  }
}