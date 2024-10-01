import java.util.HashMap;
import java.util.Map;


public class VoteCounter {
  private final Map<Character, int[]> voteCountMap;
  private final Map<Integer, Integer> positionPointsMap;
  public VoteCounter(Map<Integer, Integer> positionPointsMap){
    this.voteCountMap = new HashMap<>();
    this.positionPointsMap = positionPointsMap;
  }

  public Map<Character, int[]> getVoteCountMap() {
    return voteCountMap;
  }

  public void countVotes(String[] votes){
    if(votes.length == 0) return;
    int teamsCount = votes[0].length();
    for(String vote : votes){
      for(int i = 0; i<teamsCount; ++i){
        char member = vote.charAt(i);
        voteCountMap.putIfAbsent(member, new int[teamsCount]);
        voteCountMap.get(member)[i] = voteCountMap.get(member)[i] + positionPointsMap.get(i);
      }
    }
  }
}
