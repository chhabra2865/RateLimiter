package dto;

public class TokenBucket {
  private final int maxRequests;
  private int tokens;
  private long lastRefillTime;
  private final long timeWindowMillis;

  public TokenBucket(int maxRequests, long timeWindowMillis) {
    this.maxRequests = maxRequests;
    this.tokens = maxRequests;
    this.timeWindowMillis = timeWindowMillis;
    this.lastRefillTime = System.currentTimeMillis();
  }

  public synchronized boolean grantRequest() {
    long now = System.currentTimeMillis();
    if (now - lastRefillTime > timeWindowMillis) {
      // Reset tokens and last refill time
      tokens = maxRequests;
      lastRefillTime = now;
    }

    if (tokens > 0) {
      tokens--;
      return true;
    } else {
      return false;
    }
  }
}