import dto.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class FileSystem {
  private int totalFileSize;
  private Map<String, Integer> collectionSizeMap;

  public FileSystem(){
    this.totalFileSize = 0;
    this.collectionSizeMap = new HashMap<>();
  }

  public int getTotalFileSize() {
    return totalFileSize;
  }

  public void processFile(File file){
    this.totalFileSize += file.getFileSize();
    for(String collection : file.getCollections()){
      collectionSizeMap.put(collection, collectionSizeMap.getOrDefault(collection, 0) + file.getFileSize());
    }
  }

  public List<Map.Entry<String, Integer>> getTopKCollections(int K){
    List<Map.Entry<String, Integer>> result = new ArrayList<>();
    PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
      Comparator.comparingInt(Map.Entry :: getValue)
    );

    for(Map.Entry<String, Integer> entry : collectionSizeMap.entrySet()){
      minHeap.add(entry);
      if(minHeap.size() > K){
        minHeap.poll();
      }
    }

    while(!minHeap.isEmpty()){
      result.add(minHeap.poll());
    }

    Collections.reverse(result);
    return result;
  }
}
