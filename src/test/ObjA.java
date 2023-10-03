package test;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/20/2022  10:54 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.List;
import java.util.Objects;

/**
 *
 */
public class ObjA {

  private int i;
  private String s;

  private List<String> list;

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjA objA = (ObjA) o;
    return i == objA.i && Objects.equals(s, objA.s);
  }

  @Override
  public int hashCode() {
    return Objects.hash(i, s);
  }

  public List<String> getList() {
    return list;
  }

  public void setList(List<String> list) {
    this.list = list;
  }
}
