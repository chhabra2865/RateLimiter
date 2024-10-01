import java.util.HashMap;
import java.util.Map;


public class TopVotedCandidate {
  private final Map<Integer, Integer> timeToLeaderCache;
  private final int[] times;

  public TopVotedCandidate(int[] persons, int[] times) {
    this.timeToLeaderCache = new HashMap<>();
    this.times = times;
    int leader = -1;

    Map<Integer, Integer> voteCountMap = new HashMap<>();
    for(int i = 0; i<persons.length; ++i){
      voteCountMap.put(persons[i], voteCountMap.getOrDefault(persons[i], 0) + 1);
      if(leader == -1 || voteCountMap.get(persons[i]) >= voteCountMap.get(leader)){
        leader = persons[i];
      }

      timeToLeaderCache.put(times[i], leader);
    }
  }

  public int query(int time){
    int low = 0;
    int high = times.length - 1;
    while(low <= high){
      int mid = low + (high - low)/2;
      if(time == times[mid]){
        return timeToLeaderCache.get(time);
      }
      else if(time > times[mid]){
        low = mid + 1;
      }
      else high = mid - 1;
    }
    return high >= 0 ? timeToLeaderCache.get(times[high]) : -1;
  }
}
