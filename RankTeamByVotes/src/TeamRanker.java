import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeamRanker {
  private final Map<Character, int[]> voteCountMap;
  private final TeamRankingStrategy rankingStrategy;

  public TeamRanker(Map<Character, int[]> voteCountMap, TeamRankingStrategy rankingStrategy) {
    this.voteCountMap = voteCountMap;
    this.rankingStrategy = rankingStrategy;
  }

  public String rankTeams(){
    List<Character> teams = new ArrayList<>(voteCountMap.keySet());
    teams.sort(rankingStrategy);
    StringBuilder rankedTeams = new StringBuilder();
    for(Character team : teams){
      rankedTeams.append(team);
    }
    return rankedTeams.toString();
  }
}
