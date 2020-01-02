package Course2.Week4;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TwoSum {

    private final static int lowerBound = -10000, upperBound = 10000;
    private final static int numberOfProcessors = Runtime.getRuntime().availableProcessors();

    public TwoSum() {
    }

    public int getTargetCount(Map<Long, Integer> freqMap) throws InterruptedException {
        AtomicInteger result = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfProcessors);

        for(int target = lowerBound; target <= upperBound; target++) {

            int finalTarget = target;
            executorService.execute(() -> {
                for (Long number : freqMap.keySet()) {
                    Long toFind = finalTarget - number;
                    boolean checkForDistinctValue = toFind.longValue() == number.longValue();
                    if(freqMap.containsKey(toFind) && (!checkForDistinctValue || freqMap.get(toFind) > 1)) {
                        result.getAndIncrement();
                        break;
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        return result.get();
    }
}
