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

/**
 * Typical question. Got to observe the question carefully - How many bytes, pixels and width.
 * And usual set and get bit operations.
 */
public class DrawLine {

    public static void main(String[] args) {

        byte[] screen = new byte[16];
        int width = 32;
        printScreen(screen, width);
        System.out.println("");
        drawLine(screen, width, 3, 30, 3);
//        drawLine(screen, width, 31,31, 1);
//        drawLine(screen, width, 2, 29, 2);
//        drawLine(screen, width, 1, 5, 3);
        printScreen(screen, width);
    }

    private static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        int startRow = (x1/8) + (y * width/8);
        int endRow = (x2/8) + (y * width/8);

        for (int i = startRow + 1; i < endRow; i++) {
            screen[i] = (byte)0xFF;
        }
        if (startRow == endRow) {
            screen[startRow] |= (byte)((0xFF >> (x1%8)) & ~(0xFF >> ((x2%8) + 1)));
        } else {
            screen[startRow] |= (byte)(0xFF >> (x1%8));
            screen[endRow] |= (byte)(~(0xFF >> ((x2%8) + 1)));
        }
    }

    private static void printScreen(byte[] screen, int width) {
        int count = 0;
        for(byte b : screen) {
            for(int j = 7; j >= 0; j--) {
                System.out.print((b >> j) & 1);
            }
            count++;
            if (count % (width/8) == 0) {
                System.out.println("");
            }
        }
    }
}
