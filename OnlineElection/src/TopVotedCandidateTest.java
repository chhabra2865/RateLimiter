import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopVotedCandidateTest {

  // Data provider method for parameterized tests
  static Object[][] provideTestCases() {
    return new Object[][] {
        // Normal cases
        {new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30}, 3, 0},
        {new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30}, 12, 1},
        {new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30}, 25, 1},
        {new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30}, 15, 0},
        {new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30}, 30, 0},

        // Edge cases
        // Case with only one vote
        {new int[]{0}, new int[]{0}, 0, 0},
        {new int[]{1}, new int[]{1}, 1, 1},
        {new int[]{1}, new int[]{1}, 2, 1}, // Still the same leader

        // Case with no votes (edge case)
        {new int[]{}, new int[]{}, 0, -1}, // No leaders since no votes are cast

        // Case where the leader changes frequently
        {new int[]{0, 1, 0, 1, 0, 1, 0, 1}, new int[]{0, 5, 10, 15, 20, 25, 30, 35}, 10, 0}, // Leading: 0
        {new int[]{0, 1, 0, 1, 0, 1, 1, 1}, new int[]{0, 5, 10, 15, 20, 25, 30, 35}, 30, 1}, // Leading: 1

        // Case with tied votes
        {new int[]{0, 1, 1, 0, 0, 1}, new int[]{0, 5, 10, 15, 20, 25}, 10, 1}, // Leading: 1
        {new int[]{0, 1, 1, 0, 0, 1}, new int[]{0, 5, 10, 15, 20, 25}, 15, 0}, // Leading: 0
    };
  }

  @ParameterizedTest
  @MethodSource("provideTestCases")
  void testTopVotedCandidate(int[] persons, int[] times, int queryTime, int expectedLeader) {
    TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
    assertEquals(expectedLeader, topVotedCandidate.query(queryTime));
  }
}