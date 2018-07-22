import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlphabetFinderBroken {
  
  public static List<Character> findAlphabet(String[] dictionary) {
    
//    You can look at adjacent words to make assumptions about a few characters.
//    Assume you have a graph data structure, and you'll need to implement topological sorting.
    
    List<Character> alphabet = new ArrayList<Character>();
    
    orderCharacter(dictionary, alphabet, 0, 0, 0);
    
    return alphabet;
  }
  
  private static void orderCharacter(String[] dictionary, List<Character> alphabet, int dictLower, int dictUpper, int wordIndex) {
    
    if (nextWordHasSameStartingCharacter(dictionary, dictUpper, wordIndex) && wordIndex > 0) {
      orderCharacter(dictionary, alphabet, dictLower, dictUpper + 1, wordIndex);
      return;
    }
    
    if (multipleHaveSameCharacter(dictionary, dictLower, dictUpper, wordIndex)) {
      orderCharacter(dictionary, alphabet, dictLower, dictUpper, wordIndex + 1);
      return;
    }
    
    if (multipleWords(dictLower, dictUpper)) {
      addRestOfWord(dictionary[dictLower], alphabet, wordIndex);
      String[] subDict = Arrays.copyOfRange(dictionary, dictLower + 1, dictUpper + 1);
      orderCharacter(subDict, alphabet, 0, dictUpper - dictLower + 1, wordIndex);
    } else {
      addCharactersToAlphabet(dictionary, alphabet, dictLower, wordIndex);
      
      if (!isLastWord(dictionary, dictUpper)) {
        orderCharacter(dictionary, alphabet, dictLower + 1, dictUpper + 1, 0);
      }
    }
  }

  private static boolean multipleWords(int dictLower, int dictUpper) {
    return dictLower < dictUpper;
  }

  private static void addCharactersToAlphabet(String[] dictionary, List<Character> alphabet, int dictIndex, int wordIndex) {
    
    String currentWord = dictionary[dictIndex];
    
    if (alphabet.isEmpty()) {
      for (int i = 0; i < currentWord.length(); i++) {
        alphabet.add(currentWord.charAt(i));
      }
      return;
    }
    
    char charOfLastWord = dictionary[dictIndex - 1].charAt(wordIndex);
    char charOfCurrentWord = currentWord.charAt(wordIndex);
    int charOfLastWordIndex = alphabet.indexOf(charOfLastWord);
    int charOfCurrentWordIndex = alphabet.indexOf(charOfCurrentWord);

//    if ((alphabet.contains(charOfCurrentWord) && charOfLastWordIndex > charOfCurrentWordIndex)) {
    if (alphabet.contains(charOfCurrentWord)) {
      System.out.println(charOfLastWord + " " + charOfLastWordIndex);
      System.out.println(charOfCurrentWord + " " + charOfCurrentWordIndex);
      
    } else {
      
      alphabet.add(charOfLastWordIndex + 1, charOfCurrentWord);
      
    }
    
    addRestOfWord(currentWord, alphabet, wordIndex + 1);
  }
  
  private static void addRestOfWord(String currentWord, List<Character> alphabet, int wordIndex) {
    for (int i = wordIndex; i < currentWord.length(); i++) {
      if (!alphabet.contains(currentWord.charAt(i))) {
        alphabet.add(currentWord.charAt(i));
      }
    }
  }

  private static boolean nextWordHasSameStartingCharacter(String[] dictionary, int dictIndex, int wordIndex) {
    // assumption
    if (!isLastWord(dictionary, dictIndex)) {
      return dictionary[dictIndex].charAt(wordIndex) == dictionary[dictIndex + 1].charAt(wordIndex);
    }
    return false;
  }
  
  private static boolean multipleHaveSameCharacter(String[] dictionary, int dictLower, int dictUpper, int wordIndex) {
    // assumption
    boolean isSameChar = true;
    char firstChar = dictionary[dictLower].charAt(wordIndex);
    for (int i = dictLower + 1; i <= dictUpper; i++) {
      if (dictionary[i].charAt(wordIndex) != firstChar) {
        isSameChar = false;
      }
    }
    return dictLower < dictUpper && isSameChar;
  }
  
  private static boolean isLastWord(String[] dictionary, int dictIndex) {
    return dictIndex == dictionary.length - 1;
  }
  
  private static boolean isLastChar(String[] dictionary, int dictIndex, int wordIndex) {
    return wordIndex == dictionary[dictIndex].length() - 1;
  }

  public static void main(String[] args) {
    String[] dictionary = new String[]{
        "ART", "RAT", "CAT", "CAR"
    };
    
    List<List<Character>> validAlphabets = new ArrayList<List<Character>>();
    validAlphabets.add(Arrays.asList('A', 'T', 'R', 'C'));
    validAlphabets.add(Arrays.asList('T', 'A', 'R', 'C'));
    
    List<Character> alphabet = AlphabetFinderBroken.findAlphabet(dictionary);
    
    for (int j = 0; j < alphabet.size(); j++) {
      System.out.println(alphabet.get(j));
    }
  }

}
