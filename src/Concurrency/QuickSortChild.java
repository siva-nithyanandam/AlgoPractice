package Concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class QuickSortChild implements Callable<int[]> {

    private int[] arr;
    private int low;
    private int high;
    private ExecutorService es;

    public QuickSortChild(int[] arr, int low, int high, ExecutorService es) {
        this.arr = arr;
        this.low = low;
        this.high = high;
        this.es = es;
    }

    @Override
    public int[] call() throws InterruptedException, ExecutionException {

        if (low < high) {
            int pIdx = partition();
            QuickSortChild child1 = new QuickSortChild(arr, low, pIdx-1, es);
            Future<int[]> future1 = es.submit(child1);
            QuickSortChild child2 = new QuickSortChild(arr, pIdx+1, high, es);
            Future<int[]> future2 = es.submit(child2);

            /*while (!future1.isDone() || !future2.isDone()) {
                Thread.sleep(1000);
            }*/
            future1.get();
            future2.get();
        }

        return arr;
    }

    private int partition() {
        int s = low;

        for (int i = low; i < high; i++) {
            if (arr[i] < arr[high]) {
                int temp = arr[s];
                arr[s] = arr[i];
                arr[i] = temp;
                s++;
            }
        }

        int temp = arr[s];
        arr[s] = arr[high];
        arr[high] = temp;
        return s;
    }
}
