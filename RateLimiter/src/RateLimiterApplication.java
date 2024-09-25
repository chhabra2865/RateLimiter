import service.RateLimiterService;
import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterApplication {
  static AtomicInteger allowedRequests = new AtomicInteger(0);
  static AtomicInteger deniedRequests = new AtomicInteger(0);

  public static void main(String[] args) {
    RateLimiterService rateLimiterService = new RateLimiterService();
    Timestamp startTime = new Timestamp(System.currentTimeMillis());

    // Runnable task that simulates requests for a customer
    Runnable task = () -> {
      for (int i = 0; i < 5; i++) {
        // Use i as customerId for demonstration
        boolean allowed = rateLimiterService.rateLimit(1); // Ensure customerId is within range
        if (allowed) {
          allowedRequests.incrementAndGet();
        } else {
          deniedRequests.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName() + " - Customer " + (1) + " Request " + i + ": " + (allowed ? "Allowed" : "Denied"));

        // Sleep to simulate time between requests
        try {
          if(i != 4) Thread.sleep(250); // 100 milliseconds between requests
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    // Create and start multiple threads to simulate concurrent requests
    int numberOfThreads = 10;
    Thread[] threads = new Thread[numberOfThreads];
    for (int i = 0; i < numberOfThreads; i++) {
      threads[i] = new Thread(task);
      threads[i].start();
    }

    // Wait for all threads to complete
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    Timestamp endTime = new Timestamp(System.currentTimeMillis());
    System.out.println("Total Run Time = " + (endTime.getTime() - startTime.getTime()) + " milliseconds");
    System.out.println("Total Allowed Requests = " + allowedRequests.get());
    System.out.println("Total Denied Requests = " + deniedRequests.get());
  }
}