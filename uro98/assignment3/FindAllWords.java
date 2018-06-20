import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllWords {
  
  /**
   * Find all the words in the dictionary that can be formed in the grid.
   * @param grid
   * @param dictionary
   * @return
   */
  public static Set<String> findAllWords(Grid grid, Dictionary dictionary) {
    Set<String> totalWords = new HashSet<String>();
    
    // For each position in the grid, find all the words that can be formed starting from there
    for (int i = 0; i < grid.getHeight(); i++) {
      for (int j = 0; j < grid.getWidth(); j++) {
        Set<String> wordSet = FindAllWords.searchWords(
            grid,
            dictionary,
            new boolean[grid.getHeight()][grid.getWidth()],
            new HashSet<String>(),
            new ArrayList<String>(),
            i,
            j);
        totalWords.addAll(wordSet);
      }
    }
    
    return totalWords;
  }
  
  public static Set<String> searchWords(
      Grid grid,
      Dictionary dictionary,
      boolean[][] visited,
      Set<String> words,
      List<String> path,
      int posX,
      int posY) {
    
    if (posX < 0 || posX >= grid.getHeight() || posY < 0 || posY >= grid.getWidth() || visited[posX][posY]) {
      return new HashSet<String>();
    }
    
    // Update to include new position
    path.add(grid.getLetter(posX, posY));
    visited[posX][posY] = true;
    
    String pathString = String.join("", path);
    
    if (!dictionary.isPrefix(pathString)) {
      return new HashSet<String>();
    }
    
    if (dictionary.isWord(pathString)) {
      words.add(pathString);
    }
    
    // Recursively search each of the 8 adjacent cells
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        // Make copies of path and visited to search for words in different states
        List<String> pathCopy = new ArrayList<String>(path);
        boolean[][] visitedCopy = new boolean[visited.length][];
        for (int k = 0; k < visited.length; k++) {
          visitedCopy[k] = visited[k].clone();
        }
        
        Set<String> newWords = searchWords(grid, dictionary, visitedCopy, words, pathCopy, posX + i, posY + j);
        
        words.addAll(newWords);
      }
    }
    
    return words;
  }
}
