package service;

import contract.Router;
import dto.TrieNode;


public class MiddlewareRouter implements Router {
  private final TrieNode root;
  public MiddlewareRouter(){
    root = new TrieNode();
  }

  @Override
  public void addRoute(String path, int result) {
    if(path.length() == 0 || path.charAt(0) != '/') return;
    String[] subpaths = path.split("/");
    if(subpaths.length == 0) return;
    TrieNode current = root;

    for(String subpath : subpaths){
      if(subpath.isEmpty()) continue;;
      if(!current.getChildren().containsKey(subpath)){
        current.getChildren().put(subpath, new TrieNode());
      }
      current = current.getChildren().get(subpath);
    }
    current.setPathEnd(true);
    current.setValue(result);
  }

  @Override
  public int route(String path) {
    if(path.length() == 0 || path.charAt(0) != '/') return -1;
    String[] subPaths = path.split("/");
    if(subPaths.length == 0) return -1;
    TrieNode current = root;
    for(String subpath : subPaths){
      if(subpath.isEmpty()) continue;
      if(!current.getChildren().containsKey(subpath)){
        return -1;
      }
      current = current.getChildren().get(subpath);
    }

    return current.isPathEnd() ? current.getValue() : -1;
  }
}
