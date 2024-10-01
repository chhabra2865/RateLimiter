import java.util.Comparator;
import java.util.Map;


public class TeamRankingStrategy implements Comparator<Character> {
  private final Map<Character, int[]> voteCountMap;

  public TeamRankingStrategy(Map<Character, int[]> voteCountMap) {
    this.voteCountMap = voteCountMap;
  }

  @Override
  public int compare(Character team1, Character team2) {
    for(int i = 0; i<voteCountMap.get(team1).length; ++i){
      if (voteCountMap.get(team1)[i] != voteCountMap.get(team2)[i]) {
        return Integer.compare(voteCountMap.get(team2)[i], voteCountMap.get(team1)[i]);
      }
    }
    return Character.compare(team1, team2);
  }
}
