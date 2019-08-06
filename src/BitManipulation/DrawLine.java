package BitManipulation;

/**
 * A monochrome screen is stored as a single array of bytes, allowing eight consecutive
 * pixels to be stored in one byte.
 * The screen has width w , where w is divisible by 8 (that is, no byte will be split across rows).
 * The height of the screen, of course, can be derived from the length of the array and the width.
 * Implement a function that draws a horizontal line from (xl, y) to (x2, y).
 * The method signature should look something like:
 * drawLine(byte[] screen, int width, int xl, int x2, int y)
 */
public class DrawLine {

    public static void main(String[] args) {
        byte[] screen = new byte[64];
        int width = 16;
        int y = 3;
        int x1 = 1;
        int x2 = 2;
        printScreen(screen, width);
        System.out.println("");
        drawLine(screen, width, x1, x2, y);
        printScreen(screen, width);
    }

    private static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        int startByte = (y * width) + x1;
        for (int i = startByte; i <= startByte + x2 - x1; i++) {
            screen[i] |= 1;
        }
    }

    private static void printScreen(byte[] screen, int width) {
        for(int i = 0; i < screen.length; i++) {
            if (i%width == 0) {
                System.out.println("");
            }
            System.out.print(screen[i]);
        }
    }
}
