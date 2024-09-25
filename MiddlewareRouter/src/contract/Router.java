package contract;

public interface Router {
  void addRoute(String path, int result);
  int route(String path);
}
