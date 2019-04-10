import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

        Queue queue = new Queue();

        taskGenerator(queue, 20);
        printCurrentQueue(queue);

        Processor processor = new Processor();
        processor.process(queue);

        System.out.println("\nprocessor.getAverageWait() = " + processor.getAverageWait());

    }

    private static void taskGenerator(Queue queue, int amount) {

        queue.clear();

        for (int i = 0; i < amount; i++) {
            int startTime = ThreadLocalRandom.current().nextInt(0, 100);
            int duration = ThreadLocalRandom.current().nextInt(1, 101);
            queue.push(new Task(startTime, duration));
        }
    }

    private static void printCurrentQueue(Queue queue) {

        System.out.println("\nNumber of Tasks in Queue: " + queue.size() + "\n");
        System.out.println("Current Tasks [StartTime, Duration]");
        for (int i = 0; i < queue.size(); i++) {
            Task tmp = queue.peek(i);
            System.out.println("Task " + i + ": [" + tmp.getStartTime() + ", " + tmp.getDuration() + "]");
        }

    }
}
