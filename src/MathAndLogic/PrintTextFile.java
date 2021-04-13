package MathAndLogic;

import java.io.FileInputStream;
import java.io.IOException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Sides;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

public class PrintTextFile {

  public static void main(String[] args) throws PrintException, IOException {
    String defaultPrinter =
        PrintServiceLookup.lookupDefaultPrintService().getName();
    System.out.println("Default printer: " + defaultPrinter);

    PrintService service = PrintServiceLookup.lookupDefaultPrintService();

    FileInputStream in = new FileInputStream("c:/Users/SIVAPRAKASH/Junk/sample.txt");

    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
    pras.add(new Copies(1));
    pras.add(Sides.ONE_SIDED);

    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
    Doc doc = new SimpleDoc(in, flavor, null);

    DocPrintJob job = service.createPrintJob();
    //PrintJobWatcher pjw = new PrintJobWatcher(job);
    job.print(doc, pras);
//        pjw.waitForDone();
    in.close();

    // send FF to eject the page
//        InputStream ff = new ByteArrayInputStream("\f".getBytes());
//        Doc docff = new SimpleDoc(ff, flavor, null);
//        DocPrintJob jobff = service.createPrintJob();
////        pjw = new PrintJobWatcher(jobff);
//        jobff.print(docff, null);
////        pjw.waitForDone();
  }
}

class PrintJobWatcher {

  boolean done = false;

  PrintJobWatcher(DocPrintJob job) {
    job.addPrintJobListener(new PrintJobAdapter() {
      public void printJobCanceled(PrintJobEvent pje) {
        allDone();
      }

      public void printJobCompleted(PrintJobEvent pje) {
        allDone();
      }

      public void printJobFailed(PrintJobEvent pje) {
        allDone();
      }

      public void printJobNoMoreEvents(PrintJobEvent pje) {
        allDone();
      }

      void allDone() {
        synchronized (PrintJobWatcher.this) {
          done = true;
          System.out.println("Printing done ...");
          PrintJobWatcher.this.notify();
        }
      }
    });
  }

  public synchronized void waitForDone() {
    try {
      while (!done) {
        wait();
      }
    } catch (InterruptedException e) {
    }
  }
}