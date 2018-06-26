import java.util.HashSet;
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
            "",
            i,
            j);
        totalWords.addAll(wordSet);
      }
    }
    
    return totalWords;
  }
  
  /**
   * Returns the set of words in the dictionary that can be found starting from the specified position in the grid.
   * A boolean grid, a String set and a String list are used to keep track of the progress.
   * @param grid
   * @param dictionary
   * @param visited     specifies which positions have been visited
   * @param words       words found
   * @param path        path through the grid
   * @param posX        x coordinate in the grid
   * @param posY        y coordinate in the grid
   * @return            the set of words found
   */
  private static Set<String> searchWords(
      Grid grid,
      Dictionary dictionary,
      boolean[][] visited,
      Set<String> words,
      String path,
      int posX,
      int posY) {
    
    if (posX < 0 || posX >= grid.getHeight() || posY < 0 || posY >= grid.getWidth() || visited[posX][posY]) {
      return new HashSet<String>();
    }
    
    // Update to include new position
    path = path + grid.getLetter(posX, posY);
    visited[posX][posY] = true;
    
    if (!dictionary.isPrefix(path)) {
      return new HashSet<String>();
    }
    
    if (dictionary.isWord(path)) {
      words.add(path);
    }
    
    // Recursively search each of the 8 adjacent cells
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        // Make copies of path and visited to search for words in different states
        String pathCopy = new String(path);
        boolean[][] visitedCopy = new boolean[visited.length][];
        for (int k = 0; k < visited.length; k++) {
          visitedCopy[k] = visited[k].clone();
        }
        
        Set<String> newWords = searchWords(grid, dictionary, visitedCopy, new HashSet<String>(), pathCopy, posX + i, posY + j);
        
        words.addAll(newWords);
      }
    }
    
    return words;
  }
}
