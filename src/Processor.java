class Processor {

    private double averageWait;

    Processor() { }

    void process(Queue schedule) {

        // List of Tasks that are currently waiting to be processed
        Queue waitingTasks = new Queue();

        averageWait = 0;
        int totalWait = 0;
        int tasksFinished = 0;
        boolean isActive = false;
        int activeUntil = 0;

        for (int time = 0; schedule.size() > 0 || isActive; time++) {

            StringBuilder sb = new StringBuilder();
            sb.append("\nLoop: ").append(time).append("\n");
            // Get all tasks that start at the current time and store them into waitingTasks
            while (schedule.peek(0).getStartTime() == time) {
                Task newTask = schedule.pull();
                waitingTasks.push(newTask);
                sb.append("Added task to waitingTasks. Duration: ").append(newTask.getDuration()).append("\n");
            }

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

    double getAverageWait() {
        return averageWait;
    }
}
