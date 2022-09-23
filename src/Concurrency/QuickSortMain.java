package Concurrency;

import java.util.Arrays;
import java.util.concurrent.*;

public class QuickSortMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[]{8, 7, 2, 1, 0, 9, 6, -1, 10};

        ExecutorService es = Executors.newFixedThreadPool(8);
        QuickSortChild child = new QuickSortChild(arr, 0, arr.length-1, es);
        Future<int[]> future = es.submit(child);
        System.out.println(Arrays.toString(future.get()));
        es.shutdown();
    }
}
