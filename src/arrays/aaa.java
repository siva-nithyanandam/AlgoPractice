package arrays;
/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 *
 */
public class aaa {

  public static void main(String[] args) {
    aaa o = new aaa();
    String s = o.removeTags(new StringBuilder("<aml:annotation aml:id=\"3\" w:type=\"Word.Insertion\" aml:author=\"Vidyalakshmi, A (A.)\" aml:createdate=\"2014-03-26T16:48:00Z\">" +
            "<aml:content><w:r><w:rPr><w:rFonts w:ascii=\"Arial\" w:h-ansi=\"Arial\" w:cs=\"Arial\"/><wx:font wx:val=\"Arial\"/>" +
            "</w:rPr><w:softHyphen/></w:r></aml:content></aml:annotation>"), "<aml:annotation", ">", "</aml:annotation>");
    System.out.println(s);
  }

  public String removeTags(final StringBuilder templateBuilder, final String beginTag, final String endTag,
                         final String fullEndTag) {
    int end, start = 0;
    while (start != -1) {
      start = templateBuilder.indexOf(beginTag, start);
      if (start != -1) {
        end = templateBuilder.indexOf(endTag, start);
        templateBuilder.replace(start, end+1, "");

        end = templateBuilder.indexOf(fullEndTag, start);
        if (end != -1) {
          templateBuilder.replace(end, end + fullEndTag.length(), "");
          start = end;
        }
      }
    }
    return templateBuilder.toString();
  }
}
