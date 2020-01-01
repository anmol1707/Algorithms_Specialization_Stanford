package Course2.Week3;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MedianMaintenance {

    public MedianMaintenance() {
    }

    public List<Integer> getMedians(InputStream inputStream) {
        List<Integer> medians = new ArrayList<>();
        PriorityQueue<Integer> lowHeap = new PriorityQueue<>((Integer a, Integer b) -> b - a);
        PriorityQueue<Integer> highHeap = new PriorityQueue<>((Integer a, Integer b) -> a - b);
        Scanner in = new Scanner(inputStream);
        int number;

        while(in.hasNextInt()) {
            number = in.nextInt();
            addNewElement(lowHeap, highHeap, number);
            balanceHeaps(lowHeap, highHeap);
            medians.add(getNextMedian(lowHeap, highHeap, number));
        }

        return medians;
    }

    private void addNewElement(PriorityQueue<Integer> lowHeap, PriorityQueue<Integer> highHeap, Integer newNum) {
        Integer median1 = lowHeap.peek();
        if(median1 == null || newNum < median1) {
            lowHeap.add(newNum);
        } else {
            highHeap.add(newNum);
        }
    }

    private Integer getNextMedian(PriorityQueue<Integer> lowHeap, PriorityQueue<Integer> highHeap, Integer newNum) {
        int totalElements = lowHeap.size() + highHeap.size();
        if(totalElements % 2 == 0) {
            return lowHeap.peek();
        } else if(lowHeap.size() > highHeap.size()) {
            return lowHeap.peek();
        } else {
            return highHeap.peek();
        }
    }

    private void balanceHeaps(PriorityQueue<Integer> lowHeap, PriorityQueue<Integer> highHeap) {
        if(lowHeap.size() - highHeap.size() > 1) {
            highHeap.add(lowHeap.poll());
        } else if (highHeap.size() - lowHeap.size() > 1) {
            lowHeap.add(highHeap.poll());
        }
    }
}
