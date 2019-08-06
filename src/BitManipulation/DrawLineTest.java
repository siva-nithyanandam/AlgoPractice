package BitManipulation;

public class DrawLineTest {
    public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        int startOffset = x1 % 8;
        int firstByte = startOffset == 0 ? x1/8 : x1/8 + 1;
        int endOffset = x2 % 8;
        int lastByte = endOffset == 7 ? x2/8 : x2/8 - 1;

        // draw line for full bytes
        for (int i = firstByte; i <= lastByte; ++i) {
            screen[i + width/8*y] = (byte) 0xFF;
        }

        // draw start and end of line
        byte startMask = (byte) (0xFF >> startOffset);
        byte endMask = (byte) ~(0xFF >> (endOffset + 1));
        if (x1/8 == x2/8) {
            byte mask = (byte) (startMask & endMask);
            screen[x1/8 + width/8*y] |= mask;
        }
        else {
            if (startOffset != 0) {
                screen[firstByte - 1 + width/8*y] |= startMask;
            }
            if (endOffset != 7) {
                screen[lastByte + 1 + width/8*y] |= endMask;
            }
        }
    }

    //TEST----------------------------------
    public static void main(String[] args) {
        byte[] screen = new byte[16];
        int width = 32;
        printScreen(screen, width);
        drawLine1(screen, width, 1, 30, 3);
        printScreen(screen, width);
        /*drawLine(screen, width, 31,31, 1);
        drawLine(screen, width, 2, 29, 2);
        drawLine(screen, width, 1, 5, 3);
        printScreen(screen, width);*/
    }

    private static void printScreen(byte[] screen, int width) {
        int num = 1, widthNum = width/8;
        for (byte b : screen) {
            for (int i = 7; i >= 0; --i) {
                System.out.print((b >> i) & 1);
            }
            if (num % widthNum == 0) System.out.println();
            ++num;
        }
        System.out.println();
    }

    static byte[] drawLine1(byte[] screen, int width, int x1, int x2, int y) {
        if (x2 < x1) throw new IllegalArgumentException("end must not be less than start");
        int startByte = x1 / 8;
        int endByte = x2 / 8;

        int row = width / 8 * y;

        for (int i = startByte + 1; i <= endByte - 1; i++) {
            screen[row + i] = (byte) 0xff;
        }

        int startIndex = x1 % 8;
        int endIndex = x2 % 8;

        if (startByte == endByte) {
            set(screen, row + startByte, startIndex, endIndex);
        } else {
            set(screen, row + startByte, startIndex, 7);
            set(screen, row + endByte, 0, endIndex);
        }
        return screen;
    }

    private static void set(byte[] screen, int i, int start, int end) {
        screen[i] |= (byte) ((0xff >> start) & ~(0xff >> (end + 1)));
    }
}
