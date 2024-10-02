import dto.File;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {
  private static Object[][] FileSystemDataSource(){
    return new Object[][]{
        {
            Arrays.asList(
                new File("A", 10),
                new File("B", 20, Arrays.asList("C1")),
                new File("C", 50, Arrays.asList("C2", "C1")),
                new File("D", 5, Arrays.asList("C3")),
                new File("E", 200, Arrays.asList("C4")),
                new File("F", 6, Arrays.asList("C3"))
            ),
            2, // Top K collections
            291, // Expected total file size
            Arrays.asList(
                new AbstractMap.SimpleEntry<>("C4", 200),
                new AbstractMap.SimpleEntry<>("C1", 70)
            ) // Expected collections with sizes
        },

        // Test case 2: Files without any collections
        {
            Arrays.asList(
                new File("G", 30),
                new File("H", 40),
                new File("I", 50)
            ),
            1,
            120,
            Collections.emptyList() // No collections
        },

        // Test case 3: Overlapping collections
        {
            Arrays.asList(
                new File("J", 100, Arrays.asList("C5", "C6")),
                new File("K", 200, Arrays.asList("C5")),
                new File("L", 150, Arrays.asList("C6"))
            ),
            2,
            450,
            Arrays.asList(
                new AbstractMap.SimpleEntry<>("C5", 300),
                new AbstractMap.SimpleEntry<>("C6", 250)
            )
        }
    };
  }

  @ParameterizedTest
  @MethodSource("FileSystemDataSource")
  public void FileSystemTest(List<File> files, int K, int expectedTotalSize, List<Map.Entry<String, Integer>> expectedCollections){
    FileSystem fileSystem = new FileSystem();

    for(File file : files){
      fileSystem.processFile(file);
    }

    assertEquals(expectedTotalSize, fileSystem.getTotalFileSize(), "Expected Total Size and total Size should match");
    List<Map.Entry<String, Integer>> collections = fileSystem.getTopKCollections(K);

    for(int i = 0; i<collections.size(); ++i){
      assertEquals(expectedCollections.get(i).getKey(), collections.get(i).getKey(), "Expected Keys and key should match");
      assertEquals(expectedCollections.get(i).getValue(), collections.get(i).getValue(), "Expected Keys and key should match");
    }
  }
}
