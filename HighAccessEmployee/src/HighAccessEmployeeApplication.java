import java.util.Arrays;
import java.util.List;


public class HighAccessEmployeeApplication {
  public static void main(String[] args) {
    // Example access times for employees
    List<List<String>> accessTimes =
        Arrays.asList(Arrays.asList("Alice", "10:00"), Arrays.asList("Bob", "10:05"), Arrays.asList("Alice", "10:30"),
            Arrays.asList("Alice", "10:59"), Arrays.asList("Bob", "11:05"), Arrays.asList("Alice", "11:45"),
            Arrays.asList("Charlie", "12:00"), Arrays.asList("Bob", "12:10"), Arrays.asList("Bob", "12:30"),
            Arrays.asList("Alice", "13:00"));

    // Create recorder and checker
    TimeChecker timeChecker = new TimeChecker();
    EmployeeAccessManager employeeAccessManager = new EmployeeAccessManager();
    // Get high access employees
    List<String> highAccessEmployees = employeeAccessManager.findHighAccessEmployees(accessTimes);
    System.out.println("High Access Employees: " + highAccessEmployees);
  }
}
