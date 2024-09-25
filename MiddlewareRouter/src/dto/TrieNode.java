package dto;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
  private Map<String, TrieNode> children;
  private boolean isPathEnd;
  private int value;

  public TrieNode() {
    children = new HashMap<>();
    isPathEnd = false;
    value = -1;
  }

  public Map<String, TrieNode> getChildren() {
    return children;
  }

  public void setChildren(Map<String, TrieNode> children) {
    this.children = children;
  }

  public boolean isPathEnd() {
    return isPathEnd;
  }

  public void setPathEnd(boolean pathEnd) {
    isPathEnd = pathEnd;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
