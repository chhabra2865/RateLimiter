import java.util.HashMap;
import java.util.Map;


public class TeamRankerApplication {
  public static void main(String[] args) {
    String[] votes = {"ABC", "ACB", "ABC", "ACB", "ACB", "DHA"};

    Map<Integer, Integer> positionPointsMap = new HashMap<>();

    // Add points for different positions
    positionPointsMap.put(0, 6);  // i = 0 gets 6 points
    positionPointsMap.put(1, 3);  // i = 1 gets 3 points
    positionPointsMap.put(2, 1);  // i = 2 gets 1 point

    //{{"TEAM1" ,"TEAM2", "TEAM3"}}
    VoteCounter voteCounter = new VoteCounter(positionPointsMap);
    voteCounter.countVotes(votes);

    TeamRanker teamRanker = new TeamRanker(voteCounter.getVoteCountMap(), new TeamRankingStrategy(voteCounter.getVoteCountMap()));
    System.out.println("Final Team Ranking -> " + teamRanker.rankTeams().substring(0, 3));
  }
}
