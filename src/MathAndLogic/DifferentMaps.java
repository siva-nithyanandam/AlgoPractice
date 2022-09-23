package MathAndLogic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/19/2021  11:57 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

//https://crunchify.com/hashmap-vs-concurrenthashmap-vs-synchronizedmap-how-a-hashmap-can-be-synchronized-in-java/
//https://crunchify.com/what-is-threadsafe-blockingqueue-in-java-and-when-you-should-use-it-implementation-attached/
//https://crunchify.com/what-is-java-semaphore-and-mutex-java-concurrency-multithread-explained-with-example/
public class DifferentMaps {

  public static void main(String[] args) throws InterruptedException {

    Map<String, Integer> hashMap = new HashMap<>();
    performTest(hashMap);
    Map<String, Integer> syncHashMap = Collections.synchronizedMap(new HashMap<>());
    performTest(syncHashMap);
    Map<String, Integer> concHashMap = new ConcurrentHashMap<>();
    performTest(concHashMap);
  }

  private static void performTest(final Map<String, Integer> crunchifyThreads)
      throws InterruptedException {
    System.out.println("Test started for: " + crunchifyThreads.getClass());
    long averageTime = 0;

    for (int i = 0; i < 5; i++) {
      long startTime = System.nanoTime();
      ExecutorService crunchifyExServer = Executors.newFixedThreadPool(5);

      for (int j = 0; j < 5; j++) {
        crunchifyExServer.execute(new Runnable() {
          public void run() {

            for (int i = 0; i < 5000; i++) {
              Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 5500);

              // Retrieve value. We are not using it anywhere
              Integer crunchifyValue = crunchifyThreads.get(String.valueOf(crunchifyRandomNumber));

              // Put value
              crunchifyThreads.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);
            }
          }
        });
      }

      // Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted. Invocation
      // has no additional effect if already shut down.
      // This method does not wait for previously submitted tasks to complete execution. Use awaitTermination to do that.
      crunchifyExServer.shutdown();

      // Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is
      // interrupted, whichever happens first.
      crunchifyExServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

      long entTime = System.nanoTime();
      long totalTime = (entTime - startTime) / 1000000L;
      averageTime += totalTime;
      System.out.println("500K entried added/retrieved in " + totalTime + " ms");
    }
    System.out.println("For " + crunchifyThreads.getClass() + " the average time is " + averageTime / 5 + " ms\n");
  }
}
