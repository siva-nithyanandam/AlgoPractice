package MathAndLogic;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DesktopPrint {

    public static void main(String[] args) {
        //The desktop api can help calling other applications in our machine
        //and also many other features...
        Desktop desktop = Desktop.getDesktop();
        try {
            //desktop.print(new File("DocXfile.docx"));
            desktop.print(new File("C:/Users/SIVAPRAKASH/Junk/MI_W4.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
