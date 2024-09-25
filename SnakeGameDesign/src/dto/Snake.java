package dto;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Snake {
  private Deque<Position> snakeTraceQueue;  // Queue to track snake's body
  private Set<Position> snakeTraceSet;      // Set to ensure no collision with the snake's own body

  public Snake() {
    snakeTraceQueue = new LinkedList<>();
    snakeTraceSet = new HashSet<>();
  }

  public Deque<Position> getSnakeTraceQueue() {
    return snakeTraceQueue;
  }

  public Set<Position> getSnakeTraceSet() {
    return snakeTraceSet;
  }

  public void addPositionToFront(Position position) {
    snakeTraceQueue.addFirst(position);
    snakeTraceSet.add(position);
  }

  public void removeTail() {
    Position tail = snakeTraceQueue.pollLast();
    snakeTraceSet.remove(tail);
  }
}