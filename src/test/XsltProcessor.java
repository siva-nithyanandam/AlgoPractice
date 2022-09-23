package test;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 3/26/2022  5:27 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

//import net.sf.saxon.TransformerFactoryImpl;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *
 */
public class XsltProcessor {

  public static void main(String[] args) {
    XsltProcessor o = new XsltProcessor();
    o.convert1();
  }

  private void convert1() {

    try {
      long start = System.currentTimeMillis();
      //TransformerFactoryImpl tFactory = new net.sf.saxon.TransformerFactoryImpl();

      Source xslDoc = new StreamSource("src//test//WordXMLViewer.xsl");
      Source xmlDoc = new StreamSource("src//test//35646-2020-1-SystemGenerated.xml");
      String outputFileName = "src//test//report.html";

      OutputStream htmlFile = new FileOutputStream(outputFileName);
      Transformer trasform = null;//tFactory.newTransformer(xslDoc);

      trasform.transform(xmlDoc, new StreamResult(htmlFile));
      long end = System.currentTimeMillis();
      System.out.println("Total time -> " + (end - start)/1000);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void convert() {
    try {

      long start = System.currentTimeMillis();
      TransformerFactory tFactory = TransformerFactory.newInstance();

      Source xslDoc = new StreamSource("src//test//WordXMLViewer.xsl");

      Source xmlDoc = new StreamSource(
          "src//test//ProposalLetter.xml");

      String outputFileName = "src//test//report.html";

      OutputStream htmlFile = new FileOutputStream(outputFileName);

      Transformer trasform = tFactory.newTransformer(xslDoc);

      trasform.transform(xmlDoc, new StreamResult(htmlFile));
      long end = System.currentTimeMillis();
      System.out.println("Total time -> " + (end - start)/1000);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
