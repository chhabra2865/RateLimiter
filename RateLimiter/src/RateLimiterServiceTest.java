import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.RateLimiterService;

import static org.junit.jupiter.api.Assertions.*;


public class RateLimiterServiceTest {
  private RateLimiterService _rateLimiterService;

  @BeforeEach
  public void setup(){
    _rateLimiterService = new RateLimiterService();
  }

  static Object[][] rateLimiterTestDataProvider(){
    return new Object[][]{
        {5, 1, 0, 5, 0},
        {10, 1, 0, 5, 5},
        {15, 1, 0, 5, 10},
        {5, 1, 10, 5, 1},
        {5, 1, 10000, 6, 0},
        {100, 2, 0, 5, 95},
        {0, 1, 1, 1, 0}
    };
  }

  @ParameterizedTest
  @MethodSource("rateLimiterTestDataProvider")
  public void rateLimiterTest_withoutConcurrency(int numOfRequests, int customerId, int threadSleepTime, int expectedAllowed, int expectedDenied){
    int allowedRequests = 0;
    int deniedRequests = 0;
    for(int i = 0; i<numOfRequests; ++i){
      boolean allowed = _rateLimiterService.rateLimit(customerId);
      if(allowed){
        ++allowedRequests;
      }
      else ++deniedRequests;
    }

    if(threadSleepTime > 0){
      try{
        Thread.sleep(threadSleepTime);
      }
      catch (InterruptedException exception){
        exception.printStackTrace();
      }

      boolean allowed = _rateLimiterService.rateLimit(customerId);
      if(allowed){
        ++allowedRequests;
      }
      else ++deniedRequests;
    }

    assertEquals(expectedAllowed, allowedRequests, "Expected Allowed and allowed Requests should match");
    assertEquals(expectedDenied, deniedRequests, "Expected Denied and denied Requests should match");
  }

  @Test
  public void rateLimiterTest_withConcurrency() throws InterruptedException{
    int customerId = 1;
    AtomicInteger allowedRequests = new AtomicInteger(0);
    AtomicInteger deniedRequests = new AtomicInteger(0);

    Runnable task = () -> {
      for(int i = 0; i<5; ++i){
        if(i == 4){
          try{
            Thread.sleep(10000);
          }
          catch (InterruptedException exception){
            exception.printStackTrace();
          }
        }

        boolean allowed = _rateLimiterService.rateLimit(customerId);
        if(allowed){
          allowedRequests.incrementAndGet();
        }
        else deniedRequests.incrementAndGet();
      }
    };

    Thread thread1 = new Thread(task);
    Thread thread2 = new Thread(task);

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    assertEquals(7, allowedRequests.get(), "Expected Allowed and allowed Requests should match");
    assertEquals(3, deniedRequests.get(), "Expected Denied and denied Requests should match");
  }
}
