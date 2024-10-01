import java.util.List;


public class TimeChecker {

  public int check24HourGap(String t1, String t2) {
    int h1 = (t1.charAt(0) - '0') * 10 + (t1.charAt(1) - '0');
    int h2 = (t2.charAt(0) - '0') * 10 + (t2.charAt(1) - '0');
    int m1 = (t1.charAt(2) - '0') * 10 + (t1.charAt(3) - '0');
    int m2 = (t2.charAt(2) - '0') * 10 + (t2.charAt(3) - '0');

    if (h1 == h2) {
      return m2 - m1;
    } else if (h2 - h1 == 1) {
      return 60 - (m1 - m2);
    }
    return Integer.MAX_VALUE;
  }

  public boolean checkTimeConstraint(List<String> times) {
    for (int i = 0; i < times.size() - 2; i++) {
      String t1 = times.get(i);
      String t2 = times.get(i + 2);
      if (check24HourGap(t1, t2) < 60) {
        return true;
      }
    }
    return false;
  }
}