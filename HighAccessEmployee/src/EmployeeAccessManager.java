import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class EmployeeAccessManager {
  private final TimeChecker timeChecker;
  private final Map<String, List<String>> accessTimeMap;

  public EmployeeAccessManager() {
    this.timeChecker = new TimeChecker();
    this.accessTimeMap = new HashMap<>();
  }

  public List<String> findHighAccessEmployees(List<List<String>> accessTimes) {
    List<String> output = new ArrayList<>();

    // Record the access times
    for (List<String> entry : accessTimes) {
      String employee = entry.get(0);
      String time = entry.get(1);
      accessTimeMap.putIfAbsent(employee, new ArrayList<>());
      accessTimeMap.get(employee).add(time);
    }

    // Check for each employee if they meet the access criteria
    for (String employee : accessTimeMap.keySet()) {
      List<String> times = accessTimeMap.get(employee);

      if (times.size() >= 3) {
        Collections.sort(times); // Sort times
        if (timeChecker.checkTimeConstraint(times)) {
          output.add(employee);
        }
      }
    }
    return output;
  }
}