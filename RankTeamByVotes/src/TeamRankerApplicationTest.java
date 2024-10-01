import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamRankerApplicationTest {

  private VoteCounter voteCounter;
  private TeamRanker teamRanker;

  @BeforeEach
  public void setUp() {
    Map<Integer, Integer> positionPointsMap = new HashMap<>();

    // Add points for different positions
    positionPointsMap.put(0, 6);  // i = 0 gets 6 points
    positionPointsMap.put(1, 3);  // i = 1 gets 3 points
    positionPointsMap.put(2, 1);  // i = 2 gets 1 point

    voteCounter = new VoteCounter(positionPointsMap);
  }

  @ParameterizedTest
  @MethodSource("provideVoteData")
  public void testRankTeams(String[] votes, String expected) {
    voteCounter.countVotes(votes);
    teamRanker = new TeamRanker(voteCounter.getVoteCountMap(), new TeamRankingStrategy(voteCounter.getVoteCountMap()));

    String result = teamRanker.rankTeams();
    assertEquals(expected, result, "Ranked teams did not match the expected result.");
  }

  // Data Provider method using Object[][]
  private static Object[][] provideVoteData() {
    return new Object[][] {
        {new String[]{"ABC", "ABC", "ABC"}, "ABC"},
        {new String[]{"ACB", "ABC", "ACB"}, "ACB"},
        {new String[]{"BAC"}, "BAC"},
        {new String[]{"BCA", "CAB", "ACB"}, "CAB"},
        {new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"}, "ACB"},
        {new String[]{"ABC", "ACB", "ABC", "ACB", "ACB", "DHA"}, "ADCBH"},
        {new String[]{}, ""} // Edge case with no votes
    };
  }
}