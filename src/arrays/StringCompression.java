package arrays;

/**
 * Given an array of characters, compress it in-place.
 * <p>
 * The length after compression must always be smaller than or equal to the original array.
 * <p>
 * Every element of the array should be a character (not int) of length 1.
 * <p>
 * After you are done modifying the input array in-place, return the new length of the array.
 * <p>
 * Example : Input: ["a","a","b","b","c","c","c"] Output: Return 6, and the first 6 characters of
 * the input array should be: ["a","2","b","2","c","3"]
 * <p>
 * Input: ["a"] Output: Return 1, and the first 1 characters of the input array should be: ["a"]
 * <p>
 * Input: ["a","b","b","b","b","b","b","b","b","b","b","b","b"] Output: Return 4, and the first 4
 * characters of the input array should be: ["a","b","1","2"].
 */
public class StringCompression {

  public static void main(String[] args) {
    char[] arr3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
    System.out.println(compressCharsInPlaceFastest(arr3));

    char[] arr5 = {'o','o','o','o','o','o','o','o','o','o'};
    System.out.println(compressCharsInPlaceFastest(arr5));

    char[] arr0 = {'a', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'd', 'e'};
    System.out.println(compressCharsInPlaceFastest(arr0));

    char[] arr1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
    System.out.println(compressCharsInPlaceFastest(arr1));

    char[] arr2 = {'a'};
    System.out.println(compressCharsInPlaceFastest(arr2));


    char[] arr4 = {'a', 'b'};
    System.out.println(compressCharsInPlaceFastest(arr4));
  }

  private static int compressCharsInPlaceFastest(char[] arr) {
    StringBuilder sb = new StringBuilder();
    int count = 1;
    char lastChar = '\u0000';
    for (char c : arr) {
      if (lastChar == c) {
        count++;
      } else {
        if (count > 1) {
          sb.append(count);
        }
        sb.append(c);
        count = 1;
        lastChar = c;
      }
    }
    if (count > 1) {
      sb.append(count);
    }
    return sb.length();
  }

    private static int compressCharsInPlaceOwn(char[] arr) {
    int w = 1, r = 1, s = 0;
    if (arr.length <= 1) {
      return arr.length;
    } else {
      for (; r < arr.length; r++) {
        if (arr[r] != arr[s]) {
          w = printLen(r, s, w, arr);
          arr[w++] = arr[r];
          s = r;
        }
      }
      w = printLen(r, s, w, arr);
    }
    return w;
  }

  private static int printLen(int r, int s, int w, char[] arr) {
    if ((r-1) > s) {
      int len = r - s;
      double d = Math.floor(Math.log10(r - s));
      int a = (int)Math.pow(10, d);
      while (a > 0) {
        int digit = len/a;
        arr[w++] = (char)(digit + '0');
        len %= a;
        a /= 10;
      }
    }
    return w;
  }


  /**
   * When current and next char doesn't match, place a char and its length subsequently. Note: Value
   * 12 suppose to be in 2 places - "1" and "2".
   *
   * @param arr
   * @return
   */
  private static int compressCharsInPlaceFastest1(char[] arr) {
    int writePos = 0;
    int startPos = 0;
    for (int reader = 0; reader < arr.length; reader++) {
      if (reader + 1 == arr.length || arr[reader + 1] != arr[reader]) {
        arr[writePos++] = arr[startPos];
        if (reader > startPos) {
          int len = reader - startPos + 1;
          while (len>9) {
            int digit = len/10;
            arr[writePos++] = (char)(digit + '0');
            len = len%10;
          }
          arr[writePos++] = (char)(len + '0');
        }
        startPos = reader + 1;
      }
    }
    return writePos;
  }

  public static int compressCharsInPlaceFaster(char[] chars) {
    int idx = 0;
    int totalLen = 0;
    for (; idx<chars.length; idx++) {
      totalLen++;
      int freq = countChars(chars, idx);
      chars[totalLen-1] = chars[idx];

      if (freq>1) {
        int tempFreq = freq;
        while (tempFreq>9) {
          int digit = tempFreq/10;
          chars[totalLen] = (char) (digit + '0');
          totalLen++;
          tempFreq = tempFreq%10;
        }
        chars[totalLen] = (char) (tempFreq + '0');
        idx = idx + freq - 1;
        totalLen++;
      }

    }
    return totalLen;
  }

  private static int countChars(char[] chars, int index) {
    char ch = chars[index];
    int freq = 0;
    while (index<chars.length && chars[index]==ch) {
      freq++;
      index++;
    }
    return freq;
  }

  private static int compressCharsInPlaceInternet(char[] arr) {
    int writer = 0;
    for (int reader = 0; reader < arr.length; reader++) {
      if (reader + 1 == arr.length || arr[reader + 1] != arr[reader]) {
        if (reader > writer) {
          String len = "" + (reader - writer + 1);
          arr[writer++] = arr[reader];
          for (char c : len.toCharArray()) {
            arr[writer++] = c;
          }
        } else {
          arr[writer++] = arr[reader];
        }
      }
    }
    return writer;
  }
}
