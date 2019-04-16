import java.util.concurrent.ThreadLocalRandom;

class Processor {

    private double averageWait;
    private int runtime;
    private int generateSpeed;
    private int tasksFinished = 0;

    Processor(int runtime, int generateSpeed) {
        this.runtime = runtime;
        this.generateSpeed = generateSpeed;
    }

    void process() {

        // List of Tasks that are currently waiting to be processed
        Queue waitingTasks = new Queue();

        averageWait = 0;
        int totalWait = 0;
        boolean isActive = false;
        int activeUntil = 0;

        generateTask(waitingTasks, 0, true);
        for (int time = 0; waitingTasks.size() > 0 || isActive; time++) {

            if (time < runtime) {
                generateTask(waitingTasks, time, false);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("\nLoop: ").append(time).append("\n");

            // If the Processor has been active, check if the current task has finished
            if (isActive && activeUntil == time) {
                isActive = false;
                tasksFinished++;
                sb.append("Current task finished\n");
            }

            // Schedule new task
            if (waitingTasks.size() > 0 && !isActive) {
                Task newTask = waitingTasks.pull();
                isActive = true;
                activeUntil = time + newTask.getDuration();
                totalWait += time - newTask.getStartTime();
                sb.append("Scheduled new task: activeUntil: ").append(activeUntil).append("; totalWait: ").append(totalWait).append("\n");
            }
            System.out.print(sb.toString());
        }

        averageWait = (double) totalWait / (double) tasksFinished;
    }

    private void generateTask(Queue waitingTasks, int time, boolean overwrite) {

        boolean generateNew = ThreadLocalRandom.current().nextInt(0, 100) < generateSpeed;
        if (generateNew || overwrite) {
            int duration = ThreadLocalRandom.current().nextInt(10, 50);
            waitingTasks.push(new Task(time, duration));
        }
    }

    double getAverageWait() {
        return averageWait;
    }

    int getTasksFinished() {
        return tasksFinished;
    }
}
