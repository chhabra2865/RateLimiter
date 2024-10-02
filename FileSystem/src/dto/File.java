package dto;

import java.util.ArrayList;
import java.util.List;

public class File {
  private String fileName;
  private int fileSize;
  private List<String> collections;

  public File(String fileName, int fileSize) {
    this.fileName = fileName;
    this.fileSize = fileSize;
    this.collections = new ArrayList<>();
  }

  public File(String fileName, int fileSize, List<String> collections) {
    this.fileName = fileName;
    this.fileSize = fileSize;
    this.collections = collections;
  }

  public String getFileName() {
    return fileName;
  }

  public int getFileSize() {
    return fileSize;
  }

  public List<String> getCollections() {
    return collections;
  }
}
