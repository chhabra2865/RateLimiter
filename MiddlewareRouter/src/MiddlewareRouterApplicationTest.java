import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.MiddlewareRouter;

import static org.junit.jupiter.api.Assertions.*;


public class MiddlewareRouterApplicationTest {
  private MiddlewareRouter _middlewareRouter;

  @BeforeEach
  public void setup(){
    _middlewareRouter = new MiddlewareRouter();
    _middlewareRouter.addRoute("/a/b/c", 1);
    _middlewareRouter.addRoute("/a/b/c/d", 2);
    _middlewareRouter.addRoute("a/b", 3);
    _middlewareRouter.addRoute("/a/b", 4);
  }

  public static Object[][] dataProvider(){
    return new Object[][]{
        {"/a/b/c", 1},
        {"/a/b/c/d", 2},
        {"/a/b", 4},
        {"/a/b/c/d/e", -1},
        {"/a/b//c", 1},
        {"/", -1},
        {"/e/f", -1}
    };
  }

  @ParameterizedTest
  @MethodSource("dataProvider")
  public void middlewareRouterTest(String path, int result){
    assertEquals(result, _middlewareRouter.route(path), "route and expected route should match");
  }
}