package BitManipulation;

/**
 * You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to
 * find the length of the longest sequence of ls you could create.
 * EXAMPLE
 * Input: 1775 (or: 11011101111)
 * Output: 8
 */
public class FlipBitToWin {
  public static void main(String[] args) {
    int i = 1775;
    System.out.println(findMaxLengthSequence(Integer.toBinaryString(i).toCharArray()));
    System.out.println(findMaxLengthSequenceByBitwise(i));

    i = 71;
    System.out.println(findMaxLengthSequence(Integer.toBinaryString(i).toCharArray()));
    System.out.println(findMaxLengthSequenceByBitwise(i));

    i = 13;
    System.out.println(findMaxLengthSequence(Integer.toBinaryString(i).toCharArray()));
    System.out.println(findMaxLengthSequenceByBitwise(i));

    i = 15;
    System.out.println(findMaxLengthSequence(Integer.toBinaryString(i).toCharArray()));
    System.out.println(findMaxLengthSequenceByBitwise(i));
  }

  private static int findMaxLengthSequenceByBitwise(int i) {
    //When all of 1s
    if (~i == 0) {
      return 8 * sizeOf();
    }

    int count = 0;
    int prevCount = 0;
    int maxCount = 0;

    while (i != 0) {
      if ((i & 1) == 1) {
        count++;
      } else {
        if (prevCount + count > maxCount) {
          maxCount = prevCount + count;
        }
        prevCount = count;
        count = 0;
      }
      i >>= 1;
    }
    if (prevCount + count > maxCount) {
      maxCount = prevCount + count;
    }
    return maxCount + 1;
  }

  private static byte sizeOf() {
    byte sizeOfInteger = 8;
    return sizeOfInteger;
  }

  private static int findMaxLengthSequence(char[] arr) {
    int count = 0;
    int prevCount = 0;
    int maxCount = 0;
    for(char c : arr) {
      if (c == '1') {
        count++;
      } else {
        if (prevCount + count > maxCount) {
          maxCount = prevCount + count;
        }
        prevCount = count;
        count = 0;
      }
    }
    if (prevCount + count > maxCount) {
      maxCount = prevCount + count;
    }
    return maxCount + 1;
  }
}
