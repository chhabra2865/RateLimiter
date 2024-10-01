public class OnlineElection {
  public static void main(String[] args) {
    int[] persons = {0, 1, 1, 0, 0, 1, 0};
    int[] times = {0, 5, 10, 15, 20, 25, 30};

    TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);

    // Test queries
    System.out.println("Leader at time 3: " + topVotedCandidate.query(3)); // Output: 0
    System.out.println("Leader at time 12: " + topVotedCandidate.query(12)); // Output: 1
    System.out.println("Leader at time 25: " + topVotedCandidate.query(25)); // Output: 1
    System.out.println("Leader at time 15: " + topVotedCandidate.query(15)); // Output: 0
    System.out.println("Leader at time 30: " + topVotedCandidate.query(24)); // Output: 0
    System.out.println("Leader at time 30: " + topVotedCandidate.query(8)); // Output: 0
  }
}
