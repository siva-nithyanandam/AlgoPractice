package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/21/2022  11:27 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */


public class Test {

  @FunctionalInterface
  interface Square {
    int calculate(int x, int y);
  }

  public static void main(String[] args) {
    System.out.println(Math.log10(12));
    double d = Math.log10(12);

    int a;
    if (d % 1 == 0) {
      a = (int) (d);
    } else {
      a = a = (int)d + 1;
    }

    System.out.println(a);

    System.out.println(Math.log10(10));
    System.out.println(Math.log10(100));
    System.out.println(Math.log10(1000));
    System.out.println(Math.log10(2000));
    System.out.println(Math.log10(10000));
    System.out.println(Math.log10(100000000));

    int[] res = {2};
    Square s = (x,y) -> x * y;
    System.out.println(s.calculate(5, 6));

    ObjA objA = new ObjA();
    List<String> sampleList = new ArrayList<>();
    sampleList.add("1");
    sampleList.add("2");
    sampleList.add("3");
    objA.setList(sampleList);

    List<String> sessionChargingPeriods = Optional.ofNullable(objA.getList())
            .map(ArrayList::new).orElseThrow();
    sessionChargingPeriods.add(0,"4");
    System.out.println(sessionChargingPeriods.size());
  }
}
