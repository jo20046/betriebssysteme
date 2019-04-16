public class Main {

    public static void main(String[] args) {

        Processor processor = new Processor(100, 20);
        processor.process();

        System.out.println("\nprocessor.getAverageWait() = " + processor.getAverageWait());
        System.out.println("processor.getTasksFinished() = " + processor.getTasksFinished());

    }
}
