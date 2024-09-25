import service.MiddlewareRouter;

public class MiddlewareRouterApplication {
  public static void main(String[] args) {
    MiddlewareRouter middlewareRouter = new MiddlewareRouter();

    // Add routes with integer results
    middlewareRouter.addRoute("/apple/banana/orange", 1);
    middlewareRouter.addRoute("/apple/mango", 2);
    middlewareRouter.addRoute("/apple/banana/grape", 3);
    middlewareRouter.addRoute("/vegetable/potato", 4);
    middlewareRouter.addRoute("/vegetable/tomato", 5);
    middlewareRouter.addRoute("/animal/cat", 6);
    middlewareRouter.addRoute("/animal/dog", 7);

    // Route and verify
    System.out.println(middlewareRouter.route("/apple/banana/orange//")); // Expected: 1
    System.out.println(middlewareRouter.route("/apple/mango"));         // Expected: 2
    System.out.println(middlewareRouter.route("/apple/banana/grape"));  // Expected: 3
    System.out.println(middlewareRouter.route("/vegetable/potato"));    // Expected: 4
    System.out.println(middlewareRouter.route("/vegetable/tomato"));    // Expected: 5
    System.out.println(middlewareRouter.route("/animal/cat"));          // Expected: 6
    System.out.println(middlewareRouter.route("/animal/dog"));          // Expected: 7
    System.out.println(middlewareRouter.route("/apple/banana"));         // Expected: null (or error)
    System.out.println(middlewareRouter.route("/apple/pear"));          // Expected: null (or error)
    System.out.println(middlewareRouter.route("/"));                    // Expected: null (or error)
  }
}