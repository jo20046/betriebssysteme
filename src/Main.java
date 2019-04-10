import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

        MyQueue myQueue = new MyQueue();

        taskGenerator(myQueue, 5);
        printCurrentQueue(myQueue);

        Processor processor = new Processor();

        processor.process(myQueue);
        System.out.println("processor.getAverageWait() = " + processor.getAverageWait());

    }

    private static void taskGenerator(MyQueue myQueue, int amount) {

        myQueue.clear();

        for (int i = 0; i < amount; i++) {
            int startTime = ThreadLocalRandom.current().nextInt(0, 10);
            int duration = ThreadLocalRandom.current().nextInt(1, 11);
            myQueue.push(new Task(startTime, duration));
        }
    }

    private static void printCurrentQueue(MyQueue myQueue) {

        System.out.println("\nNumber of Tasks in Queue: " + myQueue.size() + "\n");
        System.out.println("Current Tasks [StartTime, Duration]");
        for (int i = 0; i < myQueue.size(); i++) {
            Task tmp = myQueue.peek(i);
            System.out.println("Task " + i + ": [" + tmp.getStartTime() + ", " + tmp.getDuration() + "]");
        }

    }
}
