package arrays;
/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 *
 */
public class CompareVersionNumbers {

  public static void main(String[] args) {
    CompareVersionNumbers o = new CompareVersionNumbers();
    System.out.println(o.compareVersion("0.1", "1.0"));
  }

  public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");
    int max = Math.max(v1.length, v2.length);

    int i = 0;
    while(i < max) {
      int w1 = 0, w2 = 0;
      if (i < v1.length) {
        w1 = Integer.parseInt(v1[i]);
      }
      if (i < v2.length) {
        w2 = Integer.parseInt(v2[i]);
      }
      if(w1 != w2) {
        return w1 < w2 ? -1 : 1;
      }
      i++;
    }
    return 0;
  }
}
