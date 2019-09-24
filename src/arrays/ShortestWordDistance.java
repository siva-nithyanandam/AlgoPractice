package arrays;

/**
 * Given a list of words and two words word1 and word2,
 * return the shortest distance between these two words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “coding”, word2 = “practice”, -> return 3.
 * Given word1 = "makes", word2 = "coding", -> return 1.
 */
public class ShortestWordDistance {

  public static void main(String[] args) {
    ShortestWordDistance o = new ShortestWordDistance();
    System.out.println(o.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"));
    System.out.println(o.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "makes"));
    System.out.println(o.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "different"));
  }

  public int shortestDistance(String[] words, String word1, String word2) {
    if (words.length == 0) {
      return 0;
    }
    int m = -1, n = -1;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < words.length; i++) {
      if (word1.equals(words[i])) {
        m = i;
        if (n != -1) {
          min = Math.min(min, m - n);
        }
      } else if (word2.equals(words[i])) {
        n = i;
        if (m != -1) {
          min = Math.min(min, n - m);
        }
      }
    }
    return min;
  }
}
