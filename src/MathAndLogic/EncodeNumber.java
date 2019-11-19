package MathAndLogic;

public class EncodeNumber {

  //TODO revise
  public static void main(String[] args) {
    EncodeNumber o = new EncodeNumber();
    System.out.println(o.encode(2));
    System.out.println(o.encode(3));
    System.out.println(o.encode(4));
    System.out.println(o.encode(5));
    System.out.println(o.encode(6));
    System.out.println(o.encode(7));
    System.out.println(o.encode(8));
    System.out.println(o.encode(9));
    System.out.println(o.encode(10));
    System.out.println(o.encode(23));
  }

  public String encode_faster(int num) {
    return Integer.toBinaryString(num + 1).substring(1);
  }

  public String encode(int num) {
    if (num==0) return "";
    int base = 1, count = 0;
    while (num>=base) {
      num -= base;
      base *=2;
      ++count;
    }
    String res = "";
    while (num!=0) {
      res = String.valueOf(num%2)+res;
      num /= 2;
    }
    while (res.length()<count) res = String.valueOf(0)+res;

    return res;
  }

  public String encode1(int num) {
    if (num == 0) {
      return "";
    }
    num -= 1;
    StringBuilder sb = new StringBuilder();
    if ((num & 1) == 1) {
      sb.append(1);
    } else {
      sb.append(0);
    }

    int e = 2;
    while (num >= e) {
      num -= e;
      e *= 2;
      if (num % e < (e/2)) {
        sb.append(0);
      } else {
        sb.append(1);
      }
    }
    return sb.toString();
  }
}
