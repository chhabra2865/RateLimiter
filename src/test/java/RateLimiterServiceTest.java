package test.java;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.RateLimiterService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateLimiterServiceTest {

  private RateLimiterService rateLimiterService;

  @BeforeEach
  public void setUp() {
    rateLimiterService = new RateLimiterService();
  }

  // Data provider for test cases
  static Object[][] provideTestData() {
    return new Object[][]{
        {1, 5, 0, 5, 0},  // 5 requests allowed, 0 denied, no sleep
        {1, 6, 0, 5, 1},  // 5 allowed, 1 denied, no sleep
        {1, 6, 10000, 6, 1}, // 5 allowed, 1 denied, sleep to reset window
        {2, 10, 0, 5, 5} // 5 requests allowed for two customers, then denied
    };
  }

  @ParameterizedTest
  @MethodSource("provideTestData")
  public void testRateLimiterWithDataProvider(int customerId, int numRequests, int sleepTime, int expectedAllowed, int expectedDenied) throws InterruptedException {
    Integer allowedRequests = 0;
    Integer deniedRequests = 0;

    // Simulate requests for the customer
    for (int i = 0; i < numRequests; i++) {
      boolean allowed = rateLimiterService.rateLimit(customerId);
      if (allowed) {
        allowedRequests++;
      } else {
        deniedRequests++;
      }
    }

    if (sleepTime > 0) {
      Thread.sleep(sleepTime);  // Simulate time delay between requests
      boolean allowed = rateLimiterService.rateLimit(customerId);
      if (allowed) {
        allowedRequests++;
      } else {
        deniedRequests++;
      }
    }

    // Assert on allowed and denied request counts
    assertEquals(expectedAllowed, allowedRequests, "Number of allowed requests should match");
    assertEquals(expectedDenied, deniedRequests, "Number of denied requests should match");
  }

  @Test
  public void testConcurrencyHandling() throws InterruptedException {
    int customerId = 1;
    AtomicInteger allowedRequests = new AtomicInteger(0);
    AtomicInteger deniedRequests = new AtomicInteger(0);

    // Runnable task simulating concurrent requests for a customer
    Runnable task = () -> {
      for (int i = 0; i < 5; i++) {  // Assuming limit is 5 requests
        if (rateLimiterService.rateLimit(customerId)) {
          allowedRequests.incrementAndGet();
        } else {
          deniedRequests.incrementAndGet();
        }
      }
    };

    // Create and start multiple threads to simulate concurrency
    Thread thread1 = new Thread(task);
    Thread thread2 = new Thread(task);
    thread1.start();
    thread2.start();

    // Wait for both threads to complete
    thread1.join();
    thread2.join();

    // Assert on allowed and denied request counts
    assertEquals(5, allowedRequests.get(), "Number of allowed requests should match");
    assertEquals(5, deniedRequests.get(), "Number of denied requests should match");
  }
}