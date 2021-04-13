package MathAndLogic;

import java.io.FileInputStream;
import java.lang.reflect.Array;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintJobAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.event.PrintJobAttributeEvent;
import javax.print.event.PrintJobAttributeListener;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import javax.print.event.PrintServiceAttributeEvent;
import javax.print.event.PrintServiceAttributeListener;


public class PrinterB
{
    public static void main(String... args)
    {
        try
        {
            PrintService infoPrintServiceLookup = PrintServiceLookup.lookupDefaultPrintService();
            infoPrintServiceLookup.addPrintServiceAttributeListener(new MyPrintServiceAttributeListener());

            DocPrintJob docPrintJob = infoPrintServiceLookup.createPrintJob();
            docPrintJob.addPrintJobListener(new MyPrintServiceAttributeListener());

            FileInputStream fis = new FileInputStream("C:/Users/SIVAPRAKASH/Junk/sample.txt");
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(fis, flavor, null);

            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(Chromaticity.COLOR);

            docPrintJob.print(doc, aset);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

class MyPrintServiceAttributeListener implements PrintJobListener,PrintJobAttributeListener,PrintServiceAttributeListener
{
    @Override
    public void printDataTransferCompleted(PrintJobEvent pje)
    {
        System.out.println("printDataTransferCompleted. -> "+pje.getPrintEventType());
    }

    @Override
    public void printJobCompleted(PrintJobEvent pje)
    {
        System.out.println("printJobCompleted. -> "+pje.getPrintEventType());
    }

    @Override
    public void printJobFailed(PrintJobEvent pje)
    {
        System.out.println("printJobFailed. -> "+pje.getPrintEventType());
    }

    @Override
    public void printJobCanceled(PrintJobEvent pje)
    {
        System.out.println("printJobCanceled. -> "+pje.getPrintEventType());
    }

    @Override
    public void printJobNoMoreEvents(PrintJobEvent pje)
    {
        System.out.println("printJobNoMoreEvents. -> "+pje.getPrintEventType());
    }

    @Override
    public void printJobRequiresAttention(PrintJobEvent pje)
    {
        System.out.println("printJobRequiresAttention. -> "+pje.getPrintEventType());
    }

    @Override
    public void attributeUpdate(PrintJobAttributeEvent pjae)
    {
        System.out.println("attributeUpdate Called.");
        PrintJobAttributeSet att = pjae.getAttributes();
        for (Attribute a : att.toArray())
        {
            String attributeName = a.getName();
            String attributeValue = att.get(a.getClass()).toString();

            System.out.println(attributeName + " : " + attributeValue);
        }
    }


    @Override
    public void attributeUpdate(PrintServiceAttributeEvent psae)
    {
        PrintService service = psae.getPrintService();

        Attribute[] attrs = psae.getAttributes().toArray();
        for (int i = 0; i < attrs.length; i++)
        {
            String attrName = attrs[i].getName();
            System.out.println("attrName: "+attrName);

            String attrValue = attrs[i].toString();
            System.out.println("attrValue: "+attrValue);
        }

        Class[] cats = service.getSupportedAttributeCategories();
        for (int j = 0; j < cats.length; j++)
        {
            Attribute attr = (Attribute) service.getDefaultAttributeValue(cats[j]);

            if (attr != null)
            {
                Object o = service.getSupportedAttributeValues(attr.getCategory(), null, null);
                if (o.getClass().isArray())
                {
                    for (int k = 0; k < Array.getLength(o); k++)
                    {
                        Object o2 = Array.get(o, k);
                        System.out.println(" -> "+ o2);
                    }
                }
            }
        }
    }
}