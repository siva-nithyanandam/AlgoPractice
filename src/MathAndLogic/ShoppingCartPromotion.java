package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ShoppingCartPromotion {

  public static void main(String[] args) {
    ShoppingCartPromotion o = new ShoppingCartPromotion();
    List<String> codeList = Arrays.asList("apple apple", "anything banana");
    List<String> shoppingCart = Arrays.asList("orange", "apple", "apple", "papaya", "banana");
    System.out.println(o.promotion(codeList, shoppingCart));
  }

  private int promotion(List<String> codeList, List<String> shoppingCart) {
    int c = 0, s = 0, prevS = 0;
    List<String> codes = new ArrayList<>();
    for (String t : codeList) {
      codes.addAll(Arrays.asList(t.split(" ")));
    }
    while(shoppingCart.size() - prevS >= codes.size()) {
      if (codes.get(c).equals("anything") || codes.get(c).equals(shoppingCart.get(s))) {
        c++;
        s++;
      } else {
        c = 0;
        s = ++prevS;
      }
      if (c == codes.size()) {
        return 1;
      }
    }
    return 0;
  }


}
