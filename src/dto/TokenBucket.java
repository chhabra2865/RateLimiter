package dto;

public class TokenBucket {
  private final int maxRequests;
//  private final int maxCredits;
  private int tokens;
  private int credits;
  private long lastRefillTime;
  private final long timeWindowMillis;

  public TokenBucket(int maxRequests, long timeWindowMillis, /*int maxCredits*/) {
    this.maxRequests = maxRequests;
//    this.maxCredits = maxCredits;
    this.tokens = maxRequests;
//    this.credits = 0;
    this.timeWindowMillis = timeWindowMillis;
    this.lastRefillTime = System.currentTimeMillis();
  }

  public synchronized boolean grantRequest() {
    long now = System.currentTimeMillis();
    if (now - lastRefillTime > timeWindowMillis) {
      // Refill tokens and convert excess tokens to credits
//      credits = Math.min(maxCredits, credits + tokens);
      tokens = maxRequests;
      lastRefillTime = now;
    }

    if (tokens > 0) {
      tokens--;
      return true;
    }
//    else if (credits > 0) {
//      credits--;
//      return true;
//    }
    else {
      return false;
    }
  }
}