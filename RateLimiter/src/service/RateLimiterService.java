package service;

import dto.TokenBucket;
import constants.RateLimiterConstants;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {

  private final Map<Integer, TokenBucket> customerBuckets = new HashMap<>();

  public synchronized boolean rateLimit(int customerId) {
    TokenBucket bucket = customerBuckets.get(customerId);
    if (bucket == null) {
      // Create a new TokenBucket instance
      bucket = new TokenBucket(RateLimiterConstants.MAX_REQUESTS, RateLimiterConstants.TIME_WINDOW_MILLISECONDS);
      //Part2
//      bucket = new TokenBucket(RateLimiterConstants.MAX_REQUESTS, RateLimiterConstants.TIME_WINDOW_MILLISECONDS, RateLimiterConstants.MAX_CREDITS);
      // Put it in the map
      customerBuckets.put(customerId, bucket);
    }

    // Check if the request is allowed
    boolean allowed = bucket.grantRequest();

    return allowed;
  }
}