package MathAndLogic;

/**
 * There are 100 closed lockers in a hallway. A man begins by opening all 100 lockers.
 * Next, he closes every second locker. Then, on his third pass, he toggles every third locker
 * (closes it if it is open or opens it if it is closed). This process continues for 100 passes,
 * such that on each pass i, the man toggles every ith locker. After his 100th pass in the hallway,
 * in which he toggles only locker #100, how many lockers are open?
 */

/**
 * Try manually on paper. You will find all sqrt's are finally opened. So the total number of
 * opened locker is number of sqrts in the total number of lockers.
 */
public class Lockers {
    public static void main(String[] args) {
        int totalLockers = 100;
        System.out.println((int)Math.sqrt(totalLockers));
    }
}
