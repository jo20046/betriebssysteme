import java.util.ArrayList;
import java.util.List;

class Queue {

    private List<Task> taskList = new ArrayList<>();

    Queue() {
    }

    void push(Task newTask) {

        int newStartTime = newTask.getStartTime();

        if (taskList.size() == 0) {     // empty list
            taskList.add(newTask);
        } else if (taskList.size() == 1) {  // 1 element
            if (taskList.get(0).getStartTime() > newStartTime) {
                taskList.add(0, newTask);
            } else {
                taskList.add(1, newTask);
            }
        } else {    // 2 or more elements
            boolean added = false;
            if (newStartTime < taskList.get(0).getStartTime()) {    // newTask has lowest startTime of all. Add to front
                taskList.add(0, newTask);
                added = true;
            }
            for (int i = 1; i < taskList.size() && !added; i++) {
                int predecessorStart = taskList.get(i - 1).getStartTime();
                int successorStart = taskList.get(i).getStartTime();
                if (predecessorStart <= newStartTime && successorStart >= newStartTime) {
                    taskList.add(i, newTask);
                    added = true;
                }
            }
            if (!added) {   // newTask has highest startTime of all. Add to end
                taskList.add(newTask);
            }
        }
    }

    Task pull() {
        Task returnTask = taskList.get(0);
        taskList.remove(0);
        return returnTask;
    }

    int size() {
        return taskList.size();
    }

}
